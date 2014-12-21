package com.bracketbird.client.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class PlayingStrategyConstant extends IntegerConstant {

    private static final long serialVersionUID = 1559041222238768565L;
    private static Map<Integer, PlayingStrategyConstant> map = new HashMap<Integer, PlayingStrategyConstant>();

    public static PlayingStrategyConstant ONE_ROUND_AT_THE_TIME = new PlayingStrategyConstant("Finish round before proceeding", 10);
    public static PlayingStrategyConstant ONE_LEVEL_AT_THE_TIME = new PlayingStrategyConstant("Finish level before proceeding", 20);
    public static PlayingStrategyConstant FAST_AS_POSSIBLE = new PlayingStrategyConstant("As fast as possible", 30);


    public static KeyValueList<Integer> LIST = new KeyValueList<Integer>();
    static{
        LIST.add(ONE_ROUND_AT_THE_TIME);
        LIST.add(ONE_LEVEL_AT_THE_TIME);
        LIST.add(FAST_AS_POSSIBLE);
    }



    public PlayingStrategyConstant(String text, Integer value) {
        super(text, value);
        map.put(value, this);
    }

    public PlayingStrategyConstant() {
    }

    public static PlayingStrategyConstant get(Integer type) {
        return map.get(type);
    }
}