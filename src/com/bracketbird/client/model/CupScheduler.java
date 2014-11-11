package com.bracketbird.client.model;


import com.bracketbird.client.model.tournament.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CupScheduler extends Scheduler<CupRound> {

    private static char[] charNames = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','X','Y','Z'};

    private List<CupRound> allRounds = new ArrayList<CupRound>();
    private List<Team> teams;
    private TournamentLevel level;
    private int matchNumber = 1;

    public CupScheduler(List<Team> teams, TournamentLevel level) {
        this.teams = teams;
        this.level = level;
        build();
    }

    private void build() {
        //return the number of teams nessecary to fill cup at the specifik round
        int binaryTeamCount = getBinaryTeamCount();

        List<Team> bTeamList = createTeamList(binaryTeamCount);


        List<CupMatch> left = new ArrayList<CupMatch>();
        List<CupMatch> right = new ArrayList<CupMatch>();


        left.add(createMatch(bTeamList, 1));
        List<CupMatch> pointer = right;
        int count = 0;
        while (!bTeamList.isEmpty()){
            if(count == 2){
                count = 0;
                pointer = pointer == right ? left : right;
            }
            pointer.add(createMatch(bTeamList, 1));
            count++;
        }

        Collections.reverse(right);
        List<CupMatch> allCupMatchesFirstRound = new ArrayList<CupMatch>();
        allCupMatchesFirstRound.addAll(left);
        allCupMatchesFirstRound.addAll(right);
        addNames(allCupMatchesFirstRound, 0);

        CupRound firstRound = new CupRound(allCupMatchesFirstRound);

        List<CupRound> rounds = new ArrayList<CupRound>();
        rounds.add(firstRound);

        int roundNumber = 2;
        CupRound tempRound = firstRound;
        while (tempRound.size() > 1){
            tempRound = buildNextRound(tempRound, roundNumber++);
            rounds.add(tempRound);
        }

        allRounds = rounds;
        int start = rounds.size()-1;
        while (start > 0){
            appendChilds(rounds.get(start-1).getMatches(), rounds.get(start).getMatches());
            start--;
        }
    }

    private void appendChilds(List<CupMatch> parents, List<CupMatch> childs){
        int childCount = 0;
        for (CupMatch child : childs) {
            int parentIndex = childCount*2;
            parents.get(parentIndex).setParent(child);
            parents.get(parentIndex+1).setParent(child);
           childCount++;
        }
    }

    private void addNames(List<CupMatch> allCupMatchesFirstRound, int charNameIndex) {
        char c = charNames[charNameIndex];
        int i = 1;
        for (CupMatch m : allCupMatchesFirstRound) {
            m.setName((""+c)+i++);
        }
    }

    private CupRound buildNextRound(CupRound previousRound, int roundNumber) {
        List<CupMatch> list = new ArrayList<CupMatch>();
        char c = charNames[(int)roundNumber];
        int nameIndex = 1;
        int count = 0;
        while (count < previousRound.size()){
            CupMatch one = previousRound.get(count++);
            CupMatch two = previousRound.get(count++);
            CupMatch m = MatchFac.createCup(level, null, roundNumber, matchNumber++, new SeedingTeam(), new SeedingTeam());
            m.setName((""+c)+nameIndex++);
            list.add(m);
        }
        return new CupRound(list);

    }

    private CupMatch createMatch(List<Team> list, long roundNumber){
        return MatchFac.createCup(level, null, roundNumber, matchNumber++, list.remove(0), list.remove(list.size() - 1));
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


    @Override
    public List<CupRound> getRounds() {
        return allRounds;
    }


}
