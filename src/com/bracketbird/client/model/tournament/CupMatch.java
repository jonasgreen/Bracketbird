package com.bracketbird.client.model.tournament;

import com.bracketbird.client.gui.rtc.event.UpdateMatchResultEvent;
import com.bracketbird.client.model.Team;

/**
 *
 */
public class CupMatch extends Match {
    private static final long serialVersionUID = 6944560675227729797L;

    private CupMatch parent;

    public CupMatch(CupRound round, int matchNo) {
        super(round, matchNo);
    }

    public CupMatch getParentMatch() {
        return parent;
    }

    public void setParent(CupMatch parent) {
        this.parent = parent;
    }


    protected void update(UpdateMatchResultEvent event) {
        Result oldResult = getResult();
        super.updateResult(event.getHomeScores(), event.getOutScores(), event.isFromClient());

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

        parent.update(new UpdateMatchResultEvent(null, null, null, null));
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
        //Hmmm
        Round parentRound = getParent();
        Round r = parentRound.getParent().getRounds().get(parentRound.getRoundNumber() -1);
        int indexInRound = r.indexOf(this);
        return indexInRound % 2 == 0;
    }


    public String toString() {
        return super.toString() + " - child: " + parent;
    }
}
