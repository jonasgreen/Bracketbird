package com.bracketbird.client.gui.main.personal;

import com.bracketbird.client.gui.main.personal.personal.PersonalPageController;
import com.bracketbird.client.gui.main.personal.settings.SettingsPageController;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class HomePageMenuPanel extends AbstractMenuPanel {

    private HorizontalComponent content;


    public void addSectionTitle(String title) {
        //ignore
    }

    protected void add(MenuComponent mc) {

    }


    public HorizontalComponent getPanel() {
        if (content == null) {
            content = new HorizontalComponent();

            setup(content, PersonalPageController.getInstance());
            setup(content, SettingsPageController.getInstance());
         //   setup(content, HelpPageController.getInstance());

        }
        return content;
    }

    private void setup(HorizontalComponent hc, PageController pc) {
        addMenuItem(pc);
        hc.add(pc.getMenu(), new TextLayout(Vertical.BOTTOM).paddingRight(10).paddingLeft(10));
    }


}