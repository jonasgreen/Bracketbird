package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.MatchId;

/**
 *
 */
public class MatchFac {

    private static int countId = 1;

    public static Match createGroupMatch(Round round, int matchNo, Team teamHome, Team teamOut){
        Match m = new GroupMatch(round, matchNo);
        m.setCountId(countId);
        m.setId(new MatchId("matchId "+countId++));
        m.setTeamHome(teamHome);
        m.setTeamOut(teamOut);
        m.initState();

        m.addStateHandler(round);
        return m;
    }

    public static KnockoutMatch createCup(Round round, int matchNo, Team teamHome, Team teamOut){
        KnockoutMatch m = new KnockoutMatch(round, matchNo);
        m.setCountId(countId);
        m.setId(new MatchId("matchId "+countId++));
        m.setTeamHome(teamHome);
        m.setTeamOut(teamOut);
        m.initState();

        m.addStateHandler(round);

        return m;
    }

    public static void reset(){
        countId = 1;
    }
}
