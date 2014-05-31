package com.bracketbird.clientcore.model;

import java.util.Map;
import java.util.HashMap;

/**
 *
 */
public class TournamentLevelConstant extends IntegerConstant {
    private static final long serialVersionUID = -405042641954310409L;

    private static Map<Integer, TournamentLevelConstant> map = new HashMap<Integer, TournamentLevelConstant>();

    public static TournamentLevelConstant REMOVE = new TournamentLevelConstant("Remove last added stage", -2);
    public static TournamentLevelConstant CHOOSE = new TournamentLevelConstant("Add or remove stages", -1);

    public static TournamentLevelConstant SEEDING = new TournamentLevelConstant("Seeding", 10);
    public static TournamentLevelConstant GROUP = new TournamentLevelConstant("Group", 20);
    public static TournamentLevelConstant CUP = new TournamentLevelConstant("Knockout", 30);


    public static KeyValueList<Integer> LIST = new KeyValueList<Integer>();
    static{
        //LIST.add(SEEDING);
        LIST.add(GROUP);
        LIST.add(CUP);
    }


    public static KeyValueList<Integer> LIST_WITH_CHOOSE = new KeyValueList<Integer>();
    static{
        LIST_WITH_CHOOSE.add(CHOOSE);
        //LIST_WITH_CHOOSE.add(SEEDING);
        LIST_WITH_CHOOSE.add(GROUP);
        LIST_WITH_CHOOSE.add(CUP);
        LIST_WITH_CHOOSE.add(REMOVE);

    }

    public TournamentLevelConstant(String text, Integer value) {
        super(text, value);
        map.put(value, this);
    }

    public static TournamentLevelConstant get(Integer type) {
        return map.get(type);
    }
}