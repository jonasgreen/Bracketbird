package com.bracketbird.client.pages.teamspage;


import com.bracketbird.client.gui.rtc.teams.TeamsTable;
import com.bracketbird.client.gui.rtc.matches.SeedingPageController;
import com.bracketbird.client.gui.rtc.settings.SettingsPageController;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.client.pages.TournamentPage;
import com.bracketbird.client.table.TableManager;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class TeamsPage extends TournamentPage<TeamsPageController> {

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
       // content = new VerticalComponent();
       // add(content);
    }

    public void init() {
        super.init();
        FlowPanel panel = new FlowPanel();

        int i = 0;
        while(i++ < 1000){
            Label l = new Label("alksjdfælakjsd fælkajsfælakjf");
            panel.add(l);
        }
        getScrollPanelContent().add(panel);
        /*RTC.getInstance().getTournament().addStateListener(stateListener);

        //content.add(getParticipaters(), new TextLayout(null, "100%").paddingTop(30));
        content.add(getTeamsTable(), new TextLayout(null, "100%").margin(0,0,20, 0));
*/

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
                   Application.popUp(SeedingPageController.getInstance());

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
                    Application.show(SettingsPageController.getInstance());
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
//                    ScrollPanel scrollPanel = TournamentPageController.getInstance().getPage().getScrollPanel().getScrollPanel();
//                    scrollPanel.setVerticalScrollPosition(scrollPanel.getVerticalScrollPosition() - 40);
                }

                @Override
                public void scrollDown() {
//                    ScrollPanel scrollPanel = TournamentPageController.getInstance().getPage().getScrollPanel().getScrollPanel();
//                    scrollPanel.setVerticalScrollPosition(scrollPanel.getVerticalScrollPosition() + 40);
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