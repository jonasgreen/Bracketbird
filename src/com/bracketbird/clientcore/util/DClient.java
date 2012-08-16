package com.bracketbird.clientcore.util;

import com.google.gwt.i18n.client.*;
import com.bracketbird.clientcore.model.*;

import java.util.*;

/**
 *
 */
public class DClient {
    public static DateTimeFormat clientServerProtocolFormat = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");


    public static Date get(CalendarDay cd){
        return clientServerProtocolFormat.parse(cd.getValue());
    }

    public static CalendarDay get(Date d){
        return new CalendarDay(clientServerProtocolFormat.format(d));
    }

    

}
