package com.bracketbird.client.gui.rtc;


import com.bracketbird.client.gui.rtc.matches.EnterResultsPageController;
import com.bracketbird.client.gui.rtc.settings.SettingsPageController;
import com.bracketbird.client.gui.rtc.teams.TeamsPageController;
import com.bracketbird.clientcore.appcontrol.Page;
import com.bracketbird.clientcore.gui.VerticalComponent;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;

/**
 *
 */
public class StartPage extends Page<StartPageController> {

    private VerticalComponent content;

    public StartPage() {
        super();
        content = new VerticalComponent();
        initWidget(content);
    }

    public void init() {
        StyleIt.add(content, RTCLayoutFac2.CONTENT.backgroundWhite());

        StartPageComponentManager manager = new StartPageComponentManager();
        content.add(manager.add(new StartPageComponent(TeamsPageController.getInstance(), "Add Teams", "Teams1.png", 0)));
        content.add(manager.add(new StartPageComponent(SettingsPageController.getInstance(), "Choose type of tournament", "Settings2.png", 1)), new TextLayout().paddingTop(30));
        content.add(manager.add(new StartPageComponent(EnterResultsPageController.getInstance(), "Start tournament", "play2.png", 2)), new TextLayout().paddingTop(30));
    }


    protected void setSubPageHolder(Page subPage) {
        //ignore
    }

    public VerticalComponent getContent() {
       return content;
    }
}