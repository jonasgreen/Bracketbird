package com.bracketbird.client.model.tournament;

import com.bracketbird.client.gui.rtc.event.ModelHandlerList;
import com.bracketbird.client.gui.rtc.event.UpdateMatchFieldEvent;
import com.bracketbird.client.gui.rtc.event.UpdateModelEvent;
import com.bracketbird.client.model.SeedingTeam;
import com.bracketbird.client.model.StageType;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.MatchId;
import com.bracketbird.client.model.keys.StageId;
import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.clientcore.model.PlayableModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public abstract class Stage extends PlayableModel<StageId> implements HasLevelState {
    private static final long serialVersionUID = -8838821453128489654L;

    private StageType type;

    protected Tournament tournament;
    public ModelHandlerList<StageSettings> settingsHandler;

    //each round holds all the matches in one round (from all groups).
    protected List<StageRound> rounds = new ArrayList<StageRound>();
    private StageSettings settings = new StageSettings();

    protected List<Team> startingTeams = new ArrayList<Team>();
    protected List<Team[]> endingTeams = new ArrayList<Team[]>();

    protected Stage() {
    }


    protected Stage(Tournament tournament, StageType type) {
        this.type = type;
        this.tournament = tournament;

        settingsHandler = new ModelHandlerList<StageSettings>("Stage " + type.getLevelName() + " (settingshandler)");

    }

    public List<Team> getStartingTeams() {
        return startingTeams;
    }

    public void setStartingTeams(List<Team> startingTeams) {
        this.startingTeams = startingTeams;
    }

    public void clear(boolean fromClient) {
        clearTeams();
        clearMatches();
        updateState(fromClient);
    }

    private void clearTeams() {
        startingTeams = new ArrayList<Team>();
        endingTeams = new ArrayList<Team[]>();
    }

    public void clearMatches() {
        rounds = new ArrayList<StageRound>();
    }

    public StageType getType() {
        return type;
    }

    public abstract void layoutMatches(boolean fromClient);

    protected void setupStartingTeams() {
        Stage previous = tournament.getPreviousLevel(this);
        //with first level playing teams are taken from tournament
        if (previous == null) {
            this.startingTeams = new SeedingHelper().seed(getTournament().getTeams());
        }
        else {
            //not finished
            if (!previous.isFinished()) {
                this.startingTeams = createSeedingTeams();
            }
            //finished
            else {
                List<Team> startTeams = new ArrayList<Team>();
                for (Team[] teams : previous.getEndingTeams()) {
                    Collections.addAll(startTeams, teams);
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


    public void finished(List<TeamId[]> finalRank, boolean fromClient) {
        List<Team[]> teams = new ArrayList<Team[]>();
        for (TeamId[] teamIds : finalRank) {
            Team[] tempTeams = new Team[teamIds.length];
            int i = 0;
            for (TeamId id : teamIds) {
                tempTeams[i++] = getTeam(id);
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

    public LevelState calculateState() {
        if (hasEndingTeams()) {
            return LevelState.finished;
        }
        return calculateState(rounds);
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


    @Override
    public String toString() {
        return "Subtournament{" +
                ", state=" + state +
                ", matches=" + getMatches() +
                ", teams=" + startingTeams +
                "}";
    }

    public void setSettings(StageSettings ss) {
        this.settings = ss;
    }

    public void updateSettings(StageSettings stageSettings, boolean fromClient) {
        StageSettings oldSettings = settings;
        this.settings = stageSettings;

        settingsHandler.fireEvent(new UpdateModelEvent<StageSettings>(fromClient, oldSettings, settings));
    }


    public String getName() {
        return type.getLevelName();
    }

    public List<StageRound> getRounds() {
        return rounds;
    }

    public void setRounds(List<StageRound> rounds) {
        this.rounds = rounds;
    }


    public int getMaxNumberOfTeams() {
        return tournament.getMaxNumberOfTeams(this);
    }


    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public List<Team[]> getEndingTeams() {
        return endingTeams;
    }

    public void setEndingTeams(List<Team[]> endingTeams) {
        this.endingTeams = endingTeams;
    }


    public TournamentLevelVO createVO() {
        return getSettings().createVO(this);
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

    public boolean isKnockout() {
        return this instanceof KnockoutStage;
    }

    public StageSettings getSettings() {
        return settings;
    }

    @Override
    public Tournament getParent() {
        return tournament;
    }

    @Override
    protected void stateChanged() {
        if (getState().equals(LevelState.donePlaying)) {
            //TODO set ending teams
        }

        if (state.isBelow(LevelState.finished)) {
            endingTeams = new ArrayList<Team[]>();
        }
    }
}