package com.bracketbird.client.model;

import com.bracketbird.clientcore.model.*;

import java.util.Map;
import java.util.HashMap;

/**
 *
 */
public class UserStateConstant extends IntegerConstant {
    private static final long serialVersionUID = -5727688173016135340L;

    private static Map<Integer, UserStateConstant> map = new HashMap<Integer, UserStateConstant>();

    public static UserStateConstant LOGGED_OUT = new UserStateConstant("Logged out", 0);
    public static UserStateConstant LOGGED_IN = new UserStateConstant("Logged in", 1);
    public static UserStateConstant GUEST = new UserStateConstant("Guest", 2);
    public static UserStateConstant MEMBER = new UserStateConstant("Member", 3);
    public static UserStateConstant ADMIN = new UserStateConstant("Admin", 4);


    public UserStateConstant(String text, Integer value) {
        super(text, value);
        map.put(value, this);
    }

    public static UserStateConstant get(Integer type){
        return map.get(type);
    }
}