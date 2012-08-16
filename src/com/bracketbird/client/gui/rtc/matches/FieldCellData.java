package com.bracketbird.client.gui.rtc.matches;

import com.bracketbird.client.table.EditOnlyTableData;
import com.bracketbird.client.table.TableRow;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.user.client.Timer;

/**
 *
 */
public class FieldCellData extends EditOnlyTableData {

    private Timer focusTimer;


    public FieldCellData(TableRow r) {
        super(r);
    }

    public FieldCellData(TableRow r, String text) {
        super(r, text);
    }


    protected void handleFocus() {
        super.handleFocus();
        if (focusTimer == null) {
            focusTimer = new Timer() {
                @Override
                public void run() {
                    getTextBox().selectAll();
                    focusTimer = null;
                }
            };
            focusTimer.schedule(10);
        }
    }

    public void style(TextLayout tl) {
        super.style(tl);

    }

}
