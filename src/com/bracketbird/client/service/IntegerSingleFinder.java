package com.bracketbird.client.service;

import java.io.Serializable;


/**
 *
 */
public class IntegerSingleFinder extends SingleFinder implements Serializable {
    private static final long serialVersionUID = -4782884003904572769L;

    private Integer value;

    public IntegerSingleFinder() {
        super();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public IntegerSingleFinder(String paramClass, String colNam, Integer value) {
        this(paramClass, colNam, Operator.EQUAL_TO, value);
    }

    public IntegerSingleFinder(String paramClass, String colNam, Operator operator, Integer value) {
        super(paramClass, colNam, operator);
        this.value = value;
    }



}