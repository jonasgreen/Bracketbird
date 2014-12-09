package com.bracketbird.client.model.ranking;

public class ScoreTotalLadderFactory extends LadderFactory{

    public ScoreTotalLadderFactory() {
        super(RankingLadderType.scoreTotal);
    }

    @Override
    public RankingLadder create(RankingLadder parent, Integer id) {
        return new ScoreTotalLadder(getNext(), parent, id);
    }


}
