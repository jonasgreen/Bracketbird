package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class StringAreaContainer extends DataContainer{

    private TextAreaComponent guiComponent = new TextAreaComponent();

    public StringAreaContainer(String name, boolean mandatory) {
        super(name, mandatory);
    }

    public Widget getDataWidget() {
        return guiComponent.getTextArea();
    }

    public TextAreaComponent getGui() {
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
        guiComponent.getTextArea().setFocus(focus);
    }

    protected void enableWidget(boolean editable) {
        guiComponent.getTextArea().setEnabled(editable);
    }

    public void addFocusHandler(FocusHandler fh){
        guiComponent.getTextArea().addFocusHandler(fh);
    }

    public void setVisible(boolean visible) {
        guiComponent.getTextArea().setVisible(visible);
        getLabel().getLabel().setVisible(visible);
    }


}
