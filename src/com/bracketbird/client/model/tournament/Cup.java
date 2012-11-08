package com.bracketbird.client.model.tournament;

import com.bracketbird.client.gui.rtc.event.UpdateMatchResultEvent;
import com.bracketbird.client.model.*;

import java.util.*;

/**
 *
 */
public class Cup extends TournamentLevel {


    private static final long serialVersionUID = 2056092174233628140L;

    public Cup() {
        super();

    }

    public Cup(Tournament t) {
        super(t);
    }


    public String getName() {
        if (getRounds() == null || getRounds().size() == 0) {
            return "Knockout";
        }

        return getRounds().size() == 1 ? "Knockout final" : ("Knockout 1/" + binaryCount());
    }

    protected void handleWalkover(Match m) {
        if (m.isFinish() && hasNextRound(m)) {
            CupRound nextRound = (CupRound) getRounds().get(m.getRound().intValue());
            CupRound thisRound = (CupRound) getRounds().get((int) (m.getRound() - 1));

            Match nextMatch = nextRound.getMatch(thisRound.getMatchIndexInNextRound(m));

            Team winTeam = m.getWinningTeam();

            if (thisRound.isHomeTeamInNextRound(m)) {
                nextMatch.updateHomeTeam(winTeam, true);
            }
            else {
                nextMatch.updateOutTeam(winTeam, true);
            }
        }
    }

    private boolean hasNextRound(Match m) {
        return getRounds().size() > m.getRound();
    }

    public int binaryCount() {
        int count = 1;
        for (Round round : rounds) {
            count = count * 2;
        }
        return count / 2;
    }


    public void handleWalkovers() {
        if (getRounds().size() < 2) {
            return;
        }
        CupRound thisRound = (CupRound) getRounds().get(0);
        //index of match to be updated with winning team.
        int indexNextRound;
        List<Match> fms = thisRound.getFinishedMatches();

        for (Match m : fms) {
            handleWalkover(m);
        }
    }


    @Override
    public Scheduler getScheduler() {
        return new CupScheduler(startingTeams, this);
    }


}
