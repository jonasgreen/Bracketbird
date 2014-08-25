package com.bracketbird.client.gui.main;

import com.bracketbird.client.browser.Browser;
import com.bracketbird.client.pages.frontpage.Slogan;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 *
 */
public class SloganComponent extends FlowPanel {

    public SloganComponent() {
        setStyleName("frontPage_slogans");
        addSlogans();
    }

    public void addSlogans() {
        if (Browser.isIEBrowser()) {
            Slogan w = new Slogan("Internet Explorer not supported", "This site has not been tested in Internet Explorer - Please run this site in Mozilla Firefox or Google Chrome. Sorry for the inconvenience.");
            w.getElement().getStyle().setBackgroundColor("red");
            add(w);
        }

        add(new Slogan("Group tournaments", "Set number of groups, points for a win, points for a draw, etc."));
        add(new Slogan("Knock-out tournaments", "Also supported."));
        add(new Slogan("Multi-stage tournaments", "Combine 'group' and 'knock-out' into a multi-stage tournament. Set number of teams progressing from one stage into the next."));
        add(new Slogan("Seeding", "Seed the teams or use random seeding."));
        add(new Slogan("No sign in, no installation, no fees", "Each tournament is uniquely identified by its url. Bookmark it and reopen it later. Or share it with others. It's 100% free"));
        add(new Slogan("Share tournaments in the cloud", "Update or follow the same tournament from unlimited number of devices. You can enter results on one laptop while following the tournament on a projector from another laptop."));
        add(new Slogan("Anything on your mind", "Feel free to contact me at jonasgreen12345@gmail.com."));
    }

}
