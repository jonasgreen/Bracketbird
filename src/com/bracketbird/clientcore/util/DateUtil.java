package com.bracketbird.clientcore.util;


import com.google.gwt.user.datepicker.client.CalendarUtil;

import java.util.Date;

/**
 *
 */
public class DateUtil {

    static {
        CalendarUtil.getStartingDayOfWeek();
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public static boolean isToday(Date date) {
        Date d = new Date();
        return d.getYear() == date.getYear() && d.getMonth() == date.getMonth() && d.getDate() == date.getDate();
    }


    @SuppressWarnings({"UnusedDeclaration"})
    public static boolean isTomorrow(Date date) {
        Date d = new Date(new Date().getTime() + DateConstants.ONE_DAY);
        return d.getYear() == date.getYear() && d.getMonth() == date.getMonth() && d.getDate() == date.getDate();
    }

    public static Date today() {
        return new Date();
    }

    public static Date yesterDay() {
        return new Date(new Date().getTime() - DateConstants.ONE_DAY);
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public static Date tomorrow() {
        return new Date(new Date().getTime() + DateConstants.ONE_DAY);
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public static Date oneHourAgo() {
        return new Date(new Date().getTime() - DateConstants.ONE_HOUR);
    }

    //is dOne after dTwo

    @SuppressWarnings({"UnusedDeclaration"})
    public static boolean isAfter(Date dOne, Date dTwo) {
        return dOne.getTime() > dTwo.getTime();
    }


    /**
     * Adds the given number of days to a date.
     *
     * @param date the date
     * @param days number of days
     */
    @SuppressWarnings("deprecation")
    // GWT requires Date
    public static Date addDaysToDate(Date date, int days) {
        Date d = copyDate(date);
        d.setDate(date.getDate() + days);
        return d;
    }

    /**
     * Adds the given number of hours to a date.
     *
     * @param date the date
     * @param hours number of days
     */
    @SuppressWarnings("deprecation")
    // GWT requires Date
    public static Date addHoursToDate(Date date, int hours) {
        Date d = copyDate(date);
        d.setHours(date.getHours() + hours);
        return d;
    }


    /**
     * Adds the given number of months to a date.
     *
     * @param date   the date
     * @param months number of months
     */
    @SuppressWarnings("deprecation")
    // GWT requires Date
    public static Date addMonthsToDate(Date date, int months) {
        Date d = copyDate(date);

        if (months != 0) {
            int month = date.getMonth();
            int year = date.getYear();

            int resultMonthCount = year * 12 + month + months;
            int resultYear = resultMonthCount / 12;
            int resultMonth = resultMonthCount - resultYear * 12;

            d.setMonth(resultMonth);
            d.setYear(resultYear);

        }
        return d;
    }

    /**
     * Copies a date.
     *
     * @param date the date
     * @return the copy
     */
    public static Date copyDate(Date date) {
        if (date == null) {
            return null;
        }
        Date newDate = new Date();
        newDate.setTime(date.getTime());
        return newDate;
    }

    /**
     * Returns the number of days between the two dates. Time is ignored.
     *
     * @param start  starting date
     * @param finish ending date
     * @return the different
     */
    @SuppressWarnings({"UnusedDeclaration"})
    public static int getDaysBetween(Date start, Date finish) {
        // Convert the dates to the same time
        start = copyDate(start);
        resetTime(start);
        finish = copyDate(finish);
        resetTime(finish);

        long aTime = start.getTime();
        long bTime = finish.getTime();

        long adjust = 60 * 60 * 1000;
        adjust = (bTime > aTime) ? adjust : -adjust;

        return (int) ((bTime - aTime + adjust) / (24 * 60 * 60 * 1000));
    }


    /**
     * Check if two dates represent the same date of the same year, even if they
     * have different times.
     *
     * @param date0 a date
     * @param date1 a second date
     * @return true if the dates are the same
     */
    @SuppressWarnings("deprecation")
    // GWT requires Date
    public static boolean isSameDay(Date date0, Date date1) {
        return date0.getYear() == date1.getYear()
                && date0.getMonth() == date1.getMonth()
                && date0.getDate() == date1.getDate();
    }


    /**
     * Check if dOne's day is after dTwo. Time is not considered.
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    // GWT requires Date
    public static boolean isDayAfter(Date dOne, Date dTwo) {
        return isSameDay(dOne, dTwo) ? false : dOne.getTime() > dTwo.getTime();
    }

    
    @SuppressWarnings({"UnusedDeclaration"})
    public static boolean isSameDayOrAfter(Date d1, Date d2){
        return isSameDay(d1, d2) || isDayAfter(d1, d2);
    }





    /**
     * Sets a date object to be at the beginning of the month and no time
     * specified.
     *
     * @param date the date
     */
    @SuppressWarnings({"deprecation", "UnusedDeclaration"})
    // GWT requires Date
    public static void setToFirstDayOfMonth(Date date) {
        resetTime(date);
        date.setDate(1);
    }


    /**
     * Resets the date to have no time modifiers.
     *
     * @param date the date
     */
    @SuppressWarnings("deprecation")
    // GWT requires Date
    private static void resetTime(Date date) {
        long msec = date.getTime();
        msec = (msec / 1000) * 1000;
        date.setTime(msec);

        // Daylight savings time occurs at midnight in some time zones, so we reset
        // the time to noon instead.
        date.setHours(12);
        date.setMinutes(0);
        date.setSeconds(0);
    }


}
