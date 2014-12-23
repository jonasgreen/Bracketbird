package com.bracketbird.client.pages;

import com.bracketbird.client.pages.matches.MatchesPageController;
import com.bracketbird.client.pages.scores.ScoresPageController;
import com.bracketbird.client.pages.settings.SettingsPageController;
import com.bracketbird.client.pages.teams.TeamsPageController;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class MenuPanel extends FlowPanel{


    public MenuPanel() {

        setStyleName("menuPanel_outer");
        addStyleName("flex_alignItems_center");

        FlowPanel content = new FlowPanel();
        content.setStyleName("menuPanel_inner");
        content.addStyleName("flex_alignItems_baseline");
        add(content);

        //primary menu items to the left
        content.add(new MenuItem("TEAMS", TeamsPageController.getInstance()));
        content.add(new MenuItem("SETTINGS", SettingsPageController.getInstance()));
        content.add(new MenuItem("MATCHES", MatchesPageController.getInstance()));
        content.add(new MenuItem("SCORES", ScoresPageController.getInstance()));

        //secondary menu items to the right
        FlowPanel sp = new FlowPanel();
        sp.setStyleName("menuPanel_secondaryPanel");
        content.add(sp);
        sp.add(helpMenuItem());
        sp.add(shareMenuItem());
    }

    private Label helpMenuItem() {
        Label help = createSecondaryMenuItem("Help");
        help.setTitle("");
        help.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                //TODO
            }
        });

        return help;
    }


    private Label shareMenuItem(){
        Label share = createSecondaryMenuItem("Share");

        return share;
    }



    private Label createSecondaryMenuItem(String name) {
        Label l = new Label(name);
        l.setStyleName("menuItem_secondary");
        return l;
    }

    public void addShadow(boolean shadow){
        if(shadow){
            addStyleName("menuPanel_outer_shadow");
        }
        else{
            removeStyleName("menuPanel_outer_shadow");
        }
    }


}
