package com.bracketbird.client.gui.main.personal.help;

import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class HelpPage extends Page<HelpPageController> {


    private VerticalComponent content =new VerticalComponent();

    public HelpPage() {
        super();
         initWidget(content);

    }

    public void init() {
        content.add(new LabelComponent("Help"));
    }


    protected void setSubPageHolder(Page subPage) {
        //ignore
    }



}