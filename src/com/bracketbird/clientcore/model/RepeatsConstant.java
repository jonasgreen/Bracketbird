package com.bracketbird.clientcore.model;

import java.util.HashMap;

/**
 *
 */
public class RepeatsConstant extends IntegerConstant{
    private static final long serialVersionUID = 3451908949098816778L;

    private static HashMap<Integer, RepeatsConstant> map = new HashMap<Integer, RepeatsConstant>();

    public static RepeatsConstant NO_REPEAT = new RepeatsConstant("Don't repeat", 0);

    public static RepeatsConstant WEEKLY = new RepeatsConstant("Weekly", 1);
    public static RepeatsConstant MONTHLY = new RepeatsConstant("Monthly", 2);
    public static RepeatsConstant QUARTER_YEARLY = new RepeatsConstant("Every 1/4 year", 3);
    public static RepeatsConstant HALF_YEALY = new RepeatsConstant("Every 1/2 year", 4);
    public static RepeatsConstant YEARLY = new RepeatsConstant("yearly", 5);


    public static KeyValueList<Integer> LIST = new KeyValueList<Integer>();
    static{
        LIST.add(NO_REPEAT);
        LIST.add(WEEKLY);
        LIST.add(MONTHLY);
        LIST.add(QUARTER_YEARLY);
        LIST.add(HALF_YEALY);
        LIST.add(YEARLY);
    }


    public RepeatsConstant(String text, Integer value) {
        super(text, value);
        map.put(value, this);
    }

    public static RepeatsConstant get(Integer value){
        return map.get(value);
    }
}