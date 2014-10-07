package com.bracketbird.client.pages.frontpage;


import com.bracketbird.client.browser.Browser;
import com.bracketbird.clientcore.appcontrol.Page;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;


/**
 *
 */
public class FrontPage extends Page<FrontPageController> {

    public FrontPage() {
    }

    public void init() {
        setStyleName("frontPage");

        addLogo();
        addCreateTournamentPanel();
        addSlogans();
    }

    private void addLogo() {
        add(new Logo());
    }


    private void addCreateTournamentPanel() {
        FlowPanel p = new FlowPanel();
        p.addStyleName("frontPage_createTournamentPanel");

        Label l = new Label("Instant tournaments");
        l.setStyleName("frontPage_createTournamentPanel_title");
        p.add(l);
        p.add(getCreateTournamentButton());

        Label noAccountLabel = new Label("No account required");
        noAccountLabel.setStyleName("frontPage_createTournamentPanel_noAccountLabel");
        p.add(noAccountLabel);

        add(p);
    }


    public Label getCreateTournamentButton() {
        Label createTournament = new Label("Create a Tournament");
        createTournament.setStyleName("colorbutton3");
        createTournament.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                getController().createTournament();
            }
        });
        return createTournament;
    }

    private void addSlogans() {
        FlowPanel p = new FlowPanel();
        p.setStyleName("frontPage_slogans");

        add(p);

        if (Browser.isIEBrowser()) {
            Slogan w = new Slogan("Internet Explorer not supported", "This site has not been tested in Internet Explorer - Please run this site in Mozilla Firefox or Google Chrome. Sorry for the inconvenience.");
            w.getElement().getStyle().setBackgroundColor("red");
            add(w);
        }

        p.add(new Slogan("Group tournaments", "Set number of groups, points for a win, points for a draw, etc."));
        p.add(new Slogan("Knock-out tournaments", "Also supported."));
        p.add(new Slogan("Multi-stage tournaments", "Combine 'group' and 'knock-out' into a multi-stage tournament. Set number of teams progressing from one stage into the next."));
        p.add(new Slogan("Seeding", "Seed the teams or use random seeding."));
        p.add(new Slogan("No sign in, no installation, no fees", "Each tournament is uniquely identified by its url. Bookmark it and reopen it later. Or share it with others. It's 100% free"));
        p.add(new Slogan("Share tournaments in the cloud", "Update or follow the same tournament from unlimited number of devices. You can enter results on one laptop while following the tournament on a projector from another laptop."));
        p.add(new Slogan("Anything on your mind", "Feel free to contact me at jonasgreen12345@gmail.com."));

    }


}
