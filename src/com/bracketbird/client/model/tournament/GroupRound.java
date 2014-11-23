package com.bracketbird.client.model.tournament;



/**
 *  Represent the rounds in a Group
 */
public class GroupRound extends Round {
    private static final long serialVersionUID = 7856387235373392743L;

    private Group group;

    public GroupRound(Group group, int roundNumber) {
        super(group.getParent(), roundNumber);
        this.group = group;
    }


    @Override
    public Group getParent() {
        return group;
    }



}
