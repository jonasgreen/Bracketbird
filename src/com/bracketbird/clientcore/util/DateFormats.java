package com.bracketbird.clientcore.util;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 *
 */
public class DateFormats {


    public static DateTimeFormat dateFormat_hours_minutes = DateTimeFormat.getFormat("HH:mm");
    public static DateTimeFormat dateFormat_wall = DateTimeFormat.getFormat("MMM d, HH:mm");
    public static DateTimeFormat dateFormat_events = DateTimeFormat.getFormat("EEE, MMM d, yyyy");

    public static DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yy hh:mm");
    public static DateTimeFormat dateFormat_tournament = DateTimeFormat.getFormat("yyyy_MM_dd");

}
