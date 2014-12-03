package com.bracketbird.client.model;


import com.bracketbird.client.model.tournament.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class KnockoutRoundsBuilder {

    private static char[] charNames = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','X','Y','Z'};

    private List<Round> allRounds = new ArrayList<Round>();
    private List<Team> teams;
    private int matchNumber = 1;

    private KnockoutStage stage;

    public KnockoutRoundsBuilder(List<Team> teams, KnockoutStage stage) {
        this.teams = teams;
        this.stage = stage;
        build();
    }

    private void build() {
        //return the number of teams nessecary to fill cup at the specifik round
        int binaryTeamCount = getBinaryTeamCount();

        List<Team> bTeamList = createTeamList(binaryTeamCount);

        Round firstRound = new Round(stage, 1);

        List<KnockoutMatch> left = new ArrayList<KnockoutMatch>();
        List<KnockoutMatch> right = new ArrayList<KnockoutMatch>();


        left.add(createMatch(bTeamList, firstRound));
        List<KnockoutMatch> pointer = right;
        int count = 0;
        while (!bTeamList.isEmpty()){
            if(count == 2){
                count = 0;
                pointer = pointer == right ? left : right;
            }
            pointer.add(createMatch(bTeamList, firstRound));
            count++;
        }

        Collections.reverse(right);
        List<Match> allCupMatchesFirstRound = new ArrayList<Match>();
        allCupMatchesFirstRound.addAll(left);
        allCupMatchesFirstRound.addAll(right);

        firstRound.setMatches(allCupMatchesFirstRound);

        List<Round> rounds = new ArrayList<Round>();
        rounds.add(firstRound);

        int roundNumber = 2;
        Round tempRound = firstRound;
        while (tempRound.getMatches().size() > 1){
            tempRound = buildNextRound(tempRound, roundNumber++);
            rounds.add(tempRound);
        }

        allRounds = rounds;
        int start = rounds.size()-1;
        while (start > 0){
            appendChilds(rounds.get(start-1).getMatches(), rounds.get(start).getMatches());
            start--;
        }
        for (Round round : rounds) {
            round.initState();
        }
    }

    private void appendChilds(List<Match> parents, List<Match> childs){
        int childCount = 0;
        for (Match child : childs) {
            int parentIndex = childCount*2;
            ((KnockoutMatch)parents.get(parentIndex)).setParentMatch((KnockoutMatch) child);
            ((KnockoutMatch)parents.get(parentIndex+1)).setParentMatch((KnockoutMatch) child);
           childCount++;
        }
    }

    private Round buildNextRound(Round previousRound, int roundNumber) {
        List<Match> list = new ArrayList<Match>();
        Round round = new Round(stage, roundNumber);
        char c = charNames[roundNumber];
        int nameIndex = 1;
        int count = 0;
        while (count < previousRound.getMatches().size()){
            count++;
            count++;

            KnockoutMatch m = MatchFac.createCup(round, matchNumber++, new SeedingTeam(), new SeedingTeam());
            m.setName((""+c)+nameIndex++);
            list.add(m);
        }
        round.setMatches(list);
        return round;

    }

    private KnockoutMatch createMatch(List<Team> list, Round round){
        return MatchFac.createCup(round, matchNumber++, list.remove(0), list.remove(list.size() - 1));
    }


    private List<Team> createTeamList(int binaryTeamCount){
        List<Team> list = new ArrayList<Team>();
        for (Team pt : teams) {
            list.add(pt);
        }

        int walkOvers = binaryTeamCount - teams.size();
        while(walkOvers > 0){
            list.add(new WalkOverTeam("---"));
            walkOvers--;
        }
        return list;
    }

    private int getBinaryTeamCount() {
        int binaryCount = 1;
        while (teams.size() > binaryCount) {
            binaryCount = binaryCount * 2;
        }
        return binaryCount;
    }


    public List<Round> getRounds() {
        return allRounds;
    }


}
