package com.bracketbird.clientcore.gui;

/**
 *
 */
public class AcceptTermsOfUseContainer extends CheckBoxContainer {
    private static String name = "Terms of service";


    public AcceptTermsOfUseContainer(String name, boolean mandatory) {
        super(name, mandatory);
    }


    public String getName() {
        return name;
    }


}