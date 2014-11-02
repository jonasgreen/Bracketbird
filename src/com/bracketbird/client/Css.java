package com.bracketbird.client;

import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class Css {

    public static <W extends Widget> W style(W w, String... styles){
        int i = 0;
        for (String s : styles) {
            if(i++ == 0){
                w.setStyleName(s);
            }
            else{
                w.addStyleName(s);
            }
        }
        return w;
    }
}
