package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.client.model.keys.MatchId;

/**
 *
 */
public class UpdateMatchResultEvent extends REvent<UpdateMatchResultEventHandler, MatchId>{

    private static final long serialVersionUID = -3855018382745686412L;
    private int[] homeScores;
    private int[] outScores;


    public UpdateMatchResultEvent() {
    }

    public UpdateMatchResultEvent(Long id, MatchId modelId, int[] homeScore, int[] outScore) {
        super(id, modelId);
        this.homeScores = homeScore;
        this.outScores = outScore;
    }

    public int[] getHomeScores() {
        return homeScores;
    }

    public int[] getOutScores() {
        return outScores;
    }

    @Override
    public Class<UpdateMatchResultEventHandler> getHandler() {
        return UpdateMatchResultEventHandler.class;
    }


    @Override
    public boolean isUpdateEvent(){
        return true;
    }
}
