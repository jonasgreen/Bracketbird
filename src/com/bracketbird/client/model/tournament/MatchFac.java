package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.MatchId;

/**
 *
 */
public class MatchFac {

    private static int countId = 1;

    public static Match createGroupMatch(GroupRound round, int matchNo, Team teamHome, Team teamOut){
        Match m = new GroupMatch(round, matchNo);
        m.setCountId(countId);
        m.setId(new MatchId("matchId "+countId++));
        m.setTeamHome(teamHome);
        m.setTeamOut(teamOut);
        m.initState();
        return m;
    }

    public static CupMatch createCup(CupRound round, int matchNo, Team teamHome, Team teamOut){
        CupMatch m = new CupMatch(round, matchNo);
        m.setCountId(countId);
        m.setId(new MatchId("matchId "+countId++));
        m.setTeamHome(teamHome);
        m.setTeamOut(teamOut);
        m.initState();
        return m;
    }

    public static void reset(){
        countId = 1;
    }
}
