package com.bracketbird.client;

import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class StyleIt {

    public static void setStyles(Widget w, String...styles){
        int i = 0;
        for (String s : styles) {
            if(i++ == 0){
                w.setStyleName(s);
            }
            else{
                w.addStyleName(s);
            }
        }
    }
}
