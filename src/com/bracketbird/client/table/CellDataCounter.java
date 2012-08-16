package com.bracketbird.client.table;

/**
 *
 */
public class CellDataCounter {
    private static long count = 0;

    public static long getCount(){
        count = count+1;
        return count;
    }
}
