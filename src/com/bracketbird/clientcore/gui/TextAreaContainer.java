package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.dom.client.FocusHandler;

/**
 *
 */
public class TextAreaContainer extends DataContainer {
    private TextAreaComponent guiComponent = new TextAreaComponent();

    public TextAreaContainer(String name, boolean mandatory) {
        super(name, mandatory);
    }

    public Widget getDataWidget() {
        return guiComponent.getTextArea();
    }

    public GuiComponent getGui() {
        return guiComponent;
    }

    public void clear() {
        guiComponent.setText("");
        setDataIsIllegal(false);
    }

    public void setValue(Object obj) {
        setValue((String) obj);
    }

    public String getValue() {
        return isEmpty() ? null : guiComponent.getText();
    }

    public void setValue(String value) {
        guiComponent.setText(value);
    }

    public boolean isEmpty() {
        return guiComponent.isEmpty();
    }

    @Override
    public void setFocus(boolean focus) {
        guiComponent.getTextArea().setFocus(focus);
    }

    public void enableWidget(boolean editable) {
        guiComponent.getTextArea().setReadOnly(true);
    }

    public void addFocusHandler(FocusHandler fh) {
        guiComponent.getTextArea().addFocusHandler(fh);
    }

    public void setVisible(boolean visible) {
        guiComponent.getTextArea().setVisible(visible);
        getLabel().getLabel().setVisible(visible);
    }


}
