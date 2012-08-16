package com.bracketbird.clientcore.model;

/**
 *
 */
public class ClubConstant extends IntegerConstant{
    private static final long serialVersionUID = -5727688173016135340L;

    public static ClubConstant NO_CLUB = new ClubConstant("No club", 0);
    public static ClubConstant TABLE_SOCCER = new ClubConstant("Tablesoccer", 1);


    public ClubConstant(String text, Integer value) {
        super(text, value);
    }
}