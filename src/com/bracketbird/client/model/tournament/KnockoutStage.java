package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.CupScheduler;
import com.bracketbird.client.model.LevelType;
import com.bracketbird.client.model.Scheduler;
import com.bracketbird.client.model.Team;

import java.util.List;

/**
 *
 */
public class KnockoutStage extends TournamentStage {


    private static final long serialVersionUID = 2056092174233628140L;

    private KnockoutStage() {
        super();
    }

    public KnockoutStage(Tournament t) {
        super(t, LevelType.knockout);
    }


    public String getName() {
        if (getRounds() == null || getRounds().size() == 0) {
            return "Knockout";
        }

        return getRounds().size() == 1 ? "Knockout final" : ("Knockout 1/" + binaryCount());
    }

    protected void handleWalkover(Match m) {
        if (m.isFinish() && hasNextRound(m)) {
            CupRound nextRound = (CupRound) getRounds().get(m.getRound().getRoundNumber());
            CupRound thisRound = (CupRound) getRounds().get((int) (m.getRound().getRoundNumber() - 1));

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
        return getRounds().size() > m.getRound().getRoundNumber();
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

