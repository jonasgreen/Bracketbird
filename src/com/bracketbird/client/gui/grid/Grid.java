package com.bracketbird.client.gui.grid;

import com.bracketbird.clientcore.gui.DataContainer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class Grid extends FlowPanel {

    public GridRow addRow() {
        GridRow row = new GridRow();
        add(row);
		return row;
    }


    public void addRow_50_50(Widget one, Widget two){
        GridRow row = addRow();
        row.addCell(ColWidth.col1_2, one);
        row.addCell(ColWidth.col1_2, two);
    }

    public void addRow_50_50(DataContainer dc){
        GridRow row = addRow();
        row.addCell(ColWidth.col1_2, dc.getLabel());
        row.addCell(ColWidth.col1_2, dc.getGui());
    }

    public void addRow(int colOne, int colTwo, DataContainer dc){
        this.addRow(colOne, colTwo, dc.getLabel(), dc.getGui());
    }


    public void addRow(int colWidthOne, int colWidthTwo, Widget one, Widget two){
        GridRow row = addRow();
        row.addCell(colWidthOne, one);
        row.addCell(colWidthTwo, two);
    }





}
