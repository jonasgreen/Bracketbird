package com.bracketbird.clientcore.model;

import java.io.Serializable;

/**
 *
 */
public class CalendarDay implements Serializable{
    private static final long serialVersionUID = 4443205476953529120L;

	private String value;


    public CalendarDay() {
    }

    public CalendarDay(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
    @Override
    public String toString() {
        return "CalendarDay{" +
                "value='" + value + '\'' +
                '}';
    }
}
