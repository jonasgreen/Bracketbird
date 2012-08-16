package com.bracketbird.clientcore.model;

/**
 *
 */
public class ClubVisabilityConstant extends IntegerConstant{
    private static final long serialVersionUID = -7687446125320724782L;

    public static ClubVisabilityConstant OPEN = new ClubVisabilityConstant("Open", 1);
    public static ClubVisabilityConstant CLOSED = new ClubVisabilityConstant("Closed", 2);


    public ClubVisabilityConstant(String text, Integer value) {
        super(text, value);
    }
}