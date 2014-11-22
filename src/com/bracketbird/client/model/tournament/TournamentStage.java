package com.bracketbird.client.model.tournament;

import com.bracketbird.client.gui.rtc.event.*;
import com.bracketbird.client.model.LevelType;
import com.bracketbird.client.model.Scheduler;
import com.bracketbird.client.model.SeedingTeam;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.model.keys.TournamentLevelId;
import com.bracketbird.clientcore.model.PlayableModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public abstract class TournamentStage extends PlayableModel<TournamentLevelId> implements HasLevelState {
    private static final long serialVersionUID = -8838821453128489654L;

    private LevelType type;

    protected Tournament tournament;
    private LevelState state = LevelState.notReady;
    public transient StateHandlerList stateHandlers = new StateHandlerList();

    //each round holds all the matches in one round (from all groups).
    protected List<Round> rounds = new ArrayList<Round>();
    private StageSettings stageSettings = new StageSettings();

    protected List<Team> startingTeams = new ArrayList<Team>();
    protected List<Team[]> endingTeams = new ArrayList<Team[]>();

    protected TournamentStage() {
    }


    protected TournamentStage(Tournament tournament, LevelType type) {
        this.type = type;
        this.tournament = tournament;
    }

    public abstract Scheduler getScheduler();

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
        rounds = new ArrayList<Round>();
    }

    public LevelType getType() {
        return type;
    }

    public void layoutMatches(boolean fromClient) {
        setupStartingTeams();
        //createGroupMatch matches
        Scheduler m = getScheduler();
        rounds = m.getRounds();
        handleWalkovers();

        updateState(fromClient);

    }

    protected void setupStartingTeams() {
        TournamentStage previous = tournament.getPreviousLevel(this);
        //with first level playing teams are taken from tournament
        if (previous == null) {
            //CU.sortByEventLogNr(getTournament().getTeams());
            //removing notReady teams
            Iterator<Team> iterator = getTournament().getTeams().iterator();
            while (iterator.hasNext()) {
                Team t = iterator.next();
                if (t.getName() == null || t.getName().equals("")) {
                    //    iterator.remove();TODO
                    // TeamsPageController.getInstance().getPage().getTeamsTable().teamDeleted(t, false);
                }
            }

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
                Integer maxNumberOfTeams = getStageSettings().getMaxNumberOfTeams();
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


    public void finished(LevelFinishedEvent event) {
        List<Team[]> teams = new ArrayList<Team[]>();
        for (TeamId[] teamIds : event.getFinalRank()) {
            Team[] tempTeams = new Team[teamIds.length];
            int i = 0;
            for (TeamId id : teamIds) {
                tempTeams[i++] = getTeam(id);
            }
            teams.add(tempTeams);
        }
        this.endingTeams = teams;
        updateState(event.isFromClient());
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
        if (hasMatches()) {
            boolean someIsNonFinished = false;
            boolean someIsFinished = false;

            List<Match> matches = getMatches();

            for (Match match : matches) {
                //matches that are walkovers should be treated first
                if (match.isWalkover()) {
                    //ignore
                }
                else if (match.isFinish()) {
                    someIsFinished = true;
                }
                else {
                    someIsNonFinished = true;
                }
            }
            //all matches are finished or is walkover - ending teams had not been set though
            if (!someIsNonFinished) {
                //if(isKnockout()){
                //  return new LevelStateInFinished();
                //}
                return LevelState.donePlaying;
            }
            //if non is finished - matches is layed out.
            else if (!someIsFinished) {
                return LevelState.ready;
            }
            else {
                return LevelState.inProgress;
            }
        }
        else {//Not Ready
            return LevelState.notReady;
        }
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

    public void setStageSettings(StageSettings ss) {
        this.stageSettings = ss;
    }

    public void updateStageSettings(UpdateLevelEvent event) {
        this.stageSettings = event.getStageSettings();
        fireEvent(event);
        clear(event.isFromClient());
    }


    public String getName() {
        return type.getLevelName();
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
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
        return getStageSettings().createVO(this);
    }



    public boolean updateMatchResult(UpdateMatchResultEvent event) {
        Match m = findMatch(event);

        if (m != null) {
            if (hasEndingTeams()) {
                endingTeams = new ArrayList<Team[]>();
            }

            m.updateResult(event.getHomeScores(), event.getOutScores(), event.isFromClient());
            updateState(true);
        }
        return m != null;
    }


    protected void handleWalkover(Match m) {
    }

    @Override
    public void childHasChangedState(boolean fromClient) {

    }

    protected Match findMatch(UpdateMatchResultEvent event) {
        for (Match match : getMatches()) {
            if (match.getId().equals(event.getModelId())) {
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

    public void handleWalkovers() {

    }

    public boolean isKnockout() {
        return this instanceof KnockoutStage;
    }

    public StageSettings getStageSettings() {
        return stageSettings;
    }

    @Override
    public Tournament getParent() {
        return tournament;
    }
}