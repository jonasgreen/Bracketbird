package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class FlexTableComponent extends PanelComponent{

    protected FlexTable panel = new FlexTable();

    public FlexTableComponent() {
        super();
        initWidget(panel);
    }



    public FlexTable getPanel() {
        return panel;
    }


    public void setWidget(int row, int column, GuiComponent widget, Layout17 l){
        panel.setWidget(row, column, widget);
        widget.layout(l);
    }

    public void setWidget(int row, int column, DataContainer md, Layout17 l){
        setWidget(row, column, md.getGui(), l);
        add(md);
    }

    public void setLabel(int row, int column, DataContainer md, Layout17 l){
        setWidget(row, column, md.getLabel(), l);
    }


    public void setWidget(int row, int col, Widget label) {
        panel.setWidget(row, col, label);
    }

    public FlexTable.FlexCellFormatter getFlexCellFormatter() {
        return panel.getFlexCellFormatter();
    }
}
