package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Widget;
import com.bracketbird.clientcore.validation.Validator;

/**
 *
 */
public class IntegerContainer extends DataContainer{

    private TextBoxComponent guiComponent = new TextBoxComponent();

    public IntegerContainer(String name, boolean mandatory) {
        super(name, mandatory);
        add(Validator.INTEGER_VALIDATOR);
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
        setValue((Integer) obj);
    }

    public Integer getValue(){
        return isEmpty() ? null : Integer.valueOf(guiComponent.getText());
    }

    public void setValue(Integer value){
        guiComponent.setText(value == null ? "" : String.valueOf(value));
    }

    public boolean isEmpty(){
        return guiComponent.isEmpty();
    }

    public void addKeyDownHandler(KeyDownHandler handler){
        guiComponent.addKeyDownHandler(handler);
    }

    @Override
    public void setFocus(boolean focus) {
        guiComponent.getTextBox().setFocus(focus);
    }

    protected void enableWidget(boolean editable) {
        guiComponent.getTextBox().setEnabled(editable);
    }

    public void addFocusHandler(FocusHandler fh){
        guiComponent.getTextBox().addFocusHandler(fh);
    }

    public void setVisible(boolean visible) {
        guiComponent.getTextBox().setVisible(visible);
        getLabel().getLabel().setVisible(visible);
    }


}