package com.bracketbird.client.gui.main.personal.settings;

import com.bracketbird.client.gui.main.personal.personal.FacebookSettings;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class SettingsPage extends Page<SettingsPageController> {

    private VerticalComponent content;

    public SettingsPage() {
        super();
        content = new VerticalComponent();
        initWidget(content);
    }


    public String getHeader() {
        return "Your Account";
    }

    @Override
    public void init() {
        TextLayout tl = new TextLayout(0, 0, 0, 0, null, "100%");
        content.add(new SimplePanelComponent(), new TextLayout(20, 0, 0, 0));
        content.add(new ContactSettings(), tl);
        content.add(new PasswordSettings(), tl);
        content.add(new FacebookSettings(), tl);
        content.add(new SimplePanelComponent(), new TextLayout("100%", null));
    }

    protected void setSubPageHolder(Page subPage) {
        //ignore
    }



}