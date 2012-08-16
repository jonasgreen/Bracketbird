package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 *
 */
public class HtmlWidget extends Composite {

    HTMLPanel panel;


    public HtmlWidget() {
        panel = new HTMLPanel("");
        initWidget(panel);
    }

    public void setHtml(String html){
        panel.getElement().setInnerHTML(html);
    }

    public void setStyleName(String styleName) {
        super.setStyleName(styleName);
    }

}
