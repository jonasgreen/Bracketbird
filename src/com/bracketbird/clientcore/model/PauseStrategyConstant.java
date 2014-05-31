package com.bracketbird.clientcore.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class PauseStrategyConstant extends IntegerConstant {

    private static final long serialVersionUID = 1559041222238768565L;
    private static Map<Integer, PauseStrategyConstant> map = new HashMap<Integer, PauseStrategyConstant>();

    public static PauseStrategyConstant AFTER_EACH_ROUND = new PauseStrategyConstant("After each round", 10);
    public static PauseStrategyConstant AFTER_EACH_LEVEL = new PauseStrategyConstant("After each level", 20);
    public static PauseStrategyConstant NEVER = new PauseStrategyConstant("Never", 30);


    public static KeyValueList<Integer> LIST = new KeyValueList<Integer>();
    static{
        LIST.add(AFTER_EACH_ROUND);
        LIST.add(AFTER_EACH_LEVEL);
        LIST.add(NEVER);
    }



    public PauseStrategyConstant(String text, Integer value) {
        super(text, value);
        map.put(value, this);
    }

    public static PauseStrategyConstant get(Integer type) {
        return map.get(type);
    }
}