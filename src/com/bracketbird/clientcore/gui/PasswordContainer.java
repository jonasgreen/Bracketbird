package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class PasswordContainer extends DataContainer {
    private PasswordComponent gui = new PasswordComponent();

    public PasswordContainer(String name, boolean mandatory) {
        super(name, mandatory);
    }

    public Widget getDataWidget() {
        return gui.getPasswordTextBox();
    }

    public PasswordComponent getGui() {
        return gui;
    }

    public void clear() {
        gui.getPasswordTextBox().setText("");
        setDataIsIllegal(false);        
    }

    public boolean isEmpty() {
        return gui.getPasswordTextBox().getText() == null || gui.getPasswordTextBox().getText().trim().equals("");
    }

    @Override
    public void setFocus(boolean focus) {
        gui.getPasswordTextBox().setFocus(focus);
    }

    public void enableWidget(boolean editable) {

    }

    public void addFocusHandler(FocusHandler fh) {
        gui.getPasswordTextBox().addFocusHandler(fh);
    }


    public void setValue(Object obj) {
        setValue((String) obj);
    }

    public String getValue() {
        return isEmpty() ? null : gui.getPasswordTextBox().getValue();
    }

    public void setValue(String value) {
        gui.getPasswordTextBox().setText(value);
    }

    public void addKeyDownHandler(KeyDownHandler keyDownHandler) {
        gui.addKeyDownHandler(keyDownHandler);
    }

    public void setVisible(boolean visible) {
        gui.getPasswordTextBox().setVisible(visible);
        getLabel().getLabel().setVisible(visible);
    }

}
