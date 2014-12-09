package com.bracketbird.client.model.ranking;

import java.util.HashMap;
import java.util.Map;

public class AbstractLadderFactory {

    private Map<RankingLadderType, FactoryCreator> factoryCreatorMap = new HashMap<RankingLadderType, FactoryCreator>();

    private static AbstractLadderFactory instance;

    public static AbstractLadderFactory get() {
        if (instance == null) {
            instance = new AbstractLadderFactory();
        }
        return instance;
    }

    private AbstractLadderFactory(){
        init();
    }

    private void init() {
        add(new FactoryCreator() {
            @Override
            public LadderFactory create() {
                return new PointLadderFactory();
            }
        });
        add(new FactoryCreator() {
            @Override
            public LadderFactory create() {
                return new ScoreTotalLadderFactory();
            }
        });
        add(new FactoryCreator() {
            @Override
            public LadderFactory create() {
                return new ScoreDifferenceLadderFactory();
            }
        });
    }

    private void add(FactoryCreator creator) {
        factoryCreatorMap.put(creator.create().getType(), creator);
    }

    public LadderFactory getLinkedFactories(RankingLadderType ... types){
        LadderFactory first = null;
        LadderFactory last = null;

        for (RankingLadderType type : types) {
            FactoryCreator c = factoryCreatorMap.get(type);
            if(c == null){
                throw new RuntimeException(type.name() + " " + " not supported in AbstractLadderFactory.");
            }
            LadderFactory next = c.create();
            if(first == null){
                first = next;
                last = next;
            }
            else{
                last.setNext(next);
                last = next;
            }
        }
        return first;

    }


}
