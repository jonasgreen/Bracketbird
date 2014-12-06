package com.bracketbird.client.model.ranking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractLadderFactory {

    private Map<RankingType, LadderFactory> factoryMap = new HashMap<RankingType, LadderFactory>();


    private static AbstractLadderFactory instance;


    public static AbstractLadderFactory get() {
        if (instance == null) {
            instance = new AbstractLadderFactory();
        }
        return instance;
    }

    private AbstractLadderFactory() {
        add(RankingType.point, new LadderFactory() {
            @Override
            public RankingLadder create(RankingLadder parent, Integer id) {
                return new PointLadder(parent, id);
            }
        });

        add(RankingType.scoreDifference, new LadderFactory() {
            @Override
            public RankingLadder create(RankingLadder parent, Integer id) {
                return new ScoreDifferenceLadder(parent, id);
            }
        });

        add(RankingType.interMatchWinner, new LadderFactory() {
            @Override
            public RankingLadder create(RankingLadder parent, Integer id) {
                return new InterMatchLadder(parent, id);
            }
        });
    }


    private List<LadderFactory> factories = new ArrayList<LadderFactory>();
    private int index = 0;


    public LadderFactory next(){
        if(factories.size() > index){
            return factories.get(index++);
        }
        return null;
    }


    private void add(RankingType type, LadderFactory factory){
        factoryMap.put(type, factory);
    }

    public RankingLadder createLadder(RankingType type, RankingLadder parent, Integer id) {
        return factoryMap.get(type).create(parent, id);
    }
}
