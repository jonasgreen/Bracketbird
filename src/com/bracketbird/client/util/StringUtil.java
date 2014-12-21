package com.bracketbird.client.util;

import java.util.List;


/**
 *
 */
public class StringUtil{

    public static String commaSep(String ... vals) {
        StringBuffer sb =new StringBuffer();
        int index = 0;
        for (String val : vals) {
            if(val == null || val.equals("")){
                //ignore
            }
            else{
                if(index != 0 && sb.length() != 0){
                    sb.append(", ").append(val);
                }
                else{
                    sb.append(val);
                }
            }
            index++;
        }
        return sb.toString();
    }

    public static String capitalizeFirst(String s){
        if(isEmpty(s)){
            return s;
        }
        if(s.length() == 1){
            return s.toUpperCase();
        }
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }

    public static boolean isEmpty(String s){
        return s == null || s.length()== 0;
    }

    public static String valueOf(double d){
        return String.valueOf(d).replace('.', ',');
    }



    public static boolean equals(String s1, String s2) {
        if ((s1 == null || s1.equals("")) && (s2 == null || s2.equals(""))) {
            return true;
        }
        if (s1 != null) {
            return s1.equals(s2);
        }
        return s2.equals(s1);
    }

    public static void main(String[] args){

    }

    public static String toString(List l){
        StringBuffer sb = new StringBuffer("[");
        for (Object o : l) {
            sb.append("\n    ").append(o.toString());
        }
        sb.append("]\n");
        return sb.toString();
    }

    public static void append(){

    }


    
}
