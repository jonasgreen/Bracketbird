package com.bracketbird.client.gui.rtc.matches;

import com.bracketbird.client.table.*;
import com.bracketbird.clientcore.style.P;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;

/**
 *
 */
public class MatchHeaderRow extends TableRow{

    private static TextLayout normalLayout = new TextLayout().sizeNormal().add(P.BACKGROUND_WHITE).border(2).borderColor(P.BACKGROUND_WHITE).colorBaseDark().bold().italic();

    public static TextLayout colMatchNumber = MatchTableRow.colMatchNumber.clone().colorBaseDark().bold();
    public static TextLayout colTeamHome = MatchTableRow.colTeamHome.clone().colorBaseDark().bold();
    public static TextLayout colTeamSep = MatchTableRow.colTeamSep.clone().colorBaseDark().bold();
    public static TextLayout colTeamOut = MatchTableRow.colTeamOut.clone().colorBaseDark().bold();
    public static TextLayout colResult = MatchTableRow.colResult.clone().colorBaseDark().bold();
    public static TextLayout colField = MatchTableRow.colField.clone().colorBaseDark().bold();


    public MatchHeaderRow(Table table) {
        super(table, true);
        initColumns();
        paintFocusLost();
        StyleIt.add(this, new TextLayout().borderBottom(1).borderColor(P.BACKGROUND_C1));
    }

    private void initColumns() {
        addCell(new LabelCellData(this, "#"), colMatchNumber);
        addCell(new LabelCellData(this, "Team home"), colTeamHome);
        addCell(new LabelCellData(this, "-"), colTeamSep);
        addCell(new LabelCellData(this, "Team out"), colTeamOut);
        addCell(new LabelCellData(this, "Result"), colResult);
        addCell(new LabelCellData(this, "Field"), colField);
    }


    @Override
    public TextLayout getNormalLayout() {
        return normalLayout;
    }

    @Override
    public TextLayout getFocusLayout() {
        return normalLayout;
    }

    @Override
    public TextLayout getPassiveLayout() {
        return normalLayout;
    }

    @Override
    public void setFocus(boolean focus) {
        //ignore
    }

    @Override
    protected void commit(CellData cell) {
        //ignore
    }
}
