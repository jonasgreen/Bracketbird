package com.bracketbird.client.pages.matches;


import com.bracketbird.client.model.Team;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FinalRankRow extends FlowPanel implements FinalRankingTeamHolder {

    private Team team;
    private Label teamName;
    private TextBox textBox;
    private String rank;

    public FinalRankRow(Team team, Integer aRank) {
        super();
        this.rank = aRank == null ? null : String.valueOf(aRank);
        this.team = team;
        init();
    }

    public FinalRankRow(Team team, String rank) {
        super();

        this.rank = rank;
        this.team = team;
        init();
    }


    private void init() {
        setStyleName("finalRanking_row");
        Widget w;
        if (rank == null) {
            w = getTextBox();
        }
        else{
            w = new Label(rank);
        }
        w.setStyleName("finalRanking_rank");
        add(w);
        add(getTeamName());
    }



    public TextBox getTextBox() {
        if (textBox == null) {
            textBox = new TextBox();
        }
        return textBox;
    }

    public Label getTeamName() {
        if (teamName == null) {
            teamName = new Label(team.getName());
            teamName.setStyleName("finalRanking_teamName");
        }
        return teamName;
    }


    public List<Team> getTeams() {
        List<Team> list = new ArrayList<Team>();
        list.add(team);
        return list;
    }

    public String validate() {
        return null;
    }

    public Team getTeam() {
        return team;
    }
}
