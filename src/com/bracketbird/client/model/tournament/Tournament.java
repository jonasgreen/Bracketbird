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
public class Tournament extends Model<TournamentId> {
    private static final long serialVersionUID = 5951730875833184507L;

    private transient List<TournamentListener<TournamentStateChangedEvent>> stateListener = new ArrayList<TournamentListener<TournamentStateChangedEvent>>();
    private transient List<TournamentListener<TournamentNameChangedEvent>> nameListener = new ArrayList<TournamentListener<TournamentNameChangedEvent>>();
    private transient List<TournamentListener<TournamentLevelEvent>> levelListener = new ArrayList<TournamentListener<TournamentLevelEvent>>();
    private transient List<TournamentListener<TournamentTeamEvent>> teamListener = new ArrayList<TournamentListener<TournamentTeamEvent>>();
    private transient List<TournamentListener<SeedingChangedEvent>> seedingListener = new ArrayList<TournamentListener<SeedingChangedEvent>>();

    private transient TournamentState state = new NotReady();

    private String name;
    private String url;
    private String viewUrl;
    private TournamentChannelId tournamentChannelId;
    private boolean viewOnly;

    private List<TournamentLevel> levels = new ArrayList<TournamentLevel>();
    private List<Team> teams = new ArrayList<Team>();

    private String password;
    private Date createdDate;

    private Date lastChangeDate;


    public Tournament() {
    }

    public List<TournamentLevel> getLevels() {
        return levels;
    }

    public void setLevels(List<TournamentLevel> tournamentLevels) {
        this.levels = tournamentLevels;
    }

    public TournamentState getState() {
        return state;
    }

    public void setState(TournamentState state) {
        this.state = state;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }


    public void replace(int index, TournamentLevel st) {
        getLevels().remove(index);
        getLevels().add(index, st);

    }

