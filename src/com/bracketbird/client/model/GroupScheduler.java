package com.bracketbird.client.model;


import com.bracketbird.client.model.tournament.*;

import java.util.*;

/*

Scheduling mechanism:

Even number of teams
1 2 --> 1 3 --> 1 4
3 4     4 2     2 3


Odd number of teams
1 2 --> 3 1 --> 5 3 --> 4 5 --> 2 4
3 4     5 2     4 1     2 3     1 5
5       4       2       1       3

*/

public class GroupScheduler extends Scheduler<GroupRound>{
    private List<GroupRound> rounds = new ArrayList<GroupRound>();
    private GroupStage stage;

    public GroupScheduler(GroupStage stage, List<Group> groups) {
        this.stage = stage;
        build(groups);
    }

    public List<GroupRound> getRounds() {
        return rounds;
    }

    private void build(List<Group> groups) {
        for (Group group : groups) {
            appendRounds(new AGroupScheduler(group, stage).getRounds());
        }

        int matchNumber = 1;
        for (Round round : rounds) {
            for (Match m : round.getMatches()) {
                m.setName(""+matchNumber++);
            }
        }
    }

    private void appendRounds(List<Round> newRounds) {
        int roundNumber = 0;
        for (Round r : newRounds) {
            if(rounds.size()-1 >= roundNumber){//matches are added to existing round
                rounds.get(roundNumber).getMatches().addAll(r.getMatches());
            }
            else{//no round exist - round is added
                GroupRound gr = new GroupRound(stage, r.getRoundNumber());
                gr.getMatches().addAll(r.getMatches());
                rounds.add(gr);
            }
            roundNumber++;
        }

    }


}
