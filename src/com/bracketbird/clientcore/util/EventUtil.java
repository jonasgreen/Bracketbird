package com.bracketbird.clientcore.util;

import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.*;

import java.util.*;

/**
 *
 */
public class EventUtil {

    //repeating == 0 is no repeating

    public static Date getNextEventDate(Integer repeat, Date orgEventDate) {
        if (repeat == 0) {
            return orgEventDate;
        }
        else {

            if (repeat == RepeatsConstant.WEEKLY.getValue()) {
                return DateUtil.addDaysToDate(orgEventDate, 7);
            }
            else if (repeat == RepeatsConstant.MONTHLY.getValue()) {
                return DateUtil.addMonthsToDate(orgEventDate, 1);

            }
            else if (repeat == RepeatsConstant.QUARTER_YEARLY.getValue()) {
                return DateUtil.addMonthsToDate(orgEventDate, 4);

            }
            else if (repeat == RepeatsConstant.HALF_YEALY.getValue()) {
                return DateUtil.addMonthsToDate(orgEventDate, 6);

            }
            else if (repeat == RepeatsConstant.YEARLY.getValue()) {
                return DateUtil.addMonthsToDate(orgEventDate, 12);
            }
            else {
                throw new SystemException("RepeatConstant:" + repeat + " is not supported.");
            }
        }
    }


    public static Date getNextNotificationDate(Date eventDate, Integer notificationLevel) {
        if (notificationLevel != NotificationsByMailConstant.NO_NOTIFICATION.getValue()) {
            //calculate how many days before notification should occur
            return DateUtil.addDaysToDate(eventDate, -notificationLevel);
        }
        else {
            return null;
        }
    }
}
