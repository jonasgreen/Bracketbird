package com.bracketbird.client.model;


import com.bracketbird.client.model.tournament.*;

import java.util.ArrayList;
import java.util.List;

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

public class AGroupScheduler {


    private int matchesPrRound;
    private int numberOfMatches;
    private int numberOfRounds;

    private boolean oddNumberOfTeams;
    private List<Round> rounds;
    private List<Team> teams;
    private GroupStage stage;
    private int matchNumber = 1;
    private Group group;

    public AGroupScheduler(Group ag, GroupStage stage) {
        this.group = ag;
        this.teams = ag.getTeams();
        this.stage = stage;
        numberOfMatches = (teams.size() * (teams.size() - 1)) / 2;
        matchesPrRound = teams.size() / 2;
        numberOfRounds = numberOfMatches / matchesPrRound;
        oddNumberOfTeams = (teams.size() == 3) || teams.size() % matchesPrRound == 1;

        rounds = build();
        ag.setRounds(rounds);
    }

    public List<Round> getRounds() {
        return rounds;
    }

    private List<Round> build() {
        List<Round> grRounds = new ArrayList<Round>();
        List<Team> upper = new ArrayList<Team>();
        List<Team> lower = new ArrayList<Team>();

        int i = 0;
        for (Team pt : teams) {
            if (i < matchesPrRound) {
                upper.add(pt);
            }
            else {
                lower.add(pt);
            }
            i++;
        }

        int count = 0;
        while (count < numberOfRounds) {
            grRounds.add(buildRound(upper, lower, count + 1));
            rotate(upper, lower);
            count++;
        }
        return grRounds;
    }

    private void rotate(List<Team> upper, List<Team> lower) {
        if (oddNumberOfTeams) {
            upper.add(0, lower.remove(0));
            lower.add(upper.remove(upper.size() - 1));
        }
        else {
            upper.add(1, lower.remove(0));
            lower.add(upper.remove(upper.size() - 1));
        }
    }

    private Round buildRound(List<Team> upper, List<Team> lower, int roundNumber) {
        GroupRound group = new GroupRound(stage, roundNumber);
        List<Match> matches = new ArrayList<Match>();

        int count = 0;
        for (Team teamUp : upper) {
            Match m = MatchFac.createGroupMatch(group, matchNumber, teamUp, lower.get(count++));
            matches.add(m);
        }

        group.setMatches(matches);
        return group;
    }


    public String toString() {
        return "GroupScheduler{" +
                "\nnumberOfRounds=" + numberOfRounds +
                "\nnumberOfMatches=" + numberOfMatches +
                "\nmatchesPrRound=" + matchesPrRound +
                "\nrounds=" + rounds +

                '}';
    }
}
