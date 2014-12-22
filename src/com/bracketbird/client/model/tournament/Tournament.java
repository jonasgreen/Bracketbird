package com.bracketbird.client.model.tournament;


import com.bracketbird.client.model.LevelStateModel;
import com.bracketbird.client.model.StageType;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.event.CreateDeleteDispatcher;
import com.bracketbird.client.model.event.UpdateDispatcher;
import com.bracketbird.client.model.event.UpdateEvent;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.util.CU;
import com.bracketbird.client.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Tournament extends LevelStateModel<TournamentId> {

    public transient UpdateDispatcher<String> nameDispatcher = new UpdateDispatcher<>();
    public transient CreateDeleteDispatcher<Stage> stagesDispatcher = new CreateDeleteDispatcher<>();
    public transient CreateDeleteDispatcher<Team> teamsDispatcher = new CreateDeleteDispatcher<>();
    public transient UpdateDispatcher<List<TeamId>> seedingDispatcher = new UpdateDispatcher<>();

    private String name;
    private String url;
    private String viewUrl;
    private TournamentChannelId tournamentChannelId;
    private boolean viewOnly;

    private transient List<Stage> stages = new ArrayList<Stage>();
    private transient List<Team> teams = new ArrayList<Team>();
    private transient List<List<Team>> endingTeams = new ArrayList<List<Team>>();


    public Tournament() {
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> tournamentStages) {
        this.stages = tournamentStages;
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
        nameDispatcher.fireEvent(oldName, name, fromClient);
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
        LevelState childrenState = new LevelStateCalculator().stateBasedOnChildren(getStages());

        if(childrenState.isFinished()){
            endingTeams = getLastStage().getEndingTeams();
        }
        return childrenState;


    }

    private Stage getLastStage() {
        return getStages().get(getStages().size()-1);
    }


    public void createTeam(Long eventId, TeamId id, String name, Integer seeding, Boolean fromClient) {
        Team team = new Team(name, seeding);
        team.setId(id);
        team.setEventLogId(eventId);
        team.setSeeding(getNextSeeding());
        teams.add(team);
        teamsDispatcher.fireCreateEvent(team, fromClient);
        updateState(fromClient);
    }


    public void deleteTeam(TeamId id, boolean fromClient) {
        Team team = getTeam(id);
        if (team == null) {
            return;
        }
        teams.remove(team);
        updateSeeding(getTeamIds(), fromClient);
        teamsDispatcher.fireDeleteEvent(team, fromClient);

        clearAllStages(fromClient);
        updateState(fromClient);
    }

    private void clearAllStages(boolean fromClient) {
        for (Stage stage : stages) {
            stage.clear(fromClient);
        }
    }

    public List<TeamId> getTeamIds() {
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


    public void createStage(StageType type, StageId id, Long eventId, boolean fromClient) {
        Stage stage = TournamentLevelFac.create(this, type);
        stage.setId(id);
        stage.setEventLogId(eventId);
        stages.add(stage);
        stagesDispatcher.fireCreateEvent(stage, fromClient);
        updateState(fromClient);
    }

    public void deleteLevel(StageId id, boolean fromClient) {
        Stage stage = getStage(id);
        if (stage == null) {
            return;
        }
        stages.remove(stage);
        stagesDispatcher.fireDeleteEvent(stage, fromClient);
        updateState(fromClient);
    }

    public Stage getStage(StageId id) {
        for (Stage stage : stages) {
            if (stage.getId().equals(id)) {
                return stage;
            }
        }
        return null;
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
            if (m != null) {
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

    public Stage getPreviousStage(Stage current) {
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

        seedingDispatcher.fireEvent(oldSeedingList, seedingList, fromClient);
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
        if (state.isInProgress()) {
            for (Stage tournamentStage : getStages()) {
                if (tournamentStage.isInProgress()) {
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

    public void updateStage(StageId stageId, StageSettings stageSettings, boolean isFromClient) {
        Stage stage = getStage(stageId);
        if (stage == null) {
            return;
        }
        //clear following stages
        clearFollowingStages(isFromClient, stage);
        stage.updateSettings(stageSettings, isFromClient);
    }

    private void clearFollowingStages(boolean isFromClient, Stage stage) {
        if (getStages().size() > 1) {
            int next = getIndexOf(stage) + 1;
            while (next < getStages().size() - 1) {
                Stage nextStage = getStages().get(next++);
                nextStage.clear(isFromClient);
            }
        }
    }

    public void updateMatchResult(MatchId matchId, int[] homeScores, int[] outScores, boolean fromClient) {
        Match match = RTC.getInstance().getTournament().findMatch(matchId);
        if(match == null) {
            return;
        }
        clearFollowingStages(fromClient, match.getRound().getStage());
        match.updateResult(homeScores, outScores, fromClient);
    }

    public Group getGroup(GroupId modelId) {
        for (Stage stage : stages) {
            if(stage.isGroupStage()){
                Group g = ((GroupStage) stage).getGroup(modelId);
                if(g != null){
                    return g;
                }
            }
        }
        return null;
    }

    @Override
    public void onUpdate(UpdateEvent<LevelState> event) {
        endingTeams = new ArrayList<List<Team>>();
        updateState(event.isFromClient());
    }

    public Stage getStage(int index) {
        return getStages().get(index);
    }
}



