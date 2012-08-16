package com.bracketbird.client.table;

/**
 *
 */
public interface TableRowNotifier {
    public void commit(String value, CellData cell);
    public void up(CellData cell);
    public void down(CellData cell);
    public void right(CellData cell);
    public void left(CellData cell);
    public void deleteRow(CellData cell);
    public void focusLost(CellData cell);
    public void focusGained(CellData cell);
}
