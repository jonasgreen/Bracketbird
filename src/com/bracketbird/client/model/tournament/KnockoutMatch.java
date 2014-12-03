package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.Team;

/**
 *
 */
public class KnockoutMatch extends Match {
    private static final long serialVersionUID = 6944560675227729797L;

    private KnockoutMatch parent;

    public KnockoutMatch(Round round, int matchNo) {
        super(round, matchNo);
    }

    public KnockoutMatch getParentMatch() {
        return parent;
    }

    public void setParentMatch(KnockoutMatch parent) {
        this.parent = parent;
    }

    public void updateResult(int[] homeScores, int[] outScores, boolean fromClient) {
        Result oldResult = getResult();
        super.updateResult(homeScores, outScores, fromClient);

        if (parent == null || hasSameWinner(oldResult, getResult())) {
            return;
        }

        boolean isUpperInNextRound = isUpperInNextRound();
        Team winningTeam = getResult() == null ? null : (getResult().homeIsWinning() ? getTeamHome() : getTeamOut());
        if (isUpperInNextRound) {
            parent.updateHomeTeam(winningTeam, true);
        }
        else {
            parent.updateOutTeam(winningTeam, true);
        }

        parent.updateResult(null, null, fromClient);
    }

    private boolean hasSameWinner(Result oldResult, Result result) {
        if(oldResult == null && result == null){
            return true;
        }
        if(oldResult != null && result != null){
            return (oldResult.homeIsWinning() && result.homeIsWinning()) || (oldResult.outIsWinning() && result.outIsWinning());
        }
        return false;
    }


    private boolean isUpperInNextRound() {
        Round parentRound = getRound();
        Round r = parentRound.getStage().getRounds().get(parentRound.getRoundNumber() -1);
        int indexInRound = r.indexOf(this);
        return indexInRound % 2 == 0;
    }


    public String toString() {
        return super.toString() + " - child: " + parent;
    }


}
