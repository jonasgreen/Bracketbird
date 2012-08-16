package com.bracketbird.client.model.tournament;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class CupRound extends Round {
    private static final long serialVersionUID = 7856387235373392743L;

    private CupMatch[] matches;


    public CupRound(List<CupMatch> ms) {
        super(ms.size() == 1 ? "The final" : "1/"+(ms.size())+ " finals");
        matches = new CupMatch[ms.size()];
        int i = 0;
        for (CupMatch m : ms) {
            matches[i++] = m;
        }
    }

    public List<CupMatch> getMatches() {
        List<CupMatch> list = new ArrayList<CupMatch>();
        list.addAll(Arrays.asList(matches));
        return list;
    }

    public Match getMatch(int index){
        return matches[index];
    }

    @Override
    public int indexOf(Match m) {
        int index = 0;
        for (Match match : matches) {
            if(match.equals(m)){
                return index;
            }
            index++;
        }
        return -1;
    }

    public int size(){
        return matches.length;
    }

    public CupMatch get(int index){
        return matches[index];
    }



    //teams from a match of equal index in the row will be placed as personal teams in next match in next round.
    public boolean isHomeTeamInNextRound(Match m){
        return indexOf(m) % 2 == 0;
    }

    public int getMatchIndexInNextRound(Match m){
        int index = indexOf(m);

        if(index%2 != 0){//not equal
            index = index-1;
        }

        return index/2;
    }

    public List<Match> getFinishedMatches() {
        List<Match> finished = new ArrayList<Match>();
        for (Match m : matches) {
            if(m.isFinish()){
                finished.add(m);
            }
        }
        return finished;
    }

    @Override
    public String toString() {
        return "CupRound{" +
                "matches=" + (matches == null ? null : Arrays.asList(matches)) +
                '}';
    }
}
