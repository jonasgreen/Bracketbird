package com.bracketbird.clientcore.style;

import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class ButtonStyle extends Style{

    public static <W extends Widget> W primary(W w){
        return addStyleName(w, "button_primary");
    }
}
