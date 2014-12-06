package com.bracketbird.client.model.ranking;

public abstract class LadderFactory {


    public abstract RankingLadder create(RankingLadder parent, Integer id);



}
