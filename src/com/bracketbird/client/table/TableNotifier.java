package com.bracketbird.client.table;

/**
 *
 */
public interface TableNotifier {
    public void commit(String value, TableRow row, CellData cell);
    public void up(TableRow row, CellData cell);
    public void down(TableRow row, CellData cell);
    public void right(TableRow row, CellData cell);
    public void left(TableRow row, CellData cell);
    public void backspace(TableRow row, CellData cell);
    public void enter(TableRow row, CellData cell);
    public void click(TableRow row, CellData cell);
    public void focusLost(TableRow row, CellData cell);
    public void focusGained(TableRow row, CellData cell);
}
