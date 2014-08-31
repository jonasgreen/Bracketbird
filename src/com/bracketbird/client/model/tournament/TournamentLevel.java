package com.bracketbird.client.model.tournament;

import com.bracketbird.client.gui.rtc.event.*;
import com.bracketbird.client.model.Scheduler;
import com.bracketbird.client.model.SeedingTeam;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.client.model.keys.TournamentLevelId;
import com.bracketbird.clientcore.model.Model;
import com.bracketbird.clientcore.model.TournamentLevelConstant;

import java.util.*;

/**
 *
 */
public abstract class TournamentLevel extends Model<TournamentLevelId> {
    private static final long serialVersionUID = -8838821453128489654L;

    private transient List<TournamentListener<LevelStateEvent>> stateListener = new ArrayList<TournamentListener<LevelStateEvent>>();
    private transient List<TournamentListener<TournamentLevelEvent>> levelListener = new ArrayList<TournamentListener<TournamentLevelEvent>>();


    private TournamentLevelState state = new LevelStateEmpty();


    //each round holds all the mathces in one round (from all groups).
    protected List<Round> rounds = new ArrayList<Round>();

    private TournamentId tournamentId;
    private Integer type;
    protected Tournament tournament;


    private LevelSettings levelSettings = new LevelSettings();

    protected List<Team> startingTeams = new ArrayList<Team>();
    protected List<Team[]> endingTeams = new ArrayList<Team[]>();


    private Date createdDate;
    private Date lastChangeDate;


    public TournamentLevel() {
    }

    protected TournamentLevel(Tournament tournament) {
        this.tournament = tournament;
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

    public abstract Scheduler getScheduler();

    public List<Team> getStartingTeams() {
        return startingTeams;
    }

    public void setStartingTeams(List<Team> startingTeams) {
        this.startingTeams = startingTeams;
    }

    public TournamentId getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(TournamentId tournamentId) {
        this.tournamentId = tournamentId;
    }

    public void clear(boolean notifyParent, boolean fromClient) {
        clearTeams();
        clearMatches();
        updateState(notifyParent, fromClient);

    }

    private void clearTeams() {
        startingTeams = new ArrayList<Team>();
        endingTeams = new ArrayList<Team[]>();
    }

    public void clearMatches() {
        rounds = new ArrayList<Round>();
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public boolean isFinish() {
        return state instanceof LevelStateInFinished;
    }

    public void layoutMatches(boolean fromClient) {
        setupStartingTeams();
        //create matches
        Scheduler m = getScheduler();
        rounds = m.getRounds();
        handleWalkovers();

        updateState(true, fromClient);

    }

    protected void setupStartingTeams() {
        TournamentLevel previous = tournament.getPreviousLevel(this);
        //with first level playing teams are taken from tournament
        if (previous == null) {
            //CU.sortByEventLogNr(getTournament().getTeams());
            //removing empty teams
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
            if (!previous.isFinish()) {
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


    public boolean layoutMatchesAllowed() {
        return isEmpty();
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
        updateState(false, event.isFromClient());
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


    public boolean isInProgress() {
        return state instanceof LevelStateInProgress;
    }

    public boolean isMatchesLayedOut() {
        return state instanceof LevelStateMatchesLayedOut;
    }

    public boolean isEmpty() {
        return state instanceof LevelStateEmpty;
    }


    public void updateState(boolean notifyParent, boolean fromClient) {
        TournamentLevelState newState = calculateState();
        setNewState(newState, notifyParent, fromClient);
    }

    protected boolean shouldResetNextLevel(TournamentLevelState oldState, TournamentLevelState newState) {
        if (oldState instanceof LevelStateInFinished || oldState instanceof LevelStateAllMatchesPlayed) {
            if (!oldState.equals(newState)) {
                return true;
            }
        }
        return false;
    }

    private TournamentLevelState calculateState() {
        if (hasEndingTeams()) {
            return new LevelStateInFinished();
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
                //if(isCup()){
                //  return new LevelStateInFinished();
                //}
                return new LevelStateAllMatchesPlayed();
            }
            //if non is finished - matches is layed out.
            else if (!someIsFinished) {
                return new LevelStateMatchesLayedOut();
            }
            else {
                return new LevelStateInProgress();
            }
        }
        else {//Not Ready
            return new LevelStateEmpty();
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

    private void setNewState(TournamentLevelState newState, boolean notifyParent, boolean fromClient) {
        if (newState instanceof LevelStateMatchesLayedOut || !state.equals(newState)) {
            //listeners should always be notified about matches layed out state - no matter previous state
            LevelStateEvent event = new LevelStateEvent(this, state, newState, fromClient);
            state = newState;
            fireStateChanged(event);

            if (notifyParent) {
                tournament.updateState(fromClient);
            }

        }
    }


    @Override
    public String toString() {
        return "Subtournament{" +
                ", state=" + state +
                ", matches=" + getMatches() +
                ", teams=" + startingTeams +
                "}";
    }

    public void setStageSettings(LevelSettings ss) {
        this.levelSettings = ss;
    }

    public void updateStageSettings(UpdateLevelEvent event) {
        this.levelSettings = event.getLevelSettings();
        fireEvent(event);
        clear(true, event.isFromClient());
    }

    public LevelSettings getStageSettings() {
        return levelSettings;
    }

    public String getName() {
        return TournamentLevelConstant.get(type).getText();
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }


    private void fireStateChanged(LevelStateEvent e) {
        List<TournamentListener<LevelStateEvent>> list = new ArrayList<TournamentListener<LevelStateEvent>>(stateListener);
        for (TournamentListener<LevelStateEvent> l : list) {
            l.onChange(e);
        }
    }


    public void addStateListener(TournamentListener<LevelStateEvent> l) {
        stateListener.add(l);
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

    private void fireLevelEvent(TournamentLevelEvent event) {
        for (TournamentListener<TournamentLevelEvent> listener : levelListener) {
            listener.onChange(event);
        }
    }

    public void addLevelListener(TournamentListener<TournamentLevelEvent> l) {
        levelListener.add(l);
    }

    public boolean updateMatchResult(UpdateMatchResultEvent event) {
        Match m = findMatch(event);

        if (m != null) {
            if (hasEndingTeams()) {
                endingTeams = new ArrayList<Team[]>();
            }

            m.update(event);
            updateState(false, true);
        }
        return m != null ;
    }


    protected void handleWalkover(Match m) {
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
                match.update(event);
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

    public TournamentLevelState getState() {
        return state;
    }

    public void handleWalkovers() {

    }

    public boolean isCup() {
        return this instanceof Cup;
    }

}