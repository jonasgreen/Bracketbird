package com.bracketbird.server.util;

import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.CalendarDay;
import com.bracketbird.clientcore.util.*;

import java.text.*;
import java.util.*;

/**
 *
 */
public class DServer {
    public static SimpleDateFormat clientServerProtocolFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static SimpleDateFormat eventMail = new SimpleDateFormat("EEE d MMM yyyy");



    public static Date get(CalendarDay cd){
        try {
            return clientServerProtocolFormat.parse(cd.getValue());
        }
        catch (ParseException e) {
            throw new SystemException(e.getMessage());
        }
    }

    public static CalendarDay get(Date d){
        if(d == null){
            return null;
        }
        return new CalendarDay(clientServerProtocolFormat.format(d));
    }

    public static Date shiftTimeZone(Date d, Integer timeZoneOffset){
        return DateUtil.addHoursToDate(d, timeZoneOffset*(-1));
    }

}
