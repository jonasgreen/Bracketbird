package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.MatchId;

/**
 *
 */
public class MatchFac {
    private static int countId = 1;

    public static Match create(TournamentLevel level, String groupName, long round, int order, Team teamHome, Team teamOut){
        Match m = new Match();
        m.setCountId(countId);
        m.setId(new MatchId("matchId "+countId++));
        m.setLevel(level);
        m.setRound(round);
        m.setOrder(order);
        m.setGroupName(groupName);
        m.setTeamHome(teamHome);
        m.setTeamOut(teamOut);
        m.initState();
        return m;
    }

    public static CupMatch createCup(TournamentLevel level, String groupName, long round, int order, Team teamHome, Team teamOut){
        CupMatch m = new CupMatch();
        m.setCountId(countId);
        m.setId(new MatchId("matchId "+countId++));
        m.setLevel(level);
        m.setRound(round);
        m.setOrder(order);
        m.setGroupName(groupName);
        m.setTeamHome(teamHome);
        m.setTeamOut(teamOut);
        m.initState();

        return m;
    }

    public static void reset(){
        countId = 1;
    }
}
