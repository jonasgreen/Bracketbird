package com.bracketbird.client.gui.rtc.ranking;

import com.bracketbird.client.model.tournament.*;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class MatchView extends FlowPanel {

    private static String TEAM_UPCOMING = "aMatchView_teamPanel_upcoming";
    private static String TEAM_INPROGRESS = "aMatchView_teamPanel_inProgress";
    private static String TEAM_FINISHED = "aMatchView_teamPanel_finished";

    private static String INFO_UPCOMING = "aMatchView_infoPanel_upcoming";
    private static String INFO_INPROGRESS = "aMatchView_infoPanel_inProgress";
    private static String INFO_FINISHED = "aMatchView_infoPanel_finished";


    private Match match;
    private MatchesViewGrouping parent;

    private FlowPanel teamPanel = new FlowPanel();
    private FlowPanel infoPanel = new FlowPanel();

    public MatchView(Match match) {
        this.match = match;
        setStyleName("aMatchView");
        addStyleName("aMatchView_opacity");

        match.addMatchChangedListener(new TournamentListener<MatchEvent>() {
            @Override
            public void onChange(MatchEvent event) {
                matchChanged(event.getMatch());
            }
        });


        teamPanel.setStyleName("aMatchView_teamPanel");
        infoPanel.setStyleName("aMatchView_infoPanel");

        add(infoPanel);
        add(teamPanel);

        style();
    }

    public void setParent(MatchesViewGrouping parent) {
        this.parent = parent;
    }

    private void matchChanged(Match match) {
        parent.matchChanged(this);
        style();
    }

    private void style() {
        if (match.isFinish()) {
            styleAsFinished();
        }
        else if (match.isInProgress()) {
            styleAsInProgress();
        }
        else {
            styleAsUpcoming();
        }
    }


    public void styleAsFinished() {
        teamPanel.clear();
        if (match.homeIsWinning()) {
            teamPanel.add(new HTML(asWinnerName(match.getTeamHome().getName()) + " / " + match.getTeamOut().getName()));
        }
        else if (match.outIsWinning()) {
            teamPanel.add(new HTML(match.getTeamHome().getName() + " / " + asWinnerName(match.getTeamOut().getName())));
        }
        else {
            teamPanel.add(new HTML(match.getTeamHome().getName() + " / " + match.getTeamOut().getName()));
        }

        removeStyles(teamPanel, TEAM_INPROGRESS, TEAM_UPCOMING);
        teamPanel.addStyleName(TEAM_FINISHED);

        infoPanel.clear();
        infoPanel.add(new HTML(match.getResult().asString()));
        removeStyles(infoPanel, INFO_INPROGRESS, INFO_UPCOMING);
        infoPanel.addStyleName(INFO_FINISHED);
    }

    public void styleAsInProgress() {
        teamPanel.clear();
        teamPanel.add(new HTML(match.getTeamHome().getName() + " / " + match.getTeamOut().getName()));
        removeStyles(teamPanel, TEAM_FINISHED, TEAM_UPCOMING);
        teamPanel.addStyleName(TEAM_INPROGRESS);

        infoPanel.clear();
        infoPanel.add(new Label(match.getField()));
        removeStyles(infoPanel, INFO_FINISHED, INFO_UPCOMING);
        infoPanel.addStyleName(INFO_INPROGRESS);
    }

    public void styleAsUpcoming() {
        teamPanel.clear();
        teamPanel.add(new HTML(match.getTeamHome().getName() + " / " + match.getTeamOut().getName()));
        removeStyles(teamPanel, TEAM_INPROGRESS, TEAM_FINISHED);
        teamPanel.addStyleName(TEAM_UPCOMING);

        infoPanel.clear();
        removeStyles(infoPanel, INFO_FINISHED, INFO_INPROGRESS);
        infoPanel.addStyleName(INFO_UPCOMING);
    }

    public Match getMatch() {
        return match;
    }

    public void setTop(int top) {
        getElement().getStyle().setTop(top, Style.Unit.PX);
    }

    public void setOpacity(double opacity) {
        getElement().getStyle().setOpacity(opacity);
    }

    private void removeStyles(Widget w, String... styles) {
        for (String style : styles) {
            w.removeStyleName(style);
        }
    }

    private String asWinnerName(String name) {
        return "<span style='color: rgb(27,133,206);'>" + name + "</span>";
    }

    public FlowPanel getTeamPanel() {
        return teamPanel;
    }
}
