package com.bracketbird.client.gui.rtc.teams;


import com.bracketbird.client.gui.rtc.RTCLayoutFac2;
import com.bracketbird.client.gui.rtc.RunningTournamentPageController;
import com.bracketbird.client.gui.rtc.matches.SeedingPageController;
import com.bracketbird.client.gui.rtc.settings.SettingsPageController;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.client.table.TableManager;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class TeamsPage extends Page<TeamsPageController> {

    private VerticalComponent content;

    private LabelComponent header;
    private LabelComponent nextButton;


    private TournamentListener<TournamentStateChangedEvent> stateListener = new TournamentListener<TournamentStateChangedEvent>() {
        public void onChange(TournamentStateChangedEvent event) {

        }
    };
    private PopupPanel initPopup = new PopupPanel(true);
    private LabelComponent addTeam;
    private LabelComponent deleteTeam;
    private TeamsTable teamsTable;
    private LabelComponent editSeeding;


    public TeamsPage() {
        super();
        content = new VerticalComponent();
        initWidget(content);
    }

    public void init() {
        RTC.getInstance().getTournament().addStateListener(stateListener);

        StyleIt.add(content, RTCLayoutFac2.CONTENT.backgroundWhite());


        content.add(new LabelComponent("Teams"), RTCLayoutFac2.h1().margin(0, 0, 30, 0));

        //content.add(getHeader(), RTCLayoutFac.h1());
        //content.add(getParticipaters(), new TextLayout(null, "100%").paddingTop(30));


        content.add(getTeamsTable(), new TextLayout(null, "100%").margin(0,0,20, 0));

        FlowComponent hc = new FlowComponent();

        FlowComponent buttons = new FlowComponent();
        buttons.addLeft(getAddTeam(), new TextLayout(Vertical.MIDDLE));
        buttons.addLeft(getDeleteTeam(), new TextLayout(0, 0, 0, 0, Vertical.MIDDLE));
        buttons.addLeft(getEditSeeding(), new TextLayout(0, 0, 0, 0, Vertical.MIDDLE));

        hc.addLeft(buttons, null).getElement().getStyle().setPaddingTop(20, Style.Unit.PX);
        hc.addRigth(getNextButton(), null).getElement().getStyle().setPaddingTop(20, Style.Unit.PX);


        content.add(hc, new TextLayout(null, "100%").backgroundBase());



    }


    public PopupPanel getInitPopup() {
        return initPopup;
    }

    public LabelComponent getDeleteTeam() {
        if (deleteTeam == null) {
            deleteTeam = new LabelComponent("-");
            deleteTeam.setTitle("Delete team");
            deleteTeam.setStyleName("colorbutton4");
            deleteTeam.getLabel().addMouseOverHandler(MouseOver.POINTER);
            deleteTeam.getLabel().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    teamsTable.deleteLastFocusedTeam();
                }
            });

        }
        return deleteTeam;
    }


    public LabelComponent getAddTeam() {
        if (addTeam == null) {
            addTeam = new LabelComponent("+");
            addTeam.setTitle("Add team");
            addTeam.setStyleName("colorbutton4");
            addTeam.getLabel().addMouseOverHandler(MouseOver.POINTER);
            addTeam.getLabel().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    RTC.getInstance().createTeam();
                }
            });

        }
        return addTeam;
    }

    public LabelComponent getEditSeeding() {
        if (editSeeding == null) {
            editSeeding = new LabelComponent("Change seeding");
            editSeeding.setStyleName("colorbutton4");
            editSeeding.getLabel().addMouseOverHandler(MouseOver.POINTER);
            editSeeding.getLabel().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    PageFlow.popUp(SeedingPageController.getInstance());

                }
            });

        }
        return editSeeding;
    }

    public LabelComponent getNextButton() {
        if (nextButton == null) {
            nextButton = new LabelComponent("Continue >>");
            nextButton.setStyleName("colorbutton4");
            nextButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    PageFlow.show(SettingsPageController.getInstance());
                }
            });
        }
        return nextButton;
    }

    public TeamsTable getTeamsTable() {
        if (teamsTable == null) {
            TableManager tm = new TableManager() {
                @Override
                public void scrollUp() {
                    ScrollPanel scrollPanel = RunningTournamentPageController.getInstance().getPage().getScrollPanel().getScrollPanel();
                    scrollPanel.setVerticalScrollPosition(scrollPanel.getVerticalScrollPosition() - 40);
                }

                @Override
                public void scrollDown() {
                    ScrollPanel scrollPanel = RunningTournamentPageController.getInstance().getPage().getScrollPanel().getScrollPanel();
                    scrollPanel.setVerticalScrollPosition(scrollPanel.getVerticalScrollPosition() + 40);
                }
            };

            teamsTable = new TeamsTable(tm);

        }
        return teamsTable;
    }


    public LabelComponent getHeader() {
        if (header == null) {
            header = new LabelComponent("Teams");
        }
        return header;
    }

    protected void setSubPageHolder(Page subPage) {
        //ignore
    }


    public void setFocus(boolean focus) {
        getTeamsTable().setFocus(focus);
    }

}