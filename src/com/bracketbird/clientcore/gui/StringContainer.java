package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class StringContainer extends DataContainer{
    
    private TextBoxComponent guiComponent = new TextBoxComponent();

    public StringContainer(String name, boolean mandatory) {
        super(name, mandatory);
    }

    public Widget getDataWidget() {
        return guiComponent.getTextBox();
    }

    public TextBoxComponent getGui() {
        return guiComponent;
    }

    public void clear() {
        guiComponent.setText("");
        setDataIsIllegal(false);        
    }

    public void setValue(Object obj) {
        setValue(obj == null ? "" : (String) obj);
    }

    public String getValue(){
        return isEmpty() ? null : guiComponent.getText();
    }

    public void setValue(String value){
        guiComponent.setText(value == null ? "" : value);
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
