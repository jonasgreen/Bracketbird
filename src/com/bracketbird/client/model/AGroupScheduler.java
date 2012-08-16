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
    private int number = 1;
    private List<Team> teams;
    private TournamentLevel level;
    private int matchNumber = 1;
    private AGroup agroup;

    public AGroupScheduler(AGroup ag, TournamentLevel level) {
        this.agroup = ag;
        this.teams = ag.getTeams();
        this.level = level;
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
        GroupRound gr = new GroupRound(roundNumber + ". round");
        int count = 0;
        for (Team teamUp : upper) {
            Match m = MatchFac.create(level, agroup.getName(), roundNumber, matchNumber++, teamUp, lower.get(count++));
            m.setName("" + number++);
            gr.add(m);
        }
        return gr;
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
