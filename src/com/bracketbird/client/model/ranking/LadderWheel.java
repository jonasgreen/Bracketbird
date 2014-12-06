package com.bracketbird.client.model.ranking;

public class LadderWheel {

    private final RankingType[] ladders;
    private int index = 0;

    public LadderWheel(RankingType... ladders) {
        this.ladders = ladders;
    }

    public RankingLadder first(){
        return next(null, null);
    }


    public RankingLadder next(RankingLadder parent, Integer id){
        if(index < ladders.length){
            RankingLadder ladder = AbstractLadderFactory.get().createLadder(ladders[index++], parent, id);
            ladder.setWheel(this);
        }
        return null;
    }


}
