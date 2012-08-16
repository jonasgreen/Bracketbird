package com.bracketbird.clientcore.service;

import java.io.Serializable;


/**
 *
 */
public class BooleanSingleFinder extends SingleFinder implements Serializable {
    private static final long serialVersionUID = -309967250388759139L;

    private Boolean value;

    public BooleanSingleFinder() {
        super();
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public BooleanSingleFinder(String paramClass, String colNam, Boolean value) {
        this(paramClass, colNam, Operator.EQUAL_TO, value);
    }

    public BooleanSingleFinder(String paramClass, String colNam, Operator operator, Boolean value) {
        super(paramClass, colNam, operator);
        this.value = value;
    }



}