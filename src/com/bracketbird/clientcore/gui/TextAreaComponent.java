package com.bracketbird.clientcore.gui;

import com.bracketbird.clientcore.util.FocusComponentIntfc;
import com.bracketbird.clientcore.util.FocusManager;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.TextArea;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TextAreaComponent extends GuiComponent implements FocusComponentIntfc, KeyDownHandler, ClickHandler {

    private TextArea textArea = new TextArea();
    private List<KeyDownHandler> keyDownHandlers = new ArrayList<KeyDownHandler>();
    private FocusManager fManager;

    public TextAreaComponent() {
        super();
        initWidget(textArea);
        addDomHandler(this, KeyDownEvent.getType());
        addDomHandler(this, ClickEvent.getType());

    }


    public TextArea getTextArea() {
        return textArea;
    }

    public void setText(String s) {
        this.textArea.setText(s);
    }


    public void addKeyDownHandler(KeyDownHandler keyDownHandler) {
        keyDownHandlers.add(keyDownHandler);
    }

    public String getText() {
        return textArea.getText();
    }

    public void setFocus(boolean focus) {
        getTextArea().setFocus(focus);
        if (focus) {
            if (fManager != null) {
                fManager.setLastFocus(this);
            }
        }
    }


    public boolean isEmpty() {
        return textArea.getText() == null || textArea.getText().trim().equals("");
    }

    public void setValue(Object obj) {
        textArea.setText((String) obj);
    }

    public String getValue() {
        return textArea.getText();
    }


    public boolean isLeavable(int keyCode) {
        return fManager != null && fManager.isLeavable(getTextArea(), keyCode);
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
            fManager.setCursorPosition(this, getTextArea(), e);
        }
    }

    public void onClick(ClickEvent event) {
        setFocus(true);
    }
}
