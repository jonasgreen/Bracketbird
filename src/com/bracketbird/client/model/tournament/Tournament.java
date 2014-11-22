package com.bracketbird.client.model.tournament;


import com.bracketbird.client.gui.rtc.event.*;
import com.bracketbird.client.model.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;

/**
 *
 */
public class Tournament extends PlayableModel<TournamentId>{
    private static final long serialVersionUID = 5951730875833184507L;

    public transient ModelHandlerList<String> nameEventHandlers = new ModelHandlerList<String>();
    public transient ModelHandlerList<TournamentStage> levelsEventHandlers = new ModelHandlerList<TournamentStage>();
    public transient ModelHandlerList<Team> teamsEventHandlers = new ModelHandlerList<Team>();

    private transient LevelState state = LevelState.notReady;

    private String name;
    private String url;
    private String viewUrl;
    private TournamentChannelId tournamentChannelId;
    private boolean viewOnly;

    private List<TournamentStage> levels = new ArrayList<TournamentStage>();
    private List<Team> teams = new ArrayList<Team>();


    public Tournament() {
    }

    public List<TournamentStage> getLevels() {
        return levels;
    }

    public void setLevels(List<TournamentStage> tournamentStages) {
        this.levels = tournamentStages;
    }

    @Override
    public HasLevelState getParent() {
        return null;
    }

    public LevelState getState() {
        return state;
    }

    @Override
    public void childHasChangedState(boolean fromClient) {

    }

    public void replace(int index, TournamentStage st) {
        getLevels().remove(index);
        getLevels().add(index, st);

    }

