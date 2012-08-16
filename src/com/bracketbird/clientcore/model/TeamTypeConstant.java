package com.bracketbird.clientcore.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class TeamTypeConstant extends IntegerConstant{
    private static final long serialVersionUID = -2720226803547008794L;

    private static Map<Integer, TeamTypeConstant> map = new HashMap<Integer, TeamTypeConstant>();

    public static TeamTypeConstant SINGLE = new TeamTypeConstant("Single", 0);
    public static TeamTypeConstant DOUBLE = new TeamTypeConstant("Double", 1);
    public static TeamTypeConstant TEAM = new TeamTypeConstant("Team", 2);


    public TeamTypeConstant(String text, Integer value) {
        super(text, value);
        map.put(value, this);
    }

    public static TeamTypeConstant get(Integer type){
        return map.get(type);
    }
}