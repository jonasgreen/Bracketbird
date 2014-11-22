package com.bracketbird.client.model.tournament;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CupRound extends Round {
    private static final long serialVersionUID = 7856387235373392743L;



    public CupRound(KnockoutStage stage, int roundNo) {
        super(stage, roundNo);
    }

    @Override
    public String getName() {
        return getMatches().size() == 1 ? "The final" : "1/"+(getMatches().size())+ " finals";
    }


    public CupMatch getMatch(int index){
        return (CupMatch) getMatches().get(index);
    }


    public int size(){
        return getMatches().size();
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
        for (Match m : getMatches()) {
            if(m.isFinish()){
                finished.add(m);
            }
        }
        return finished;
    }

    @Override
    public String toString() {
        return "CupRound{" +
                "matches=" + (getMatches() == null ? null :getMatches()) +
                '}';
    }

}
