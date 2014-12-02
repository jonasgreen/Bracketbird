package com.bracketbird.client.model.tournament;

import java.util.List;

/**
 *  Represent a round at Stage level - when multiple groups occurs, this will contain matches from
 *  different groups (mix of GroupRounds).
 */
public class GroupStageRound extends StageRound {
    private static final long serialVersionUID = 7856387235373392743L;



    public GroupStageRound(GroupStage stage, int roundNo) {
        super(stage, roundNo);
    }


    public void addMatches(List<Match> matches) {
        for (Match m : matches) {
            getMatches().add(m);
            //When a group match changes state - the stageRound and the groupRound has to be notified.
            m.addStateHandler(this);
        }

    }

}
