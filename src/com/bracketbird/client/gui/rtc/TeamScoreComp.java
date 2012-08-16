package com.bracketbird.client.gui.rtc;

import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.gui.LabelComponent;
import com.bracketbird.clientcore.style.P;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;

/**
 *
 */
public class TeamScoreComp extends FlowComponent {

    private Team team;

    private LabelComponent teamName;
    private FlowComponent scoreComp;

    public TeamScoreComp(Team team) {
        super();
        this.team = team;
        getTeamName().setText(parseName(team));
        add(getTeamName(), new TextLayout().colorWhite().sizeNormal().padding(4).alignLeft());
        StyleIt.add(this, new TextLayout().border(1).borderColor(P.BACKGROUND_BLACK));
    }

    public void update(Match m, Team newTeam) {
        team = newTeam;
        getTeamName().setText(parseName(team));
        if (m.isFinish()) {
            if (m.getWinningTeam().equals(team)) {
                updateFinishedWinning(m);
            }
            else {
                updateFinishedLosing(m);
            }
        }
        else {
            updateNonFinished(m);
        }
    }

    private void updateNonFinished(Match m) {
        setBackgroundColor(P.BACKGROUND_BLACK);
        StyleIt.add(this, new TextLayout().border(1).borderColor(P.BACKGROUND_BLACK));
        getTeamName().getElement().getStyle().setColor("white");

    }

    private void updateFinishedWinning(Match m) {
        setBackgroundColor(P.BACKGROUND_BLACK);
        StyleIt.add(this, new TextLayout().border(1).borderColor(P.BACKGROUND_BLACK));
        getTeamName().getElement().getStyle().setColor(P.BACKGROUND_BLUE.getValue());
    }

    private void updateFinishedLosing(Match m) {
        setBackgroundColor(P.BACKGROUND_BLACK);
        StyleIt.add(this, new TextLayout().border(1).borderColor(P.BACKGROUND_BLACK));
        getTeamName().getElement().getStyle().setColor("white");
    }


    public LabelComponent getTeamName() {
        if (teamName == null) {
            teamName = new LabelComponent("");
        }
        return teamName;
    }

    public FlowComponent getScoreComp() {
        if (scoreComp == null) {
            scoreComp = new FlowComponent();
        }
        return scoreComp;
    }

    private String parseName(Team team){
        if(team == null || team.isEmpty() || team.isSeedingTeam()){
            return "";
        }
        return team.getName();
    }


}
