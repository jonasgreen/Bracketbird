package com.bracketbird.client.pages.matches;

import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.gui.rtc.event.REventListener;
import com.bracketbird.client.gui.rtc.event.UpdateTeamNameEvent;
import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.model.tournament.MatchEvent;
import com.bracketbird.client.model.tournament.TournamentListener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class MatchRow extends FlowPanel {

    private Match match;

    private Label matchNoLabel;
    private Label teamHomeLabel;
    private Label teamOutLabel;
    private Label teamSep;

    private ResultBox resultTextBox;
    private TextBox fieldTextBox;

    private MatchesTable table;

    public MatchRow(Match match, MatchesTable table) {
        this.match = match;
        this.table = table;

        setStyleName("matchRow");
        addStyleName("flex_alignItems_center");


        add(getMatchNumberLabel());
        add(getTeamHomeLabel());
        add(getTeamSepLabel());
        add(getTeamOutLabel());
        add(getResultTextBox());
        //add(getFieldTextBox());

        match.addMatchChangedListener(new TournamentListener<MatchEvent>() {
            @Override
            public void onChange(MatchEvent event) {
                onMatchChange();
            }
        });

        onMatchChange();
        addDomHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                handleClick();
            }
        }, ClickEvent.getType());
    }

    private void handleClick() {
        getResultTextBox().setFocus(true);
    }


    public Label getMatchNumberLabel() {
        if (matchNoLabel == null) {
            matchNoLabel = new Label(match.getName());
            matchNoLabel.setStyleName("matchRow_no");
        }
        return matchNoLabel;
    }

    public Label getTeamHomeLabel() {
        if (teamHomeLabel == null) {
            teamHomeLabel = new Label(match.getTeamHome().getName());
            teamHomeLabel.setStyleName("matchRow_team");
            teamHomeLabel.addStyleName("flex_grow_1");
            match.getTeamHome().addListener(new REventListener() {
                public void onChange(REvent<?, ?> event) {
                    teamHomeLabel.setText(match.getTeamHome().getName());
                }
            }, new UpdateTeamNameEvent());
        }
        return teamHomeLabel;
    }

    public Label getTeamSepLabel() {
        if (teamSep == null) {
            teamSep = new Label("-");
            teamSep.setStyleName("matchRow_sep");
        }
        return teamSep;
    }

    public Label getTeamOutLabel() {
        if (teamOutLabel == null) {
            teamOutLabel = new Label(match.getTeamOut().getName());
            teamOutLabel.setStyleName("matchRow_team");
            teamOutLabel.addStyleName("flex_grow_1");
            match.getTeamOut().addListener(new REventListener() {
                public void onChange(REvent<?, ?> event) {
                    teamOutLabel.setText(match.getTeamOut().getName());
                }
            }, new UpdateTeamNameEvent());
        }
        return teamOutLabel;
    }

    public ResultBox getResultTextBox() {
        if (resultTextBox == null) {
            resultTextBox = new ResultBox(match, table, this);
        }
        return resultTextBox;
    }

    public TextBox getFieldTextBox() {
        if (fieldTextBox == null) {
            fieldTextBox = new TextBox();
            fieldTextBox.setStyleName("matchRow_field");
            fieldTextBox.getElement().setAttribute("placeholder", "Field");
        }
        return fieldTextBox;
    }


    public void onMatchChange() {
        //updating result
        //getResultTextBox().setText(match.resultAsString());
        getFieldTextBox().setText(match.getField());
        getTeamHomeLabel().setText(match.getTeamHome().getName());
        getTeamOutLabel().setText(match.getTeamOut().getName());
        //update teams

        if (match.isFinish()) {
            if (match.getWinningTeam().equals(match.getTeamHome())) {
                //StyleIt.add(getTeamHomeCol(), MatchComponent.tWinnner);
                //StyleIt.add(getTeamOutCol(), MatchComponent.tLoser);
            }
            else {
                //StyleIt.add(getTeamHomeCol(), MatchComponent.tLoser);
                //StyleIt.add(getTeamOutCol(), MatchComponent.tWinnner);
            }
        }
        else {
            //StyleIt.add(getTeamHomeCol(), MatchComponent.tNormal);
            //StyleIt.add(getTeamOutCol(), MatchComponent.tNormal);
        }


    }

}