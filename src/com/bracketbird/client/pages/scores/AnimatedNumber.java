package com.bracketbird.client.pages.scores;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;

public class AnimatedNumber extends Label {

    private int number;
    private long interval = 100;
    private long maxInterval = 300;


    public AnimatedNumber(int number) {
        this.number = number;
        setText(number + "");
    }

    public void setValue(Integer value) {
        if (value == number) {
            return;
        }
        if (value > number) {
            animateUp(value);
        }
        else {
            animateDown(value);
        }
    }

    private void animateDown(Integer value) {
        long intervalToUse = calculateIntervalToUse(value);
        animateDown(intervalToUse, value);

    }

    private long calculateIntervalToUse(Integer value) {
        long intervalToUse = interval;
        if(number-value > 3){
            intervalToUse = maxInterval/(number-value);
        }
        return intervalToUse;
    }

    private void animateDown(final long intervalToUse, final Integer value) {
        if (number > value) {
            Timer timer = new Timer() {
                @Override
                public void run() {
                    Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                        @Override
                        public void execute() {
                            setText("" + --number);
                            animateDown(intervalToUse, value);
                        }
                    });
                }
            };
            timer.schedule((int) intervalToUse);
        }
    }

    private void animateUp(Integer value) {
        long intervalToUse = interval;
        if(value-number > 3){
            intervalToUse = maxInterval/(value-number);
        }
        animateUp(intervalToUse, value);
    }

    private void animateUp(final long intervalToUse, final int value) {
        if (number < value) {
            Timer timer = new Timer() {
                @Override
                public void run() {
                    Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                        @Override
                        public void execute() {
                            setText("" + ++number);
                            animateUp(intervalToUse, value);
                        }
                    });
                }
            };
            timer.schedule((int) intervalToUse);
        }
    }


}
