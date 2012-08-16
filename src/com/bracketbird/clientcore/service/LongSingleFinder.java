package com.bracketbird.clientcore.service;

import java.io.Serializable;


/**
 *
 */
public class LongSingleFinder extends SingleFinder implements Serializable {
    private static final long serialVersionUID = -4782884003904572769L;

    private Long value;

    public LongSingleFinder() {
        super();
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public LongSingleFinder(String paramClass, String colNam, Long value) {
        this(paramClass, colNam, Operator.EQUAL_TO, value);
    }

    public LongSingleFinder(String paramClass, String colNam, Operator operator, Long value) {
        super(paramClass, colNam, operator);
        this.value = value;
    }



}