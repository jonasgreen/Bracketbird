package com.bracketbird.client.model.tournament;

import com.bracketbird.client.gui.rtc.event.UpdateMatchResultEvent;
import com.bracketbird.client.model.Team;

/**
 *
 */
public class CupMatch extends Match {
    private CupMatch parent;

    public CupMatch getParent() {
        return parent;
    }

    public void setParent(CupMatch parent) {
        this.parent = parent;
    }


    protected void update(UpdateMatchResultEvent event) {
        Result oldResult = getResult();
        super.update(event);

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
        Round r = getLevel().getRounds().get((int) getRound().longValue()-1);
        int indexInRound = r.indexOf(this);
        return indexInRound % 2 == 0;
    }


    public String toString() {
        return super.toString() + " - child: " + parent;
    }
}
