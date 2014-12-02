package com.bracketbird.client.model.tournament;


/**
 *  Represent the rounds in a Group
 */
public class GroupRound extends Round {
    private static final long serialVersionUID = 7856387235373392743L;


    public GroupRound(Group group, int roundNumber) {
        super(group.getStage(), roundNumber);
    }


    @Override
    protected LevelState stateChanged(LevelState oldState, LevelState newState) {
        return null;
    }


}
