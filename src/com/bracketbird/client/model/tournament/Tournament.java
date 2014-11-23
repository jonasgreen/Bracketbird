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

    public transient ModelHandlerList<String> nameEventHandlers;
    public transient ModelHandlerList<Stage> stagesEventHandlers;
    public transient ModelHandlerList<Team> teamsEventHandlers;
    public transient ModelHandlerList<List<TeamId>> seedingHandlers;

    private String name;
    private String url;
    private String viewUrl;
    private TournamentChannelId tournamentChannelId;
    private boolean viewOnly;

    private transient List<Stage> stages = new ArrayList<Stage>();
    private transient List<Team> teams = new ArrayList<Team>();


    public Tournament() {
        nameEventHandlers = new ModelHandlerList<String>("Tournament (nameHandler)");
        stagesEventHandlers = new ModelHandlerList<Stage>("Tournament (stageHandler)");
        teamsEventHandlers = new ModelHandlerList<Team>("Tournament (teamsHandler)");
        seedingHandlers = new ModelHandlerList<List<TeamId>>("Tournament (seedingHandler)");
    }

    @Override
    protected void stateChanged() {
        //TODO
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> tournamentStages) {
        this.stages = tournamentStages;
    }

    @Override
    public HasLevelState getParent() {
        return null;
    }

    public LevelState getState() {
        return state;
    }

    public void replace(int index, Stage st) {
        getStages().remove(index);
        getStages().add(index, st);

    }

    public int getIndexOf(Stage st) {
        int index = 0;
        for (Stage stTemp : stages) {
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
        List<Stage> list = getStages();
        return list != null && !list.isEmpty();
    }

    public LevelState calculateState() {
        if (hasLevels()) {
            if (!hasTeams()) {
                return LevelState.notReady;
            }
            int countEmpty = 0;
            int countFinish = 0;
            for (Stage l : stages) {
                if (l.isFinished()) {
                    countFinish++;
                }
                else if (l.isNotReady()) {
                    countEmpty++;
                }
            }
            if (countFinish == stages.size()) {//all stages are finished
                return LevelState.finished;
            }
            else if (countEmpty == stages.size()) {//all stages are notReady
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



    public void createTeam(Long eventId, TeamId id, String name, Integer seeding, Boolean fromClient) {
        Team team = new Team(name, seeding);
        team.setId(id);
        team.setEventLogId(eventId);
        team.setSeeding(getNextSeeding());
        teams.add(team);
        teamsEventHandlers.fireEvent(new CreateModelEvent<Team>(fromClient, team));
        updateState(fromClient);
    }



    public void deleteTeam(TeamId id, boolean fromClient) {
        Team team = getTeam(id);
        if (team == null) {
            return;
        }
        teams.remove(team);
        updateSeeding(getTeamIds(), fromClient);
        teamsEventHandlers.fireEvent(new DeleteModelEvent<Team>(fromClient, team));
        updateState(fromClient);
    }

    public List<TeamId> getTeamIds(){
        List<TeamId> ids = new ArrayList<TeamId>();
        for (Team team : teams) {
            ids.add(team.getId());
        }
        return ids;
    }

    public Team getTeam(TeamId id) {
        for (Team team : teams) {
            if (team.getId().equals(id)) {
                return team;
            }
        }
        return null;
    }

    public Stage getLevel(StageId id) {
        for (Stage level : stages) {
            if (level.getId().equals(id)) {
                return level;
            }
        }
        return null;
    }

    private void clearAllLevels(boolean fromClient) {
        for (Stage l : stages) {
            l.clear(fromClient);
        }
    }

    public void createLevel(StageType type, StageId id, Long eventId, boolean fromClient) {
        Stage tl = TournamentLevelFac.create(this, type);
        tl.setId(id);
        tl.setEventLogId(eventId);
        stages.add(tl);
        stagesEventHandlers.fireEvent(new CreateModelEvent<Stage>(fromClient, tl));
        updateState(fromClient);
    }

    public void deleteLevel(StageId levelId, boolean fromClient) {
        Stage level = getLevel(levelId);
        if (level == null) {
            return;
        }
        stages.remove(level);
        stagesEventHandlers.fireEvent(new DeleteModelEvent<Stage>(fromClient, level));
        updateState(fromClient);
    }

    public Stage getStage(StageId id){
        for (Stage stage : stages) {
            if(stage.getId().equals(id)){
                return stage;
            }
        }
        return null;
    }

    public boolean updateLevelAllowed(Stage l) {
        return l.isNotReady();
    }

    public int getMaxNumberOfTeams(Stage tournamentStage) {
        int min = getTeams().size();
        for (Stage l : stages) {
            Integer maxTeams = l.getSettings().getMaxNumberOfTeams();
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
        return stages.size() - 1 == index;
    }



    public Match findMatch(MatchId id) {
        for (Stage stage : stages) {
            Match m = stage.findMatch(id);
            if(m != null){
                return m;
            }
        }
        return null;
    }

    public Stage getNextLevel(Stage current) {
        int index = getIndexOf(current);
        if (!isLastLevel(index)) {
            return stages.get(index + 1);
        }
        return null;
    }

    public Stage getPreviousLevel(Stage current) {
        int index = getIndexOf(current);
        if (index == 0) {
            return null;
        }
        return stages.get(index - 1);

    }

    public TournamentChannelId getTournamentChannelId() {
        return tournamentChannelId;
    }

    public void setTournamentChannelId(TournamentChannelId tournamentChannelId) {
        this.tournamentChannelId = tournamentChannelId;
    }

    public boolean isLastLevel(Stage current) {
        return CU.isLast(stages, current);
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

    public void updateSeeding(List<TeamId> seedingList, boolean fromClient) {
        List<TeamId> oldSeedingList = getSeedingList();
        changeTeamsAccordingToSeeding(seedingList);

        seedingHandlers.fireEvent(new UpdateModelEvent<List<TeamId>>(fromClient, oldSeedingList, seedingList));
    }

    private List<TeamId> getSeedingList() {
        List<TeamId> seedingList = new ArrayList<TeamId>();
        for (Team team : teams) {
            seedingList.add(team.getId());
        }
        return seedingList;
    }

    private void changeTeamsAccordingToSeeding(List<TeamId> seedingList) {
        Map<TeamId, Team> map = new HashMap<TeamId, Team>();
        List<Team> newlyCreatedTeams = new ArrayList<Team>();
        int seedingSize = seedingList.size();
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
        for (TeamId seeding : seedingList) {
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

    public Stage getLevelInPogress() {
        if(state.isInProgress()){
            for (Stage tournamentStage : getStages()) {
                if(tournamentStage.isInProgress()){
                    return tournamentStage;
                }
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "Tournament{" +
                "\n  subtournaments=" + StringUtil.toString(stages) +
                "  Teams=" + StringUtil.toString(teams) +
                "}\n";
    }
}



