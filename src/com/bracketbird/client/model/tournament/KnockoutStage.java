package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.KnockoutRoundsBuilder;
import com.bracketbird.client.model.Team;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class KnockoutStage extends Stage {


    private static final long serialVersionUID = 2056092174233628140L;


    public KnockoutStage(Tournament t) {
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
            KnockoutStageRound nextRound = (KnockoutStageRound) getRounds().get(m.getRound().getRoundNumber());
            KnockoutStageRound thisRound = (KnockoutStageRound) getRounds().get((m.getRound().getRoundNumber() - 1));

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
        KnockoutStageRound thisRound = (KnockoutStageRound) getRounds().get(0);
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

    public LevelState calculateState() {
        return stateBasedOnChildren(rounds);
    }

    protected LevelState stateChanged(LevelState oldState, LevelState newState) {
        endingTeams = new ArrayList<List<Team>>();
        if (newState.equals(LevelState.finished)) {
            setEndingTeams();
        }
        return newState;
    }

    private void setEndingTeams() {
        //find ranking and set ending teams
        for (StageRound round : rounds) {
            List<Match> matches = round.getMatches();
            List<Team> losingTeams = new ArrayList<Team>();
            for (Match match : matches) {
                Team t = match.getLosingTeam();
                if (t.isARealTeam()) {
                    losingTeams.add(t);
                }
            }
            List<Team> teams = new ArrayList<Team>();
            for (Team losingTeam : losingTeams) {
                teams.add(losingTeam);
            }
            endingTeams.add(0, teams);
        }

        Team winner = (rounds.get(rounds.size() - 1).getMatches().get(0)).getWinningTeam();
        List<Team> l = new ArrayList<Team>();
        l.add(winner);
        endingTeams.add(0, l);
    }


}

