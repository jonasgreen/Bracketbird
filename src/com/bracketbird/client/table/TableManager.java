package com.bracketbird.client.table;

import com.google.gwt.user.client.ui.UIObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class TableManager {

    private List<Table> tables = new ArrayList<Table>();


    public void addTable(Table t) {
        tables.add(t);
    }

    public void up(Table table, int column) {
        int index = tables.indexOf(table);
        if (index == 0) {
            scrollUp();
            return;
        }
        Table t = tables.get(index - 1);
        t.setFocus(t.getRows().size() - 1, column);
    }


    public void down(Table table, int column) {
        int index = tables.indexOf(table);
        if (index == tables.size() - 1) {
            scrollDown();
            return;
        }
        Table t = tables.get(index + 1);
        t.setFocus(0, column);
    }


    public List<Table> getTables() {
        return tables;
    }

    public abstract void scrollUp();

    public abstract void scrollDown();

}
