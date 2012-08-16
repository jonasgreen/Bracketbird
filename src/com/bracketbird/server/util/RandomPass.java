package com.bracketbird.server.util;

import java.util.Random;

/**
 *
 */
public class RandomPass {
    private static char[] passChars = new char[]{'A', 'B', 'C', 'D', 'E', 'a', 'b', 'c', 'd', 'e', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    private static char[] urlChars = new char[]{'a', 'b', 'c', 'd', 'e', 'f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','x','y','z'};

    private static Random r = new Random();

    public static String getPass(){
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (i++ < 8){
            sb.append(passChars[r.nextInt(passChars.length)]);
        }
        return sb.toString();
    }

    public static String getUrl(int length){
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (i++ < length){
            sb.append(urlChars[r.nextInt(urlChars.length)]);
        }
        return sb.toString();
    }

    
}
