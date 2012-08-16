package com.bracketbird.clientcore.util;


import java.util.List;


/**
 *
 */
public class WriterIt {

    public static String toString(String startMsg, ToString target) {
        StringBuffer sb = new StringBuffer(startMsg).append("\n");
        target.toString(sb, 0);
        sb.append("\n");
        return sb.toString();
    }

    public static void append(StringBuffer sb, String name, List list, int tab) {
        appendTab(sb, tab);
        sb.append(name);
        if (list == null) {
            sb.append(" null");
        }
        else if (list.isEmpty()) {
            sb.append("[]");
        }
        else {
            sb.append("\n");
            for (Object obj : list) {
                if (obj instanceof ToString) {
                    ((ToString) obj).toString(sb, tab + 1);
                }
                else{
                    appendTab(sb, tab+1);
                    sb.append(obj.toString());
                    sb.append("\n");
                }
            }
        }
        sb.append("\n");
    }

    public static void append(StringBuffer sb, String name, Object value, int tab) {
            appendTab(sb, tab);
            sb.append(name);
            if (value == null) {
                sb.append(": null");
            }
            else {
                sb.append(": ").append(value.toString());
            }
            sb.append("\n");
        }


    public static void appendTab(StringBuffer sb, int i) {
        while (i > 0) {
            sb.append("    ");
            i--;
        }
    }


}
