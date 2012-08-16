package com.bracketbird.clientcore.util;

/**
 *
 */
public class EqualsUtil {


    public static boolean equals(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }
        if (obj1 != null) {
            return obj1.equals(obj2);
        }
        return obj2.equals(obj1);
    }

    public static int compareTo(Comparable obj1, Comparable obj2) {
           if (obj1 == null && obj2 == null) {
            return 0;
        }
        if (obj1 != null) {
            //noinspection unchecked
            return obj1.compareTo(obj2);
        }
        //noinspection unchecked
        return obj2.compareTo(obj1);
    }

}
