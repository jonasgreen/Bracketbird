package com.bracketbird.client.model.tournament;

import com.bracketbird.client.gui.rtc.event.StateChangedEvent;
import com.bracketbird.client.model.keys.RoundId;
import com.bracketbird.clientcore.model.StateModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class Round extends StateModel<RoundId> {
    private static final long serialVersionUID = -990956812294369030L;

    private int roundNumber;
    protected List<Match> matches = new ArrayList<Match>();
    protected Stage stage;

    public Round(Stage stage, int roundNo) {
        this.stage = stage;
        addStateHandler(stage);
        this.roundNumber = roundNo;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public String getName() {
        return roundNumber + ". round";
    }

    public void initState() {
        this.state = calculateState();
    }

    public List<Match> getMatches() {
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
    public void updateState(boolean fromClient) {
        LevelState newState = calculateState();
        setNewState(newState, fromClient);
    }

    public LevelState calculateState() {
        return new LevelStateCalculator().stateBasedOnChildren(getMatches());
    }

    //called from a child (match)
    @Override
    public void onChange(StateChangedEvent event) {

    }

    public Stage getStage() {
        return stage;
    }
}
