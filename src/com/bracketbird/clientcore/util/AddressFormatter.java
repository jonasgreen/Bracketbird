package com.bracketbird.clientcore.util;

import com.bracketbird.clientcore.model.*;

/**
 *
 */
public class AddressFormatter {


    public static String toolTip(Address adr) {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        add(sb, adr.getStreet());
        add(sb, adr.getCity());
        add(sb, adr.getCityCode());
        add(sb, adr.getNationalCode());
        sb.append("</html>");

        return sb.toString();
    }

    private static void add(StringBuffer sb, String s) {
        if (s == null || s.equals("")) {
            return;
        }
        if (sb.length() != 0) {
            sb.append("<br/>").append(s);
        }
        else {
            sb.append(s);
        }
    }

}
