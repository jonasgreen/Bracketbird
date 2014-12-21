package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.KnockoutRoundsBuilder;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.rtc.event.UpdateEvent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class KnockoutStage extends Stage {

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
            Round nextRound = getRounds().get(m.getRound().getRoundNumber());
            Round thisRound = getRounds().get((m.getRound().getRoundNumber() - 1));

            Match nextMatch = nextRound.getMatch(getMatchIndexInNextRound(thisRound, m));

            Team winTeam = m.getWinningTeam();

            if (isHomeTeamInNextRound(thisRound, m)) {
                nextMatch.updateHomeTeam(winTeam, true);
            }
            else {
                nextMatch.updateOutTeam(winTeam, true);
            }
        }
    }

    @Override
    public void onUpdate(UpdateEvent<LevelState> event) {
        endingTeams = new ArrayList<>();
        updateState(event.isFromClient());
    }

    @Override
    public String getRoundName(Round round) {
            return getMatches().size() == 1 ? "The final" : "1/"+(getMatches().size())+ " finals";
    }

    public int getMatchIndexInNextRound(Round round, Match m){
        int index = round.indexOf(m);

        if(index%2 != 0){//not equal
            index = index-1;
        }

        return index/2;
    }


    public boolean isHomeTeamInNextRound(Round round, Match m){
        return round.indexOf(m) % 2 == 0;
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
        Round thisRound = getRounds().get(0);
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
        setupRounds();
        handleWalkovers();

        updateState(fromClient);
    }

    private void setupRounds() {
        rounds = new ArrayList<Round>(new KnockoutRoundsBuilder(startingTeams, this).getRounds());
    }


    public LevelState calculateState() {
        LevelState childrenState = new LevelStateCalculator().stateBasedOnChildren(rounds);
        if(childrenState.isFinished()){
            setEndingTeams();
            return LevelState.finished;
        }
        return childrenState;
    }

    private void setEndingTeams() {
        //find ranking and set ending teams
        for (Round round : rounds) {
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

