package com.bracketbird.clientcore.style;

import com.bracketbird.clientcore.gui.LabelComponent;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class TextStyle extends Style{


    public static <W extends Widget> W label(W w){
        return addStyleName(w, "text_label");
    }

    public static <W extends Widget> W title(W w){
        return addStyleName(w, "text_title");
    }


}
