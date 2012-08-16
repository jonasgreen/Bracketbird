package com.bracketbird.client.gui.main.club;

import com.bracketbird.client.gui.main.club.home.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class ClubHomePageMenuPanel extends AbstractMenuPanel {

    private HorizontalComponent content;


    public void addSectionTitle(String title) {
        //ignore
    }

    protected void add(MenuComponent mc) {

    }


    public HorizontalComponent getPanel() {
        if (content == null) {
            content = new HorizontalComponent();

            setup(content, ClubHomePageController.getInstance());
            //setup(content, ForumPageController.getInstance());
            setup(content, MembersPageController.getInstance());
            setup(content, StatisticsResultTournamentPageController.getInstance());
            setup(content, AdminPageController.getInstance());

        }
        return content;
    }

    private void setup(HorizontalComponent hc, PageController pc) {
        addMenuItem(pc);
        hc.add(pc.getMenu(), new TextLayout(Vertical.BOTTOM).paddingRight(10).paddingLeft(10));
    }


}