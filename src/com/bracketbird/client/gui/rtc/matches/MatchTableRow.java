package com.bracketbird.client.gui.rtc.matches;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.gui.rtc.event.REventListener;
import com.bracketbird.client.gui.rtc.event.UpdateMatchFieldEvent;
import com.bracketbird.client.gui.rtc.event.UpdateTeamNameEvent;
import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.model.tournament.MatchEvent;
import com.bracketbird.client.model.tournament.TournamentListener;
import com.bracketbird.client.table.*;
import com.bracketbird.clientcore.style.Name;
import com.bracketbird.clientcore.style.P;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.util.StringUtil;
import org.apache.bcel.generic.NEW;

/**
 *
 */
public class MatchTableRow extends TableRow implements TournamentListener<MatchEvent> {

    private static TextLayout focusLayout = new TextLayout().add(P.BACKGROUND_WHITE).border(2).borderColor(new P(Name.COLOR, "#29ABE2"));
    private static TextLayout normalLayout = new TextLayout().add(P.BACKGROUND_WHITE).border(2).borderColor(P.BACKGROUND_WHITE);
    private static TextLayout passivLayout = new TextLayout().add(P.BACKGROUND_F5).border(2).borderColor(P.BACKGROUND_F5);

    private static String COL_HEIGHT = "16px";

    public static TextLayout colMatchNumber = new TextLayout(COL_HEIGHT, "3%").alignLeft().sizeNormal().add(Name.COLOR, "#5C5C5C");
    public static TextLayout colTeamHome = new TextLayout(COL_HEIGHT, "30%").alignLeft().sizeNormal().add(Name.COLOR, "#5C5C5C");
    public static TextLayout colTeamSep = new TextLayout(COL_HEIGHT, "3%").alignLeft().sizeNormal().add(Name.COLOR, "#5C5C5C");
    public static TextLayout colTeamOut = new TextLayout(COL_HEIGHT, "30%").alignLeft().sizeNormal().add(Name.COLOR, "#5C5C5C");
    public static TextLayout colResult = new TextLayout(COL_HEIGHT, "20%").alignLeft().sizeNormal().add(Name.COLOR, "#5C5C5C");
    public static TextLayout colField = new TextLayout(COL_HEIGHT, "16%").alignLeft().sizeNormal().add(Name.COLOR, "#5C5C5C");


    private Match match;
    private LabelCellData matchNumberCol;
    private FieldCellData fieldCol;
    private ResultCellData resultCol;
    private LabelCellData teamOutCol;
    private LabelCellData teamSepCol;
    private LabelCellData teamHomeCol;

    public MatchTableRow(Table table, final Match match) {
        super(table, false);
        this.match = match;
        match.addMatchChangedListener(this);
        initColumns();
        paintFocusLost();
        match.addListener(new REventListener() {
            public void onChange(REvent<?, ?> event) {
                getFieldCol().setText(match.getField());
            }
        }, new UpdateMatchFieldEvent());
        onChange(null);
    }

    private void initColumns() {
        addCell(getMatchNumberCol(), colMatchNumber);
        addCell(getTeamHomeCol(), colTeamHome);
        addCell(getTeamSepCol(), colTeamSep);
        addCell(getTeamOutCol(), colTeamOut);
        addCell(getResultCol(), colResult);
        addCell(getFieldCol(), colField);
    }

    public LabelCellData getMatchNumberCol() {
        if (matchNumberCol == null) {
            matchNumberCol = new LabelCellData(this, match.getName());
        }
        return matchNumberCol;
    }

    public LabelCellData getTeamHomeCol() {
        if (teamHomeCol == null) {
            teamHomeCol = new LabelCellData(this, match.getTeamHome().getName());
            match.getTeamHome().addListener(new REventListener() {
                public void onChange(REvent<?, ?> event) {
                    teamHomeCol.setText(match.getTeamHome().getName());
                }
            }, new UpdateTeamNameEvent());
        }
        return teamHomeCol;
    }

    public LabelCellData getTeamSepCol() {
        if (teamSepCol == null) {
            teamSepCol = new LabelCellData(this, "-");
        }
        return teamSepCol;
    }

    public LabelCellData getTeamOutCol() {
        if (teamOutCol == null) {
            teamOutCol = new LabelCellData(this, match.getTeamOut().getName());
            match.getTeamOut().addListener(new REventListener() {
                public void onChange(REvent<?, ?> event) {
                    teamOutCol.setText(match.getTeamOut().getName());
                }
            }, new UpdateTeamNameEvent());
        }
        return teamOutCol;
    }

    public ResultCellData getResultCol() {
        if (resultCol == null) {
            resultCol = new ResultCellData(this, match);
        }
        return resultCol;
    }

    public FieldCellData getFieldCol() {
        if (fieldCol == null) {
            fieldCol = new FieldCellData(this);
        }
        return fieldCol;
    }

    @Override
    public TextLayout getNormalLayout() {
        return normalLayout;
    }

    @Override
    public TextLayout getFocusLayout() {
        return focusLayout;
    }

    @Override
    public TextLayout getPassiveLayout() {
        return passivLayout;
    }


    @Override
    protected void commit(CellData cell) {
        if (cell instanceof FieldCellData) {
            if (!StringUtil.equals(match.getField(), ((FieldCellData) cell).getText())) {
                RTC.getInstance().updateMatchField(match.getId(), ((FieldCellData) cell).getText());
            }
        }
    }


    public void onChange(MatchEvent event) {
        //updating result
        getResultCol().setText(match.resultAsString());
        getFieldCol().setText(match.getField());
        getTeamHomeCol().setText(match.getTeamHome().getName());
        getTeamOutCol().setText(match.getTeamOut().getName());
        //update teams

        if (match.isFinish()) {
            if (match.getWinningTeam().equals(match.getTeamHome())) {
                StyleIt.add(getTeamHomeCol(), MatchComponent.tWinnner);
                StyleIt.add(getTeamOutCol(), MatchComponent.tLoser);
            }
            else {
                StyleIt.add(getTeamHomeCol(), MatchComponent.tLoser);
                StyleIt.add(getTeamOutCol(), MatchComponent.tWinnner);
            }
        }
        else {
            StyleIt.add(getTeamHomeCol(), MatchComponent.tNormal);
            StyleIt.add(getTeamOutCol(), MatchComponent.tNormal);
        }


    }

}
