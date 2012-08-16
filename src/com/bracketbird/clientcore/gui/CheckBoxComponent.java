package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.CheckBox;

/**
 *
 */
public class CheckBoxComponent extends GuiComponent{

    private CheckBox checkBox;

    public CheckBoxComponent(String text) {
        this.checkBox = new CheckBox(text);
        initWidget(checkBox);
    }

    public CheckBox getCheckBox() {
        
        return checkBox;
    }

    public void addKeyDownHandler(KeyDownHandler handler){
        checkBox.addKeyDownHandler(handler);
    }
}
