package com.bracketbird.client.table;


import com.bracketbird.clientcore.gui.GuiComponent;
import com.bracketbird.clientcore.gui.TextBoxComponent;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.event.dom.client.*;

/**
 *
 */
public class IntegerEditOnlyTableData extends EditOnlyTableData {

    private boolean isValid = true;

    public IntegerEditOnlyTableData(TableRow r) {
        super(r);
    }

    public IntegerEditOnlyTableData(TableRow r, String text) {
        super(r, text);

    }


    public String getText() {
        String value = super.getText();
        try {
            if (value != null && !value.trim().equals("")) {
                Integer i = Integer.valueOf(value);
            }
            isValid = true;
        }
        catch (Exception e) {
            isValid = false;
        }
        return value;
    }

    public void style(TextLayout tl) {
        StyleIt.add(this, tl);
        if (!isValid) {
            getElement().getStyle().setBorderColor("red");
        }
    }
}
