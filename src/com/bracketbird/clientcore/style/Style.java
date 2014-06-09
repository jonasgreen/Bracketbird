package com.bracketbird.clientcore.style;

import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public abstract class Style {

    protected static <W extends Widget> W addStyleName(W w, String style){
        w.addStyleName(style);
        return w;
    }
}
