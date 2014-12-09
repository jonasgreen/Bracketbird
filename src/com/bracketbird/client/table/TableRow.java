package com.bracketbird.client.table;

import com.bracketbird.clientcore.gui.HorizontalComponent;
import com.bracketbird.clientcore.style.P;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.KeyDownEvent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class TableRow extends HorizontalComponent {
    private static int count = 0;
    private int id;
    protected CellData lastFocus;

    private boolean isHeader = false;

    private List<CellData> allCells = new ArrayList<CellData>();
    private Table table;
    private boolean hasFocus = false;

    public TableRow(Table table, boolean isHeader) {
        this.table = table;
        this.id = count++;
        this.isHeader = isHeader;
    }

    public void addCell(CellData cell, TextLayout tl) {
        allCells.add(cell);
        add(cell.getComponent(), tl);
        cell.getComponent().getElement().getStyle().setWidth(100, Style.Unit.PCT);


    }

    public abstract TextLayout getNormalLayout();

    public abstract TextLayout getFocusLayout();

    public abstract TextLayout getPassiveLayout();

    @Override
    public void setFocus(boolean focus) {
        if (allCells.isEmpty()) {
            return;
        }
        if (lastFocus != null) {
            lastFocus.setFocus(true);
        }
        else {
            allCells.get(0).setFocus(true);
        }
    }

    protected abstract void commit(CellData cell);

    public int getId() {
        return id;
    }

    public List<CellData> getAllCells() {
        return allCells;
    }


    public void up(CellData cell) {
        table.up(this, allCells.indexOf(cell));
    }

    public void down(CellData cell) {
        table.down(this, allCells.indexOf(cell));
    }

    public void right(CellData cell, KeyDownEvent event) {
        event.preventDefault();
        event.stopPropagation();
        int index = allCells.indexOf(cell) + 1;
        while (index < allCells.size()) {
            CellData c = allCells.get(index++);
            if (c.isFocusable()) {
                c.setFocus(true);
                return;
            }
        }
        cell.setFocus(true);
    }

    public void left(CellData cell, KeyDownEvent event) {
        event.preventDefault();
        event.stopPropagation();
        int index = allCells.indexOf(cell) - 1;
        while (index >= 0) {
            CellData c = allCells.get(index--);
            if (c.isFocusable()) {
                c.setFocus(true);
                return;
            }
        }
        cell.setFocus(true);
    }

    public void backspace(CellData cell) {
        table.backspace(this, allCells.indexOf(cell));
    }

    public void enter(CellData cell) {
        table.enter(this, allCells.indexOf(cell));
    }

    public void focusLost(CellData cell) {
        commit(cell);
        paintFocusLost();
    }

    public void focusGained(CellData cell) {
        for (CellData cellData : allCells) {
            if (cellData.equals(cell)) {
                cellData.style(getFocusLayout());
            }
            else {
                cellData.style(getPassiveLayout());
            }
        }
        hasFocus = true;
        table.setLastFocus(this);
        lastFocus = cell;
    }


    public void paintFocusLost() {
        for (CellData cell : allCells) {
            cell.style(getNormalLayout());
        }
        hasFocus = false;
    }


    public void focusNext(CellData cellData) {
        int index = allCells.indexOf(cellData);
        //going rigth for focus
        int tempIndex = index + 1;
        while (tempIndex < allCells.size() - 1) {
            CellData cell = allCells.get(tempIndex++);
            if (cell.isFocusable()) {
                cell.setFocus(true);
                return;
            }
        }
        //going left for focus
        index--;
        while (index >= 0) {
            CellData cell = allCells.get(index--);
            if (cell.isFocusable()) {
                cell.setFocus(true);
                return;
            }
        }
    }


    public boolean isHeader() {
        return isHeader;
    }

    public boolean isHasFocus() {
        return hasFocus;
    }

}
