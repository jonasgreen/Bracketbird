package com.bracketbird.clientcore.util;

import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;

/**
 *
 */
public class MouseOver {
    public static MouseOverHandler POINTER = new MouseOverHandler() {
        public void onMouseOver(MouseOverEvent
                event) {
            event.getRelativeElement().getStyle().setProperty("cursor", "pointer");
        }
    };
}
