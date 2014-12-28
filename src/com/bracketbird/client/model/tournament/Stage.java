package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.LevelStateModel;
import com.bracketbird.client.model.SeedingTeam;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.event.UpdateDispatcher;
import com.bracketbird.client.model.keys.MatchId;
import com.bracketbird.client.model.keys.StageId;
import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.rtc.event.UpdateMatchFieldEvent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class Stage extends LevelStateModel<StageId> {

    //each round holds all the matches in one round (from all groups).
    protected List<Round> rounds = new ArrayList<Round>();
    private StageSettings settings = new StageSettings();

    protected List<Team> startingTeams = new ArrayList<Team>();
    protected List<List<Team>> endingTeams = new ArrayList<List<Team>>();

    public UpdateDispatcher<StageSettings> updateSettingsDispatcher = new UpdateDispatcher<>();

    protected Tournament tournament;


    protected Stage(Tournament tournament) {
        this.tournament = tournament;
    }

    public List<Team> getStartingTeams() {
        return startingTeams;
    }

    public void setStartingTeams(List<Team> startingTeams) {
        this.startingTeams = startingTeams;
    }

    protected void clear() {
        clearTeams();
        clearMatches();
    }

    public void clear(boolean fromClient){
        clear();
        updateState(fromClient);
    }

    private void clearTeams() {
        startingTeams = new ArrayList<Team>();
        endingTeams = new ArrayList<List<Team>>();
    }

    public void clearMatches() {
        rounds = new ArrayList<Round>();
    }


    public abstract void layoutMatches(boolean fromClient);

    protected void setupStartingTeams() {
        Stage previous = getTournament().getPreviousStage(this);
        //with first level playing teams are taken from tournament
        if (previous == null) {
            this.startingTeams = new SeedingHelper().seed(getTournament().getTeams());
        }
        else {
            //not updateEndingTeams
            if (!previous.isFinished()) {
                this.startingTeams = createSeedingTeams();
            }
            //updateEndingTeams
            else {
                List<Team> startTeams = new ArrayList<Team>();
                for (List<Team> teams : previous.getEndingTeams()) {
                    startTeams.addAll(teams);
                }
                Integer maxNumberOfTeams = getSettings().getMaxNumberOfTeams();
                //remove last item until max number of teams is satisfied.
                if (maxNumberOfTeams != null) {
                    while (startTeams.size() > maxNumberOfTeams) {
                        startTeams.remove(startTeams.size() - 1);
                    }
                }
                this.startingTeams = startTeams;
            }
        }
    }


    public void updateEndingTeams(List<TeamId[]> finalRank, boolean fromClient) {
        List<List<Team>> teams = new ArrayList<List<Team>>();
        for (TeamId[] teamIds : finalRank) {
            List<Team> tempTeams = new ArrayList<Team>();
            for (TeamId id : teamIds) {
                tempTeams.add(getTeam(id));
            }
            teams.add(tempTeams);
        }
        this.endingTeams = teams;
        updateState(fromClient);
    }


    protected List<Team> createSeedingTeams() {
        List<Team> list = new ArrayList<Team>();
        int seed = 1;

        Integer numberOfTeams = getMaxNumberOfTeams();
        int i = 0;
        while (i < numberOfTeams) {
            list.add(new SeedingTeam(seed++));
            i++;
        }
        return list;
    }


    protected boolean shouldResetNextLevel(LevelState oldState, LevelState newState) {
        if (oldState.isFinished() || oldState.isDonePlaying()) {
            if (!oldState.equals(newState)) {
                return true;
            }
        }
        return false;
    }


    protected boolean hasEndingTeams() {
        return !getEndingTeams().isEmpty();
    }

    public List<Match> getMatches() {
        List<Match> list = new ArrayList<Match>();
        for (Round r : getRounds()) {
            list.addAll(r.getMatches());
        }
        return list;
    }

    public boolean hasMatches() {
        if (rounds == null || rounds.isEmpty()) {
            return false;
        }
        for (Round round : rounds) {
            if (!round.getMatches().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void setSettings(StageSettings ss) {
        this.settings = ss;
    }

    public void updateSettings(StageSettings stageSettings, boolean fromClient) {
        StageSettings oldSettings = this.settings;
        this.settings = stageSettings;
        clear();
        updateSettingsDispatcher.fireEvent(oldSettings, settings, fromClient);
        updateState(fromClient);
    }


    public abstract String getName();

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }


    public int getMaxNumberOfTeams() {
        return getTournament().getMaxNumberOfTeams(this);
    }

    public List<List<Team>> getEndingTeams() {
        return endingTeams;
    }


    public boolean updateMatchResult(MatchId id, int[] homeScores, int[] outScores, boolean fromClient) {
        Match m = findMatch(id);

        if (m != null) {
            m.updateResult(homeScores, outScores, fromClient);
        }
        return m != null;
    }


    protected void handleWalkover(Match m) {
    }

    protected Match findMatch(MatchId id) {
        for (Match match : getMatches()) {
            if (match.getId().equals(id)) {
                return match;
            }
        }
        return null;
    }

    public boolean updateMatchField(UpdateMatchFieldEvent event) {
        for (Match match : getMatches()) {
            if (match.getId().equals(event.getModelId())) {
                match.updateField(event.getField(), event.isFromClient());
                return true;
            }
        }
        return false;
    }


    public Team getTeam(TeamId id) {
        for (Team team : startingTeams) {
            if (id.equals(team.getId())) {
                return team;
            }
        }
        return null;
    }

    public LevelState getState() {
        return state;
    }

    public boolean isKnockoutStage() {
        return this instanceof KnockoutStage;
    }

    public boolean isGroupStage(){
        return this instanceof GroupStage;
    }

    public StageSettings getSettings() {
        return settings;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public abstract String getRoundName(Round round);
}