package com.bracketbird.client;

import com.bracketbird.clientcore.model.*;

/**
 *
 */
public class KeyValueConstant {


    public static KeyValueList<Boolean> YES_NO = new KeyValueList<Boolean>();
    public static KeyValueList<Integer> ELIMINATION_TYPE = new KeyValueList<Integer>();


    public static KeyValueList<Integer> TIMES_TEAM_MEET_EACH_OTHER = new KeyValueList<Integer>();

    static{
        YES_NO.add("Yes", Boolean.TRUE);
        YES_NO.add("No", Boolean.FALSE);
    }

    static{
        ELIMINATION_TYPE.add("Single elimination", 1);
        //ELIMINATION_TYPE.add("Double elimination", 2);
    }

    static{
        TIMES_TEAM_MEET_EACH_OTHER.add("One time", 1);
        //TIMES_TEAM_MEET_EACH_OTHER.add("Two times", 2);
        //TIMES_TEAM_MEET_EACH_OTHER.add("Three times", 3);
    }

 




}
