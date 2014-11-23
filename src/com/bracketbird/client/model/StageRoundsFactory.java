package com.bracketbird.client.model;


import com.bracketbird.client.model.tournament.*;

import java.util.*;


public class StageRoundsFactory{
    private List<StageRound> rounds = new ArrayList<StageRound>();

    public StageRoundsFactory(List<Group> groups) {
        build(groups);
    }

    public List<StageRound> getRounds() {
        return rounds;
    }

    private void build(List<Group> groups) {
        for (Group group : groups) {
            appendRounds(group.getRounds());
        }

        int matchNumber = 1;
        for (Round round : rounds) {
            for (Match m : round.getMatches()) {
                m.setName(""+matchNumber++);
            }
        }
    }

    private void appendRounds(List<GroupRound> newRounds) {
        int roundNumber = 0;
        for (GroupRound r : newRounds) {
            if(rounds.size()-1 >= roundNumber){//matches are added to existing round
                rounds.get(roundNumber).addMatches(r.getMatches());
            }
            else{//no round exist - round is added
                StageRound gr = new StageRound(r.getStage(), r.getRoundNumber());
                gr.addMatches(r.getMatches());
                gr.initState();
                rounds.add(gr);
            }
            roundNumber++;
        }

    }


}
