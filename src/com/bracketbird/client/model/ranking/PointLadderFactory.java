package com.bracketbird.client.model.ranking;

public class PointLadderFactory extends LadderFactory{

    public PointLadderFactory() {
        super(RankingLadderType.point);
    }

    @Override
    public RankingLadder create(RankingLadder parent, Integer id) {
        return new PointLadder(getNext(), parent, id);
    }


}
