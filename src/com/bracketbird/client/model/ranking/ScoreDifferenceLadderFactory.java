package com.bracketbird.client.model.ranking;

public class ScoreDifferenceLadderFactory extends LadderFactory{

    public ScoreDifferenceLadderFactory() {
        super(RankingLadderType.scoreDifference);
    }

    @Override
    public RankingLadder create(RankingLadder parent, Integer id) {
        return new ScoreDifferenceLadder(getNext(), parent, id);
    }


}
