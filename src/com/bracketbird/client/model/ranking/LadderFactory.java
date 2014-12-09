package com.bracketbird.client.model.ranking;

public abstract class LadderFactory {

    private LadderFactory next;
    private RankingLadderType type;

    public LadderFactory(RankingLadderType type) {
        this.type = type;
    }

    public abstract RankingLadder create(RankingLadder parent, Integer id);


    public RankingLadderType getType() {
        return type;
    }

    public LadderFactory getNext() {
        return next;
    }

    public void setNext(LadderFactory next) {
        this.next = next;
    }


}
