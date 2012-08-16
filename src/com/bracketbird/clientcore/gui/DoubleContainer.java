package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Widget;
import com.bracketbird.clientcore.validation.Validator;

/**
 *
 */
public class DoubleContainer extends DataContainer {

    private TextBoxComponent guiComponent = new TextBoxComponent();

    public DoubleContainer(String name, boolean mandatory) {
        super(name, mandatory);
        add(Validator.DOUBLE_VALIDATOR);
    }

    public Widget getDataWidget() {
        return guiComponent.getTextBox();
    }

    public GuiComponent getGui() {
        return guiComponent;
    }

    public void clear() {
        guiComponent.setText("");
        setDataIsIllegal(false);
    }

    public void setValue(Object obj) {
        setValue((Double) obj);
    }

    public Double getValue() {
        if (isEmpty()) {
            return null;
        }
        String s = guiComponent.getText();
        return isEmpty() ? null : Double.valueOf(s.replace(',', '.'));
    }

    public void setValue(Double value) {
        String s = null;
        if (value == null) {
            s = "";
        }
        else {
            s = String.valueOf(value).replace('.', ',');
        }
        guiComponent.setText(s);
    }

    public boolean isEmpty() {
        return guiComponent.isEmpty();
    }

    public void addKeyDownHandler(KeyDownHandler handler) {
        guiComponent.addKeyDownHandler(handler);
    }

    @Override
    public void setFocus(boolean focus) {
        guiComponent.getTextBox().setFocus(focus);
    }

    public void enableWidget(boolean editable) {
        
    }

    public void addFocusHandler(FocusHandler fh) {
        guiComponent.getTextBox().addFocusHandler(fh);
    }

    public void setVisible(boolean visible) {
        guiComponent.getTextBox().setVisible(visible);
        getLabel().getLabel().setVisible(visible);
    }


}