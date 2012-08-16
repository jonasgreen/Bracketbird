package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.event.dom.client.KeyDownHandler;

/**
 *
 */
public class PasswordComponent extends GuiComponent{

    private PasswordTextBox passwordTextBox = new PasswordTextBox();

    public PasswordComponent() {
        super();
        initWidget(passwordTextBox);
    }


    public PasswordTextBox getPasswordTextBox() {
        return passwordTextBox;
    }

    public void setValue(Object obj) {
        passwordTextBox.setText((String)obj);
    }

    public String getValue() {
        return passwordTextBox.getText();
    }

    public void addKeyDownHandler(KeyDownHandler keyDownHandler) {
        passwordTextBox.addKeyDownHandler(keyDownHandler);
    }
}
