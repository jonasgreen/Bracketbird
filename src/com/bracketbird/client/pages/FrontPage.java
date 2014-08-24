package com.bracketbird.client.pages;


import com.bracketbird.client.Logo;
import com.bracketbird.client.browser.Browser;
import com.bracketbird.client.gui.main.CreateTournamentPageController;
import com.bracketbird.client.gui.main.FrontPageController;
import com.bracketbird.client.gui.main.FrontSlogan;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.teams.TeamsPageController;
import com.bracketbird.client.service.BBService;
import com.bracketbird.client.service.TournamentResult;
import com.bracketbird.clientcore.appcontrol.Application;
import com.bracketbird.clientcore.appcontrol.Page;
import com.bracketbird.clientcore.appcontrol.RunningTournamentContext;
import com.bracketbird.clientcore.service.CallBack;
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
        Label createTournament = new Label("Create a tournament");
        createTournament.setStyleName("colorbutton3");
        createTournament.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                BBService.createTournament(new CallBack<TournamentResult>() {
                    @Override
                    public void success(TournamentResult r) {
                        Application.get().shiftApplicationContext(RunningTournamentContext.get());
                        Application.show(TeamsPageController.getInstance());
                        //RTC.getInstance().loadTournament(r.getTournament(), r.getEventLogs(), true);
                    }

                    @Override
                    public void fail(Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
        return createTournament;
    }

    private void addSlogans() {
        FlowPanel p = new FlowPanel();
        p.setStyleName("frontPage_slogans");

        add(p);

        if (Browser.isIEBrowser()) {
            FrontSlogan w = new FrontSlogan("Internet Explorer not supported", "This site has not been tested in Internet Explorer - Please run this site in Mozilla Firefox or Google Chrome. Sorry for the inconvenience.");
            w.getElement().getStyle().setBackgroundColor("red");
            add(w);
        }

        p.add(new FrontSlogan("Group tournaments", "Set number of groups, points for a win, points for a draw, etc."));
        p.add(new FrontSlogan("Knock-out tournaments", "Also supported."));
        p.add(new FrontSlogan("Multi-stage tournaments", "Combine 'group' and 'knock-out' into a multi-stage tournament. Set number of teams progressing from one stage into the next."));
        p.add(new FrontSlogan("Seeding", "Seed the teams or use random seeding."));
        p.add(new FrontSlogan("No sign in, no installation, no fees", "Each tournament is uniquely identified by its url. Bookmark it and reopen it later. Or share it with others. It's 100% free"));
        p.add(new FrontSlogan("Share tournaments in the cloud", "Update or follow the same tournament from unlimited number of devices. You can enter results on one laptop while following the tournament on a projector from another laptop."));
        p.add(new FrontSlogan("Anything on your mind", "Feel free to contact me at jonasgreen12345@gmail.com."));

    }


}
