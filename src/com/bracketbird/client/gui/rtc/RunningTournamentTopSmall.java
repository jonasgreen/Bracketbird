package com.bracketbird.client.gui.rtc;

import com.bracketbird.client.LogoDiv;
import com.bracketbird.client.gui.rtc.help.AboutPageController;
import com.bracketbird.client.gui.rtc.help.HelpPageController;
import com.bracketbird.client.model.tournament.TournamentListener;
import com.bracketbird.client.model.tournament.TournamentNameChangedEvent;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.Horizontal;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.style.Vertical;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

/**
 *
 */
public class RunningTournamentTopSmall extends HorizontalComponent implements TournamentListener<TournamentNameChangedEvent> {

    private static RunningTournamentTopSmall instance;
    private LabelComponent tournamentName;
    private ImageComponent ejectOut;


    private RunningTournamentTopSmall() {
        super();
        RTC.getInstance().getTournament().addNameListener(this);

        init();
    }

    public static RunningTournamentTopSmall getInstance() {
        if (instance == null) {
            instance = new RunningTournamentTopSmall();

        }
        return instance;
    }


    private void init() {
        add(new LogoDiv(), new TextLayout("40px", "175px",Horizontal.LEFT, Vertical.TOP).paddingLeft(18).paddingBottom(0).paddingTop(0).paddingRight(20));
        add(getTournamentName(), new TextLayout(null, "100%", Horizontal.CENTER, Vertical.MIDDLE).sizeH1().italic().colorBlack().margin(0,0,0,-173).paddingTop(6));
        add(getEjectOut(), new TextLayout("20px", "20px",Horizontal.RIGHT, Vertical.MIDDLE).margin(0,20, 0,0));
        //setBackgroundColor();
    }

    public ImageComponent getEjectOut() {
        if (ejectOut == null) {
            ejectOut = new ImageComponent("ejectout.png");
            ejectOut.setTitle("Show top panel");
            ejectOut.getImage().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    RunningTournamentPageController.getInstance().ejectOut();
                }
            });
            ejectOut.getImage().addMouseOverHandler(MouseOver.POINTER);
        }
        return ejectOut;
    }

    public LabelComponent getTournamentName() {
        if (tournamentName == null) {
            tournamentName = new LabelComponent(RunningTournamentTop.getInstance().getTournamentName().getLabel().getText());
        }
        return tournamentName;
    }

    public void onChange(TournamentNameChangedEvent event) {
        getTournamentName().setText(RTC.getInstance().getTournament().getName());
        Window.setTitle(RTC.getInstance().getTournament().getName());
    }
}