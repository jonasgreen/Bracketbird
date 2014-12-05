package com.bracketbird.client.model;


import com.bracketbird.client.model.tournament.*;

import java.util.*;


public class GroupStageRoundsFactory {
    private List<Round> rounds = new ArrayList<Round>();

    public GroupStageRoundsFactory(List<Group> groups) {
        build(groups);
    }

    public List<Round> getRounds() {
        return rounds;
    }

    private void build(List<Group> groups) {
        for (Group group : groups) {
            appendRounds(group.getRounds());
        }

        int matchNumber = 1;
        for (Round round : rounds) {
            for (Match m : round.getMatches()) {
                m.setMatchNo(matchNumber++);
            }
        }
    }

    private void appendRounds(List<Round> newRounds) {
        int roundNumber = 0;
        for (Round r : newRounds) {
            if(rounds.size()-1 >= roundNumber){//matches are added to existing round
                Round stageRound = rounds.get(roundNumber);
                stageRound.addMatches(r.getMatches());
            }
            else{//no round exist - round is added

                //no model listens for state change in group-stage rounds. A stage listens for group or knockout rounds.
                Round gr = new Round(r.getStage(), r.getRoundNumber());
                gr.addMatches(r.getMatches());
                gr.initState();
                rounds.add(gr);

                //let stage round listen for changes in matches
                for (Match match : r.getMatches()) {
                    match.addStateHandler(gr);
                }

            }
            roundNumber++;
        }

    }


}
