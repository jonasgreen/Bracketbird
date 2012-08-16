package com.bracketbird.client.gui.main.club;


import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class AdminPage extends Page<AdminPageController> {

    private VerticalComponent content;

    public AdminPage() {
        super();
        content = new VerticalComponent();
        initWidget(content);
    }

    public void init() {
        content.add(new LabelComponent("Admin page"), new TextLayout().sizeH1().colorLink());
    }

    protected void setSubPageHolder(Page subPage) {
        //ignore
    }


}