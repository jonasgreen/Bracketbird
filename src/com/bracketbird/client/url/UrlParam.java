package com.bracketbird.client.url;;

/**
 *
 */
public class UrlParam {


    public static String CREATED_DATE = "CREATED_DATE";

    public static String CLUB_ID = "CLUB_ID";
    public static String CLUB_NAME = "CLUB_NAME";

    public static String MEMBER_ID = "MEMBER_ID";
    public static String TOURNAMENT_ID = "TOURNAMENT_ID";
    public static String TOURNAMENT_NAME = "TOURNAMENT_NAME";
    public static String USER_ID = "USER_ID";

    public static String INVALID_DATE = "INVALID_DATE";


    private StringBuffer sb = new StringBuffer("?");
    private boolean first = true;


    public UrlParam add(String key, String value) {
        if (!first) {
            sb.append("&");
        }
        first = false;
        sb.append(key).append("=").append(value);
        return this;
    }


    public String toString(){
        return sb.toString();
    }
}
