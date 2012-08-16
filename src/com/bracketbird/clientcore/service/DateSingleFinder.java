package com.bracketbird.clientcore.service;

import java.io.Serializable;
import java.util.Date;


/**
 *
 */
public class DateSingleFinder extends SingleFinder implements Serializable {
    private static final long serialVersionUID = -9201660323650579466L;

    private Date value;

    public DateSingleFinder() {
        super();
    }

    public Date getValue() {
        return value;
    }



    public DateSingleFinder(String paramClass, String colNam, Date value) {
        this(paramClass, colNam, Operator.EQUAL_TO, value);
    }

    public DateSingleFinder(String paramClass, String colNam, Operator operator, Date value) {
        super(paramClass, colNam, operator);
        this.value = value;
    }

    public void setValue(Date value) {
        this.value = value;
    }
}