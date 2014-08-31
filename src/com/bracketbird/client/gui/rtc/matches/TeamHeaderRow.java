package com.bracketbird.client.gui.rtc.matches;

import com.bracketbird.client.gui.rtc.teams.TeamsTableRowOld;
import com.bracketbird.client.table.CellData;
import com.bracketbird.client.table.LabelCellData;
import com.bracketbird.client.table.Table;
import com.bracketbird.client.table.TableRow;
import com.bracketbird.clientcore.style.P;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;

/**
 *
 */
public class TeamHeaderRow extends TableRow{

    private static TextLayout normalLayout = new TextLayout().sizeNormal().add(P.BACKGROUND_WHITE).border(2).borderColor(P.BACKGROUND_WHITE).colorBaseDark().italic();

    public static TextLayout teamName = TeamsTableRowOld.teamLayout.clone().colorBaseDark();
    public static TextLayout comment = TeamsTableRowOld.commnetLaypout.clone().colorBaseDark();
    public static TextLayout seeding = TeamsTableRowOld.seedingLayout.clone().colorBaseDark();


    public TeamHeaderRow(Table table) {
        super(table, true);
        initColumns();
        paintFocusLost();
        StyleIt.add(this, new TextLayout().borderBottom(1).borderColor(P.BACKGROUND_C1));
    }

    private void initColumns() {
        addCell(new LabelCellData(this, "#"), seeding);
        addCell(new LabelCellData(this, "Team name"), teamName);
        addCell(new LabelCellData(this, "Comment"), comment);
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
    protected void commit(CellData cell) {
        //ignore
    }
}
