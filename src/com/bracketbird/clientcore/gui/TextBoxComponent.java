package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.bracketbird.clientcore.util.*;

import java.util.List;
import java.util.ArrayList;

/**
 *
 */
public class TextBoxComponent extends GuiComponent implements FocusComponentIntfc, KeyDownHandler, ClickHandler {

    private TextBox textBox = new TextBox();
    private List<KeyDownHandler> keyDownHandlers = new ArrayList<KeyDownHandler>();
    private FocusManager fManager;

    public TextBoxComponent() {
        super();
        //add(textBox);
        addDomHandler(this, KeyDownEvent.getType());
        addDomHandler(this, ClickEvent.getType());

    }


    public TextBox getTextBox() {
        return textBox;
    }

    public void setText(String s) {
        this.textBox.setText(s);
    }


    public void addKeyDownHandler(KeyDownHandler keyDownHandler) {
        keyDownHandlers.add(keyDownHandler);
    }

    public String getText() {
        return textBox.getText();
    }

    public void setFocus(boolean focus) {
        getTextBox().setFocus(focus);
        if (focus) {
            if (fManager != null) {
                fManager.setLastFocus(this);
            }
        }
    }


    public boolean isEmpty() {
        return textBox.getText() == null || textBox.getText().trim().equals("");
    }

    public void setValue(Object obj) {
        textBox.setText((String) obj);
    }

    public String getValue() {
        return textBox.getText();
    }


    public boolean isLeavable(int keyCode) {
        return fManager != null && fManager.isLeavable(getTextBox(), keyCode);
    }

    public void addParent(FocusManager fm) {
        this.fManager = fm;
    }

    public void focusLost() {
        setFocus(false);
    }


    public void onKeyDown(KeyDownEvent event) {
        if (fManager != null) {
            fManager.onKeyDown(new FocusKeyDownEvent(this, event.getNativeKeyCode()));
        }

        for (KeyDownHandler keyDownHandler : keyDownHandlers) {
            keyDownHandler.onKeyDown(event);
        }
    }

    public void doFocus(FocusKeyDownEvent e) {
        setFocus(true);
        if (fManager != null && e != null) {
            fManager.setCursorPosition(this, getTextBox(), e);
        }
    }

    public void onClick(ClickEvent event) {
        setFocus(true);
    }
}