    public int getIndexOf(TournamentStage st) {
        int index = 0;
        for (TournamentStage stTemp : levels) {
            if (stTemp.equals(st)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateName(String name, boolean fromClient) {
        String oldName = this.name;
        this.name = name;
        nameEventHandlers.fireEvent(new UpdateModelEvent<String>(fromClient, oldName, name));
    }


    public boolean hasTeams() {
        List<Team> teams = getTeams();
        if (teams == null || teams.size() == 0 || teams.size() == 1) {
            return false;
        }
        int countNonEmpty = 0;
        for (Team team : teams) {
            if (countNonEmpty > 1) {
                return true;
            }
            if (!team.isEmpty()) {
                countNonEmpty++;
            }
        }
        return countNonEmpty > 1;
    }

    public boolean hasLevels() {
        List<TournamentStage> list = getLevels();
        return list != null && !list.isEmpty();
    }

    public LevelState calculateState() {
        if (hasLevels()) {
            if (!hasTeams()) {
                return LevelState.notReady;
            }
            int countEmpty = 0;
            int countFinish = 0;
            for (TournamentStage l : levels) {
                if (l.isFinished()) {
                    countFinish++;
                }
                else if (l.isNotReady()) {
                    countEmpty++;
                }
            }
            if (countFinish == levels.size()) {//all levels are finished
                return LevelState.finished;
            }
            else if (countEmpty == levels.size()) {//all levels are notReady
                return LevelState.ready;
            }
            else {
                return LevelState.inProgress;
            }
        }
        else {
            return LevelState.notReady;
        }
    }


    @Override
    public String toString() {
        return "Tournament{" +
                "\n  subtournaments=" + StringUtil.toString(levels) +
                "  Teams=" + StringUtil.toString(teams) +
                "}\n";
    }

    public void createTeam(CreateTeamEvent event) {
        Team team = new Team(event.getTeamName(), event.getSeeding());
        team.setId(event.getModelId());
        team.setEventLogId(event.getEventId());
        teams.add(team);
        teamsEventHandlers.fireEvent(new CreateModelEvent<Team>(event.isFromClient(), team));
        clearAllLevels(event.isFromClient());
        updateState(event.isFromClient());
        updateSeedingAfterCreate(team);
    }

    private void updateSeedingAfterCreate(Team team) {
        team.setSeeding(new UpdateTeamSeedingEvent(0L, team.getId(), getNextSeeding()));
    }


    public void deleteTeam(TeamId id, boolean fromClient) {
        Team team = getTeam(id);
        if (team == null) {
            return;
        }
        teams.remove(team);
        clearAllLevels(fromClient);
        teamsEventHandlers.fireEvent(new DeleteModelEvent<Team>(fromClient, team));
        updateState(fromClient);
        updateSeedingAfterDelete(team);
    }

    private void updateSeedingAfterDelete(Team team) {
        for (Team t : getTeams()) {
            if (t.getSeeding() > team.getSeeding()) {
                t.setSeeding(new UpdateTeamSeedingEvent(0L, t.getId(), t.getSeeding() - 1));
            }
        }
    }

    public Team getTeam(TeamId id) {
        for (Team team : teams) {
            if (team.getId().equals(id)) {
                return team;
            }
        }
        return null;
    }

    public TournamentStage getLevel(TournamentLevelId id) {
        for (TournamentStage level : levels) {
            if (level.getId().equals(id)) {
                return level;
            }
        }
        return null;
    }

    private void clearAllLevels(boolean fromClient) {
        for (TournamentStage l : levels) {
            l.clear(fromClient);
        }
    }



    public void createLevel(CreateLevelEvent event) {
        TournamentStage tl = TournamentLevelFac.create(this, event.getLevelType());
        tl.setId(event.getModelId());
        levels.add(tl);
        levelsEventHandlers.fireEvent(new CreateModelEvent<TournamentStage>(event.isFromClient(), tl));
        updateState(event.isFromClient());
    }

    public void deleteLevel(TournamentLevelId levelId, boolean fromClient) {
        TournamentStage level = getLevel(levelId);
        if (level == null) {
            return;
        }
        levels.remove(level);
        levelsEventHandlers.fireEvent(new DeleteModelEvent<TournamentStage>(fromClient, level));
        updateState(fromClient);
    }

    public void updateLevel(UpdateLevelEvent event) {
        TournamentStage level = getLevel(event.getModelId());
        if (level == null) {
            return;
        }
        int index = levels.indexOf(level);
        levels.get(index).updateStageSettings(event);
        //clear the following
        index++;
        while (index < levels.size()) {
            levels.get(index).clear(event.isFromClient());
            index++;
        }
        updateState(event.isFromClient());
    }


    public boolean updateLevelAllowed(TournamentStage l) {
        return l.isNotReady();
    }


    public Team getPlayingTeam(TeamId id) {
        for (Team team : teams) {
            if (team.getId().equals(id)) {
                return team;
            }
        }
        return null;
    }



    public int getMaxNumberOfTeams(TournamentStage tournamentStage) {
        int min = getTeams().size();
        for (TournamentStage l : levels) {
            Integer maxTeams = l.getStageSettings().getMaxNumberOfTeams();
            if (maxTeams != null && maxTeams < min) {
                min = maxTeams;
            }
            if (l == tournamentStage) {
                return min;
            }
        }
        return min;
    }


    public TournamentVO createVO() {
        TournamentVO vo = new TournamentVO();
        vo.setName(getName());
        return vo;
    }

    private void finished() {
        //TODO
    }

    private boolean isLastLevel(int index) {
        return levels.size() - 1 == index;
    }

    public void layoutMatches(LayoutMatchesEvent event) {
        TournamentStage level = getLevel(event.getModelId());
        if (level == null) {
            return;
        }
        level.layoutMatches(event.isFromClient());
    }


    public void updateMatchResult(UpdateMatchResultEvent event) {
        boolean matchUpdated = false;
        for (TournamentStage level : levels) {
            if(!matchUpdated){
                matchUpdated = level.updateMatchResult(event);
            }
            else{
                System.out.println("clear level");
                level.clear(true);
            }
        }
        updateState(true);
    }

    public void updateMatchField(UpdateMatchFieldEvent event) {
        for (TournamentStage level : levels) {
            if (level.updateMatchField(event)) {
                return;
            }
        }
    }


    public void finishedLevel(LevelFinishedEvent event) {
        TournamentStage level = getLevel(event.getModelId());
        if (level == null) {
            return;
        }
        level.finished(event);
        updateState(event.isFromClient());
    }


    public TournamentStage getNextLevel(TournamentStage current) {
        int index = getIndexOf(current);
        if (!isLastLevel(index)) {
            return levels.get(index + 1);
        }
        return null;
    }

    public TournamentStage getPreviousLevel(TournamentStage current) {
        int index = getIndexOf(current);
        if (index == 0) {
            return null;
        }
        return levels.get(index - 1);

    }

    public TournamentChannelId getTournamentChannelId() {
        return tournamentChannelId;
    }

    public void setTournamentChannelId(TournamentChannelId tournamentChannelId) {
        this.tournamentChannelId = tournamentChannelId;
    }

    public boolean isLastLevel(TournamentStage current) {
        return CU.isLast(levels, current);
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNextSeeding() {
        int nextSeed = 0;
        for (Team team : getTeams()) {
            nextSeed = team.getSeeding() == null ? nextSeed : (team.getSeeding() > nextSeed ? team.getSeeding() : nextSeed);
        }
        return nextSeed + 1;
    }

    public void updateSeeding(UpdateSeedingEvent event) {
        changeTeamsAccordingToSeeding(event.getSeedings());
        //fireSeedingChanged(new SeedingChangedEvent(event.isFromClient(), event.getSeedings()));
        clearAllLevels(event.isFromClient());
        updateState(event.isFromClient());
    }

    private void changeTeamsAccordingToSeeding(List<TeamId> seedings) {
        //TODO - move to tournament
        Map<TeamId, Team> map = new HashMap<TeamId, Team>();
        List<Team> newlyCreatedTeams = new ArrayList<Team>();
        int seedingSize = seedings.size();
        int count = 0;
        for (Team t : getTeams()) {
            if (count++ < seedingSize) {
                map.put(t.getId(), t);
            }
            else {
                newlyCreatedTeams.add(t);
            }
        }

        teams = new ArrayList<Team>();
        int seed = 1;
        for (TeamId seeding : seedings) {
            Team team = map.get(seeding);
            if (team != null) {
                team.setSeedingSilent(seed++);
                teams.add(team);
            }
        }
        for (Team team : newlyCreatedTeams) {
            team.setSeedingSilent(seed++);
            teams.add(team);
        }
    }


    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    public TournamentStage getLevelInPogress() {
        if(state.isInProgress()){
            for (TournamentStage tournamentStage : getLevels()) {
                if(tournamentStage.isInProgress()){
                    return tournamentStage;
                }
            }
        }
        return null;
    }
}



