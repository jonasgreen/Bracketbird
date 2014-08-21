package com.bracketbird.client.gui.main;

import com.bracketbird.client.browser.Browser;
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
            FrontSlogan w = new FrontSlogan("Internet Explorer not supported", "This site has not been tested in Internet Explorer - Please run this site in Mozilla Firefox or Google Chrome. Sorry for the inconvenience.");
            w.getElement().getStyle().setBackgroundColor("red");
            add(w);
        }

        add(new FrontSlogan("Group tournaments", "Set number of groups, points for a win, points for a draw, etc."));
        add(new FrontSlogan("Knock-out tournaments", "Also supported."));
        add(new FrontSlogan("Multi-stage tournaments", "Combine 'group' and 'knock-out' into a multi-stage tournament. Set number of teams progressing from one stage into the next."));
        add(new FrontSlogan("Seeding", "Seed the teams or use random seeding."));
        add(new FrontSlogan("No sign in, no installation, no fees", "Each tournament is uniquely identified by its url. Bookmark it and reopen it later. Or share it with others. It's 100% free"));
        add(new FrontSlogan("Share tournaments in the cloud", "Update or follow the same tournament from unlimited number of devices. You can enter results on one laptop while following the tournament on a projector from another laptop."));
        add(new FrontSlogan("Anything on your mind", "Feel free to contact me at jonasgreen12345@gmail.com."));
    }

}
