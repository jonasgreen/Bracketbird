package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.keys.RoundId;
import com.bracketbird.clientcore.model.PlayableModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class Round extends PlayableModel<RoundId> {
    private static final long serialVersionUID = -990956812294369030L;

    private int roundNumber;
    protected Stage stage;
    protected List<Match> matches = new ArrayList<Match>();

    public Round(Stage stage, int roundNo) {
        this.stage = stage;
        this.roundNumber = roundNo;
    }

    public Stage getStage() {
        return stage;
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



    public LevelState calculateState() {
        return calculateState(matches);
    }



        @Override
    protected void stateChanged() {

    }
}
