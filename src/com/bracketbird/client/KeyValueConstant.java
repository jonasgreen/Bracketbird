package com.bracketbird.client;

import com.bracketbird.clientcore.model.*;

/**
 *
 */
public class KeyValueConstant {


    public static KeyValueList<Integer> USER_TYPES = new KeyValueList<Integer>();
    public static KeyValueList<Integer> BEST_OF_SETS = new KeyValueList<Integer>();
    public static KeyValueList<Boolean> YES_NO = new KeyValueList<Boolean>();
    public static KeyValueList<Integer> ELIMINATION_TYPE = new KeyValueList<Integer>();
    public static KeyValueList<Integer> TYPE_OF_TOURNAMENT = new KeyValueList<Integer>();


    public static KeyValueList<Integer> TIMES_TEAM_MEET_EACH_OTHER = new KeyValueList<Integer>();

    public static KeyValueList<String> CLUBVISABILITY = new KeyValueList<String>();

    static{
        CLUBVISABILITY.add(ClubVisabilityConstant.OPEN.getText(), ClubVisabilityConstant.OPEN.getValue()+"");
        CLUBVISABILITY.add(ClubVisabilityConstant.CLOSED.getText(), ClubVisabilityConstant.CLOSED.getValue()+"");
    }

    static{
        BEST_OF_SETS.add("1 set", 1);
        BEST_OF_SETS.add("3 sets", 3);
        BEST_OF_SETS.add("5 sets", 5);
    }

    static{
        YES_NO.add("Yes", Boolean.TRUE);
        YES_NO.add("No", Boolean.FALSE);
    }


    static{
        TYPE_OF_TOURNAMENT.add("Choose type...", -1);
        TYPE_OF_TOURNAMENT.add("Single", 1);
        TYPE_OF_TOURNAMENT.add("Double", 2);
        TYPE_OF_TOURNAMENT.add("Teams", 3);
        TYPE_OF_TOURNAMENT.add("Draw your partner", 4);

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
