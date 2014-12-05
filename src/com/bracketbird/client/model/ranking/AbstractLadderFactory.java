package com.bracketbird.client.model.ranking;

import java.util.ArrayList;
import java.util.List;

public class AbstractLadderFactory {

    private List<LadderFactory> factories = new ArrayList<LadderFactory>();
    private int index = 0;


    public LadderFactory next(){
        if(factories.size() > index){
            return factories.get(index++);
        }
        return null;
    }


}
