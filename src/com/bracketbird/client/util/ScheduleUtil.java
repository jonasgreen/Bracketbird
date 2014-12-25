package com.bracketbird.client.util;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;

public class ScheduleUtil {

    private static ScheduleUtil instance;

    private ScheduleUtil() {
    }

    public static ScheduleUtil get() {
        if (instance == null) {
            instance = new ScheduleUtil();
        }
        return instance;
    }

    public void executeLater(int delayInMillis, final Command cmd){
        Timer timer = new Timer() {
            @Override
            public void run() {
                Scheduler.get().scheduleDeferred(cmd);
            }
        };
        timer.schedule(delayInMillis);
    }


}
