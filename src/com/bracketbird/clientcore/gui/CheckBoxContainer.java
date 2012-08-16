package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class CheckBoxContainer extends DataContainer {
    protected CheckBoxComponent checkBox;

    public CheckBoxContainer(String name, boolean mandatory) {
        super(name, mandatory);
        checkBox = new CheckBoxComponent(name);
    }

    public Widget getDataWidget() {
        return checkBox;
    }


    public GuiComponent getGui() {
        return checkBox;
    }

    public void clear() {
        checkBox.getCheckBox().setValue(false);
        setDataIsIllegal(false);
    }

    public boolean isEmpty() {
        return false;
    }

    public void setFocus(boolean focus) {
        checkBox.getCheckBox().setFocus(focus);
    }

    public void enableWidget(boolean editable) {
        
    }

    public void addFocusHandler(FocusHandler fh) {
        checkBox.getCheckBox().addFocusHandler(fh);
    }

    public void setValue(Object obj) {
        checkBox.getCheckBox().setValue((Boolean) obj);
    }

    public Boolean getValue() {
        return isEmpty() ? null : checkBox.getCheckBox().getValue();
    }

    public String getStringValue() {
        return String.valueOf(checkBox.getCheckBox().getValue());
    }

    public void setDataIsIllegal(boolean dataIsIllegal) {
        if (dataIsIllegal) {

            StyleIt.add(checkBox, P.COLOR_DARK_RED);
        }
        else {
            StyleIt.add(checkBox, P.COLOR_BLACK);
        }
    }

    public void setVisible(boolean visible) {
        checkBox.getCheckBox().setVisible(visible);
        getLabel().getLabel().setVisible(visible);
    }


}