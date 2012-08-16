package com.bracketbird.clientcore.model;

import java.util.HashMap;

/**
 *
 */
public class NotificationsByMailConstant extends IntegerConstant{
    private static final long serialVersionUID = 118644985896229767L;

    private static HashMap<Integer, NotificationsByMailConstant> map = new HashMap<Integer, NotificationsByMailConstant>();

    public static NotificationsByMailConstant NO_NOTIFICATION = new NotificationsByMailConstant("Don't notify", 0);

    public static NotificationsByMailConstant ONE_DAY = new NotificationsByMailConstant("1 day before", 1);
    public static NotificationsByMailConstant TWO_DAYS = new NotificationsByMailConstant("2 days before", 2);
    public static NotificationsByMailConstant THREE_DAYS = new NotificationsByMailConstant("3 days before", 3);
    public static NotificationsByMailConstant FOUR_DAYS = new NotificationsByMailConstant("4 days before", 4);
    public static NotificationsByMailConstant FIVE_DAYS = new NotificationsByMailConstant("5 days before", 5);
    public static NotificationsByMailConstant SIX_DAYS = new NotificationsByMailConstant("6 days before", 6);
    public static NotificationsByMailConstant SEVEN_DAYS = new NotificationsByMailConstant("7 days before", 7);


    public static KeyValueList<Integer> LIST = new KeyValueList<Integer>();

    static{
        LIST.add(NO_NOTIFICATION);
        LIST.add(ONE_DAY);
        LIST.add(TWO_DAYS);
        LIST.add(THREE_DAYS);
        LIST.add(FOUR_DAYS);
        LIST.add(FIVE_DAYS);
        LIST.add(SIX_DAYS);
        LIST.add(SEVEN_DAYS);

    }


    public NotificationsByMailConstant(String text, Integer value) {
        super(text, value);
        map.put(value, this);
    }

    public static NotificationsByMailConstant get(Integer value){
        return map.get(value);
    }
}