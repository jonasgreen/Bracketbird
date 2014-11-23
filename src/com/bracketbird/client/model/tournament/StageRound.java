package com.bracketbird.client.model.tournament;


import java.util.List;

/**
 *  Represent a round at Stage level - when multiple groups occurs, this will contain matches from
 *  different groups (mix of GroupRounds).
 */
public class StageRound extends Round {
    private static final long serialVersionUID = 7856387235373392743L;


    public StageRound(Stage stage, int roundNumber) {
        super(stage, roundNumber);
    }


    @Override
    public Stage getParent() {
        return stage;
    }


    public void addMatches(List<Match> matches) {
        for (Match m : matches) {
            getMatches().add(m);
            ((GroupMatch)m).setStageRound(this);
        }

    }
}
