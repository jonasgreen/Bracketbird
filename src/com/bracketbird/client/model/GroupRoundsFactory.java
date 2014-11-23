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

public class GroupRoundsFactory {

    private int matchesPrRound;
    private int numberOfMatches;
    private int numberOfRounds;
    private boolean oddNumberOfTeams;

    private List<GroupRound> rounds;
    private int matchNumber = 1;
    private Group group;

    public GroupRoundsFactory(Group group) {
        this.group = group;

        List<Team> teams = group.getTeams();
        numberOfMatches = (teams.size() * (teams.size() - 1)) / 2;
        matchesPrRound = teams.size() / 2;
        numberOfRounds = numberOfMatches / matchesPrRound;
        oddNumberOfTeams = (teams.size() == 3) || teams.size() % matchesPrRound == 1;

        rounds = build();
    }

    public List<GroupRound> getRounds() {
        return rounds;
    }

    private List<GroupRound> build() {
        List<GroupRound> rounds = new ArrayList<GroupRound>();

        List<Team> upper = new ArrayList<Team>();
        List<Team> lower = new ArrayList<Team>();

        int i = 0;
        for (Team pt : group.getTeams()) {
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
            rounds.add(buildRound(upper, lower, count + 1));
            rotate(upper, lower);
            count++;
        }
        return rounds;
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

    private GroupRound buildRound(List<Team> upper, List<Team> lower, int roundNumber) {
        GroupRound round = new GroupRound(group, roundNumber);
        List<Match> matches = new ArrayList<Match>();

        int count = 0;
        for (Team teamUp : upper) {
            Match m = MatchFac.createGroupMatch(round, matchNumber, teamUp, lower.get(count++));
            matches.add(m);
        }

        round.setMatches(matches);
        round.initState();
        return round;
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
