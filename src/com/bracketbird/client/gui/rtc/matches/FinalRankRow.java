package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.model.*;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class FinalRankRow extends HorizontalComponent implements FinalRankingTeamHolder {


    private Team team;
    private LabelComponent teamName;
    private TextBoxComponent textBoxComponent;
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
        add(getTeamName(), new TextLayout(null, "100%", Horizontal.LEFT, Vertical.MIDDLE).sizeNormal().colorBaseDark().paddingRight(20));
        if (rank == null) {
            add(getTextBoxComponent(), new TextLayout("14px", "32px", Horizontal.RIGHT, Vertical.MIDDLE).sizeNormal().alignRight());
        }
        else{
            add(new LabelComponent(rank), new TextLayout(Horizontal.RIGHT, Vertical.MIDDLE).sizeNormal().colorBaseDark().alignRight().noWrap());
        }

    }



    public TextBoxComponent getTextBoxComponent() {
        if (textBoxComponent == null) {
            textBoxComponent = new TextBoxComponent();
            textBoxComponent.getTextBox().addFocusHandler(new FocusHandler() {
                public void onFocus(FocusEvent event) {
                    //StyleIt.add(FinalRankRow.this, P.BACKGROUND_BASE);
                }
            });

            textBoxComponent.getTextBox().addBlurHandler(new BlurHandler() {
                public void onBlur(BlurEvent event) {
                    //StyleIt.add(FinalRankRow.this, P.BACKGROUND_BASE_2);
                }
            });
        }
        return textBoxComponent;
    }

    public LabelComponent getTeamName() {
        if (teamName == null) {
            teamName = new LabelComponent(team.getName());
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