    public int getIndexOf(TournamentLevel st) {
        int index = 0;
        for (TournamentLevel stTemp : levels) {
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
        this.name = name;
        fireNameChanged(new TournamentNameChangedEvent(name, fromClient));

    }


    public boolean isSettingUp() {
        return state instanceof NotReady || state instanceof Ready;
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
        List<TournamentLevel> list = getLevels();
        return list != null && !list.isEmpty();
    }

    public void updateState(boolean fromClient) {
        TournamentState newState = calculateState();
        if (newState.equals(state)) {
            return;
        }
        TournamentStateChangedEvent event = new TournamentStateChangedEvent(state, newState, fromClient);
        state = newState;
        fireStateChanged(event);
    }

    private TournamentState calculateState() {
        if (hasLevels()) {
            if (!hasTeams()) {
                return new NotReady(false, true);
            }
            int countEmpty = 0;
            int countFinish = 0;
            for (TournamentLevel l : levels) {
                if (l.isFinish()) {
                    countFinish++;
                }
                else if (l.isEmpty()) {
                    countEmpty++;
                }
            }
            if (countFinish == levels.size()) {//all levels are finished
                return new Finished();
            }
            else if (countEmpty == levels.size()) {//all levels are empty
                return new Ready();
            }
            else {
                return new InProgress();
            }
        }
        else {
            return new NotReady(hasTeams(), false);
        }
    }


    public boolean isFinish() {
        return state instanceof Finished;
    }

    public boolean isNotReady() {
        return state instanceof NotReady;
    }

    public boolean isReady() {
        return state instanceof Ready;
    }


    @Override
    public String toString() {
        return "Tournament{" +
                "\n  subtournaments=" + StringUtil.toString(levels) +
                "  Teams=" + StringUtil.toString(teams) +
                "}\n";
    }

    public void createTeam(CreateTeamEvent event) {
        Team team = new Team();
        team.setId(event.getModelId());
        team.setEventLogId(event.getEventId());
        teams.add(team);
        fireTeamsChanged(new TournamentTeamEvent(event.isFromClient(), team, TournamentTeamEvent.Action.create));
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
        fireTeamsChanged(new TournamentTeamEvent(fromClient, team, TournamentTeamEvent.Action.delete));
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

    public TournamentLevel getLevel(TournamentLevelId id) {
        for (TournamentLevel level : levels) {
            if (level.getId().equals(id)) {
                return level;
            }
        }
        return null;
    }

    private void clearAllLevels(boolean fromClient) {
        for (TournamentLevel l : levels) {
            l.clear(false, fromClient);
        }
    }


    public boolean allowDeleteTeam() {
        return true;
    }

    public void createLevel(CreateLevelEvent event) {
        TournamentLevel tl = TournamentLevelFac.create(this, event.getLevelType());
        tl.setId(event.getModelId());
        levels.add(tl);
        fireLevelEvent(new TournamentLevelEvent(tl, TournamentLevelEvent.LevelAction.create, event.isFromClient()));
        updateState(event.isFromClient());
    }

    public void deleteLevel(TournamentLevelId levelId, boolean fromClient) {
        TournamentLevel level = getLevel(levelId);
        if (level == null) {
            return;
        }
        levels.remove(level);
        fireLevelEvent(new TournamentLevelEvent(level, TournamentLevelEvent.LevelAction.delete, fromClient));
        updateState(fromClient);
    }

    public void updateLevel(UpdateLevelEvent event) {
        TournamentLevel level = getLevel(event.getModelId());
        if (level == null) {
            return;
        }
        int index = levels.indexOf(level);
        levels.get(index).updateStageSettings(event);
        //clear the following
        index++;
        while (index < levels.size()) {
            levels.get(index).clear(false, event.isFromClient());
            index++;
        }
        updateState(event.isFromClient());
    }


    public boolean updateLevelAllowed(TournamentLevel l) {
        return l.isEmpty();
    }


    public Team getPlayingTeam(TeamId id) {
        for (Team team : teams) {
            if (team.getId().equals(id)) {
                return team;
            }
        }
        return null;
    }


    //LISTENERS

    public void addStateListener(TournamentListener<TournamentStateChangedEvent> l) {
        stateListener.add(l);
    }

    public void addLevelListener(TournamentListener<TournamentLevelEvent> l) {
        levelListener.add(l);
    }


    public void addNameListener(TournamentListener<TournamentNameChangedEvent> l) {
        nameListener.add(l);
    }


    public void addTeamsListener(TournamentListener<TournamentTeamEvent> l) {
        teamListener.add(l);
    }

    public void addSeedingListener(TournamentListener<SeedingChangedEvent> l) {
        seedingListener.add(l);
    }

    private void fireSeedingChanged(SeedingChangedEvent e) {
        for (TournamentListener<SeedingChangedEvent> l : seedingListener) {
            l.onChange(e);
        }
    }

    private void fireTeamsChanged(TournamentTeamEvent event) {
        for (TournamentListener<TournamentTeamEvent> listener : teamListener) {
            listener.onChange(event);
        }
    }


    private void fireStateChanged(TournamentStateChangedEvent event) {
        for (TournamentListener<TournamentStateChangedEvent> listener : stateListener) {
            listener.onChange(event);
        }
    }

    private void fireLevelEvent(TournamentLevelEvent event) {
        for (TournamentListener<TournamentLevelEvent> listener : levelListener) {
            listener.onChange(event);
        }
    }


    private void fireNameChanged(TournamentNameChangedEvent event) {
        for (TournamentListener<TournamentNameChangedEvent> listener : nameListener) {
            listener.onChange(event);
        }
    }

    public int getMaxNumberOfTeams(TournamentLevel tournamentLevel) {
        int min = getTeams().size();
        for (TournamentLevel l : levels) {
            Integer maxTeams = l.getStageSettings().getMaxNumberOfTeams();
            if (maxTeams != null && maxTeams < min) {
                min = maxTeams;
            }
            if (l == tournamentLevel) {
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
        TournamentLevel level = getLevel(event.getModelId());
        if (level == null) {
            return;
        }
        level.layoutMatches(event.isFromClient());
    }

    public boolean layoutMatchesAllowed(TournamentLevel tl) {
        return CU.get(levels, tl).layoutMatchesAllowed();
    }


    public void updateMatchResult(UpdateMatchResultEvent event) {
        boolean matchUpdated = false;
        for (TournamentLevel level : levels) {
            if(!matchUpdated){
                matchUpdated = level.updateMatchResult(event);
            }
            else{
                level.clear(false, true);
            }
        }
        updateState(true);
    }

    public void updateMatchField(UpdateMatchFieldEvent event) {
        for (TournamentLevel level : levels) {
            if (level.updateMatchField(event)) {
                return;
            }
        }
    }


    public void finishedLevel(LevelFinishedEvent event) {
        TournamentLevel level = getLevel(event.getModelId());
        if (level == null) {
            return;
        }
        level.finished(event);
        updateState(event.isFromClient());
    }


    public TournamentLevel getNextLevel(TournamentLevel current) {
        int index = getIndexOf(current);
        if (!isLastLevel(index)) {
            return levels.get(index + 1);
        }
        return null;
    }

    public TournamentLevel getPreviousLevel(TournamentLevel current) {
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

    public boolean isLastLevel(TournamentLevel current) {
        return CU.isLast(levels, current);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        fireSeedingChanged(new SeedingChangedEvent(event.isFromClient(), event.getSeedings()));
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

    public TournamentLevel getLevelInPogress() {
        if(state instanceof InProgress){
            for (TournamentLevel tournamentLevel : getLevels()) {
                if(tournamentLevel.getState() instanceof LevelStateInProgress){
                    return tournamentLevel;
                }
            }
        }
        return null;
    }
}



