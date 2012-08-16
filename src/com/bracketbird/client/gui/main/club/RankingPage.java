package com.bracketbird.client.gui.main.club;

import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class RankingPage extends Page<RankingPageController> {

    private VerticalComponent content;

    public RankingPage() {
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