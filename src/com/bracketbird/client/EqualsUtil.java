package com.bracketbird.client;

public class EqualsUtil {

    public static boolean equals(Object obj1, Object obj2){
        if(obj1 != null){
            return obj1.equals(obj2);
        }
        return obj2 == null;
    }
}
