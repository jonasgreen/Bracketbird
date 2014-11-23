package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.KnockoutRoundsBuilder;
import com.bracketbird.client.model.StageType;
import com.bracketbird.client.model.Team;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class KnockoutStage extends Stage {


    private static final long serialVersionUID = 2056092174233628140L;

    private KnockoutStage() {
        super();
    }

    public KnockoutStage(Tournament t) {
        super(t, StageType.knockout);
    }


    public String getName() {
        if (getRounds() == null || getRounds().size() == 0) {
            return "Knockout";
        }

        return getRounds().size() == 1 ? "Knockout final" : ("Knockout 1/" + binaryCount());
    }

    protected void handleWalkover(Match m) {
        if (m.isFinish() && hasNextRound(m)) {
            KnockoutRound nextRound = (KnockoutRound) getRounds().get(m.getRound().getRoundNumber());
            KnockoutRound thisRound = (KnockoutRound) getRounds().get((int) (m.getRound().getRoundNumber() - 1));

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
        KnockoutRound thisRound = (KnockoutRound) getRounds().get(0);
        //index of match to be updated with winning team.
        int indexNextRound;
        List<Match> fms = thisRound.getFinishedMatches();

        for (Match m : fms) {
            handleWalkover(m);
        }
    }


    @Override
    public void layoutMatches(boolean fromClient) {
        setupStartingTeams();

        rounds = new ArrayList<StageRound>(new KnockoutRoundsBuilder(startingTeams, this).getRounds());

        handleWalkovers();
        updateState(fromClient);
    }


}

