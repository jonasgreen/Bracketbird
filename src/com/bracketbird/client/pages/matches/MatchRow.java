package com.bracketbird.client.pages.matches;

import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.rtc.event.ModelEvent;
import com.bracketbird.client.rtc.event.ModelEventHandler;
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

    private MatchesTablePanel table;

    public MatchRow(Match match, MatchesTablePanel table) {
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

        match.matchHandlers.addHandler(new ModelEventHandler<Match>() {
            @Override
            public void handleEvent(ModelEvent<Match> event) {
                onMatchChange();
            }
        });

        onMatchChange();
        addDomHandler(  new ClickHandler() {
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
            matchNoLabel = new Label("" + match.getMatchNo());
            matchNoLabel.setStyleName("matchRow_no");
        }
        return matchNoLabel;
    }

    public Label getTeamHomeLabel() {
        if (teamHomeLabel == null) {
            teamHomeLabel = new Label(match.getTeamHome().getName());
            teamHomeLabel.setStyleName("matchRow_team");
            teamHomeLabel.addStyleName("flex_grow_1");

            match.getTeamHome().nameHandlers.addHandler(new ModelEventHandler<String>() {
                @Override
                public void handleEvent(ModelEvent<String> event) {
                    teamHomeLabel.setText(event.getNewValue());
                }
            });
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

            match.getTeamOut().nameHandlers.addHandler(new ModelEventHandler<String>() {
                @Override
                public void handleEvent(ModelEvent<String> event) {
                    teamOutLabel.setText(event.getNewValue());
                }
            });
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
        getResultTextBox().load(match.getResult());

        getTeamHomeLabel().setText(match.getTeamHome().getName());
        getTeamOutLabel().setText(match.getTeamOut().getName());

        //reset
        getTeamHomeLabel().removeStyleName("matchRow_team_loser");
        getTeamHomeLabel().removeStyleName("matchRow_team_winner");
        getTeamOutLabel().removeStyleName("matchRow_team_loser");
        getTeamOutLabel().removeStyleName("matchRow_team_winner");

        if(match.getResult() == null || match.getResult().isDraw()){
            return;
        }
        if(match.getResult().homeIsWinning()){
            getTeamHomeLabel().addStyleName("matchRow_team_winner");
            getTeamOutLabel().addStyleName("matchRow_team_loser");
        }
        else{
            getTeamHomeLabel().addStyleName("matchRow_team_loser");
            getTeamOutLabel().addStyleName("matchRow_team_winner");

        }


    }

}
