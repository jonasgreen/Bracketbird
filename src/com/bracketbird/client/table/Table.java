package com.bracketbird.client.table;

import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.gui.TableHeader;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class Table extends FlowComponent {

    private static int count = 0;
    private final long id;
    protected TableRow lastFocus;
    private List<TableRow> rows = new ArrayList<TableRow>();
    private TableManager tableManager = null;
    private TableRow header;


    public List<TableRow> getRows() {
        return rows;
    }

    protected Table() {
        this(null);
    }

    protected Table(TableManager tm) {
        super();
        if (tm != null) {
            tm.addTable(this);
        }
        this.id = count++;
        this.tableManager = tm;
        StyleIt.add(this, new TextLayout().alignCenter());
    }

    public void addHeaderRow(TableRow header) {
        add(header, new TextLayout(null, "100%"));
        this.header = header;
        styleRow(header);
    }

    public void addTableRow(TableRow row) {
        rows.add(row);
        add(row, new TextLayout(null, "100%"));
        styleRow(row);
    }


    protected void styleRow(TableRow row) {
        row.getElement().getStyle().setTableLayout(Style.TableLayout.FIXED);
        DOM.setElementProperty(row.getElement(), "cellSpacing", "2");
        DOM.setElementProperty(row.getElement(), "cellPadding", "3");
    }


    public void down(TableRow row, int cellIndex) {
        int index = rows.indexOf(row);
        if (isLastRow(index)) {
            //call manager
            if (tableManager != null) {
                tableManager.down(this, cellIndex);
            }
            return;
        }
        else {
            TableRow tr = rows.get(index + 1);
            tr.getAllCells().get(cellIndex).setFocus(true);
        }
    }

    public boolean isLastRow(int index) {
        return index == rows.size() - 1;
    }

    public abstract void backspace(TableRow row, int cellIndex);

    public abstract void enter(TableRow row, int cellIndex);

    public void up(TableRow row, int cellIndex) {
        int index = rows.indexOf(row);
        if (index == 0) {
            //call manager
            if (tableManager != null) {
                tableManager.up(this, cellIndex);
            }
            return;
        }
        else {
            TableRow tr = rows.get(index - 1);
            tr.getAllCells().get(cellIndex).setFocus(true);
        }
    }

    public void deleteRow(int index) {
        TableRow remove = rows.remove(index);
        remove.removeFromParent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Table table = (Table) o;

        if (id != table.id) {
            return false;
        }

        return true;
    }


    public void setFocus(boolean focus) {
        if (rows == null || rows.isEmpty()) {
            return;
        }
        if (lastFocus != null) {
            lastFocus.setFocus(true);
        }
        else {
            setFocus(0, 0);
        }
    }

    public void setFocus(int row, int column) {
        rows.get(row).getAllCells().get(column).setFocus(true);
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public TableRow getLastFocus() {
        return lastFocus;
    }

    public void setLastFocus(TableRow lastFocus) {
        this.lastFocus = lastFocus;
    }

    public boolean isEmpty() {
        return rows.isEmpty();
    }

}
