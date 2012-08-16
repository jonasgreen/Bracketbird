package com.bracketbird.clientcore.gui;

import com.bracketbird.clientcore.style.Name;
import com.bracketbird.clientcore.style.P;
import com.bracketbird.clientcore.style.StyleIt;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 *
 */
public class NoteBar extends PopupPanel {

    public NoteBar() {
        super();
        StyleIt.add(this, P.BACKGROUND_MENU_LIGHT);
        StyleIt.add(this, Name.BORDER, "NONE");
    }


    public void start(String text) {
        StyleIt.add(this, P.BACKGROUND_MENU_LIGHT);
        setPopupPosition(250, Window.getScrollTop());


    }



}
