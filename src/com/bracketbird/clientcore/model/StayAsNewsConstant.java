package com.bracketbird.clientcore.model;

import java.util.HashMap;

/**
 *
 */
public class StayAsNewsConstant extends IntegerConstant{
    private static final long serialVersionUID = 118644985896229767L;

    private static HashMap<Integer, StayAsNewsConstant> map = new HashMap<Integer, StayAsNewsConstant>();


    public static StayAsNewsConstant ONE_WEEK = new StayAsNewsConstant("1 week", 1);
    public static StayAsNewsConstant TWO_WEEKS = new StayAsNewsConstant("2 weeks", 2);
    public static StayAsNewsConstant THREE_WEEKS = new StayAsNewsConstant("3 weeks", 3);
    public static StayAsNewsConstant FOUR_WEEKS = new StayAsNewsConstant("4 weeks", 4);


    public static KeyValueList<Integer> LIST = new KeyValueList<Integer>();

    static{
        LIST.add(ONE_WEEK);
        LIST.add(TWO_WEEKS);
        LIST.add(THREE_WEEKS);
        LIST.add(FOUR_WEEKS);
    }


    public StayAsNewsConstant(String text, Integer value) {
        super(text, value);
        map.put(value, this);
    }

    public static StayAsNewsConstant get(Integer value){
        return map.get(value);
    }
}