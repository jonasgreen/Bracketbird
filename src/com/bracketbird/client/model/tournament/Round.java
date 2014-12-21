package com.bracketbird.client.model.tournament;

import com.bracketbird.client.rtc.event.StateChangedEvent;
import com.bracketbird.client.model.keys.RoundId;
import com.bracketbird.client.model.LevelStateModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Round extends LevelStateModel<RoundId> {

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

    public int indexOf(Match m) {
        return getMatches().indexOf(m);
    }

    public Match getMatch(int index){
        return matches.get(index);
    }

    public LevelState calculateState() {
        return new LevelStateCalculator().stateBasedOnChildren(getMatches());
    }

    //called from a child (match)
    @Override
    public void onChange(StateChangedEvent event) {
        updateState(event.isFromClient());
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
