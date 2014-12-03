package com.bracketbird.client.model.tournament;

import com.bracketbird.client.gui.rtc.event.StateChangedEvent;
import com.bracketbird.client.model.keys.RoundId;
import com.bracketbird.clientcore.model.StateModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Round extends StateModel<RoundId> {
    private static final long serialVersionUID = -990956812294369030L;

    private int roundNumber;
    protected List<Match> matches = new ArrayList<Match>();
    protected Stage stage;

    public Round(Stage stage, int roundNo) {
        this.stage = stage;
        this.roundNumber = roundNo;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public String getName() {
        return stage.getRoundName(this);
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
        return "\nRound{" +
                "matches=" + getMatches() +
                '}';
    }

    public int indexOf(Match m) {
        return getMatches().indexOf(m);
    }

    public Match getMatch(int index){
        return matches.get(index);
    }

    @Override
    public void updateState(boolean fromClient) {
        LevelState newState = calculateState();
        setNewState(newState, fromClient);
    }

    public LevelState calculateState() {
        return new LevelStateCalculator().stateBasedOnChildren(getMatches());
    }

    @Override
    protected LevelState stateChanged(LevelState oldState, LevelState newState) {
        return null;
    }

    //called from a child (match)
    @Override
    public void onChange(StateChangedEvent event) {

    }

    public Stage getStage() {
        return stage;
    }

    public List<Match> getFinishedMatches() {
        List<Match> finished = new ArrayList<Match>();
        for (Match m : getMatches()) {
            if(m.isFinish()){
                finished.add(m);
            }
        }
        return finished;
    }



    public void addMatches(List<Match> matches) {
        for (Match m : matches) {
            getMatches().add(m);
            //When a group match changes state - the stageRound and the groupRound has to be notified.
            m.addStateHandler(this);
        }

    }
}
