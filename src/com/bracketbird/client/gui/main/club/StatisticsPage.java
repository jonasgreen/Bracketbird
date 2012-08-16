package com.bracketbird.client.gui.main.club;


import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class StatisticsPage extends Page<StatisticsPageController> {

    private VerticalComponent content;

    public StatisticsPage() {
        super();
        content = new VerticalComponent();
        initWidget(content);
    }

    public void init() {
    }

    protected void setSubPageHolder(Page subPage) {
        //ignore
    }


}