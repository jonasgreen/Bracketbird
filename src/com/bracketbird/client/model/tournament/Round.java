package com.bracketbird.client.model.tournament;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public abstract class Round implements HasLevelState, Serializable {
    private static final long serialVersionUID = -990956812294369030L;

    private int roundNumber;
    private TournamentStage stage;
    private List<Match> matches;
    private LevelState state = LevelState.notReady;

    public Round(TournamentStage stage, int roundNo) {
        this.stage = stage;
        this.roundNumber = roundNo;
    }

    public TournamentStage getStage() {
        return stage;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public String getName() {
        return roundNumber + ". round";
    }

    public List<Match> getMatches(){
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "\nGroupRound{" +
                "matches=" + getMatches() +
                '}';
    }

    public int indexOf(Match m) {
        return getMatches().indexOf(m);
    }


    @Override
    public LevelState getState() {
        return null;
    }

    public void childHasChangedState(boolean fromClient) {
        updateState(fromClient);
    }

    private void updateState(boolean fromClient) {
        LevelState newState = calculateState();
        if(this.state.equals(newState)){
            return;
        }

    }

    public LevelState calculateState() {
        LevelState lowestState = LevelState.finished;
        for (Match m : matches) {
            if(m.getState().lowerThan(lowestState)){
                lowestState = m.getState();
            }
        }
        return lowestState;
    }

    @Override
    public boolean isNotReady() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isInProgress() {
        return false;
    }

    @Override
    public boolean isDonePlaying() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }


    @Override
    public TournamentStage getParent() {
        return stage;
    }
}
