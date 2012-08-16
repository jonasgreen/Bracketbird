package com.bracketbird.client.gui.main.club;


import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class ClubInfoPage extends Page<ClubInfoPageController> {


    private VerticalComponent content;

    public ClubInfoPage() {
        super();
        content = new VerticalComponent();
        initWidget(content);
    }

    public void init() {
        SimplePanelComponent sp = new SimplePanelComponent();
        sp.add(new LabelComponent("Club Info"), Layout.SUB_PAGE_HEADER);

        content.add(sp, new TextLayout(null, "100%").backgroundCompl());
        content.add(new SimplePanelComponent(), new TextLayout("100%", null));
    }

     protected void setSubPageHolder(Page subPage) {
        //ignore
    }
}