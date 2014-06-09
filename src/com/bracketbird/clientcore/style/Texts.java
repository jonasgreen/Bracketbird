package com.bracketbird.clientcore.style;

import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class Texts {

    public static Label label(String text){
        return TextStyle.label(create(text));
    }

    public static Label title(String text) {
        return TextStyle.title(create(text));
    }






    private static Label create(String text){
        return new Label(text);
    }

}
