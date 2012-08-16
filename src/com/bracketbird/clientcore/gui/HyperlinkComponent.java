package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;

/**
 *
 */
public class HyperlinkComponent extends GuiComponent  implements MouseOverHandler {

    private Hyperlink hyperlink;

    public HyperlinkComponent(){
        this("");
    }


    public HyperlinkComponent(String text) {
        super();
        hyperlink = new Hyperlink();
        hyperlink.setText(text);
        initWidget(hyperlink);
    }

    public Hyperlink getHyperlink() {
        return hyperlink;
    }

    public void onMouseOver(MouseOverEvent event) {
        event.getRelativeElement().getStyle().setProperty("cursor", "pointer");
    }
}