package com.bracketbird.clientcore.model;

import java.io.*;

/**
 *
 */
@SuppressWarnings({"UnusedAssignment"})
public abstract class IntegerConstant implements Serializable{

    private static final long serialVersionUID = 7355933060387314797L;

    private Integer value;
    private String text;

    protected IntegerConstant() {
    }

    public IntegerConstant(String text, Integer value) {
        this.text = text;
        this.value = value;
    }



    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }



}
