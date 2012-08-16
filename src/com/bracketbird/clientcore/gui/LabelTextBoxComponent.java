package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.bracketbird.clientcore.util.FocusManager;
import com.bracketbird.clientcore.util.MouseOver;
import com.bracketbird.clientcore.util.FocusComponentIntfc;
import com.bracketbird.clientcore.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LabelTextBoxComponent extends SimplePanelComponent implements FocusComponentIntfc, KeyDownHandler {

    private TextBoxComponent textBox = new TextBoxComponent();
    private LabelComponent label = new LabelComponent("");


    private List<KeyDownHandler> keyDownHandlers = new ArrayList<KeyDownHandler>();

    private FocusManager fManager;

    public LabelTextBoxComponent() {
        super();
        add(getLabel());
        addMouseOverHandler(MouseOver.POINTER);
        addDomHandler(this, KeyDownEvent.getType());
    }


    public void onClick(ClickEvent event) {
        super.onClick(event);
        setFocus(true);
    }

    public TextBoxComponent getTextBox() {
        return textBox;
    }

    public LabelComponent getLabel() {
        return label;
    }

    public void setText(String s) {
        this.textBox.setText(s);
        this.label.setText(s);
    }


    public void addKeyDownHandler(KeyDownHandler keyDownHandler) {
        keyDownHandlers.add(keyDownHandler);
    }

    public String getText() {
        return textBox.getText();
    }

    public void setFocus(boolean focus) {
        if (focus) {
            add(getTextBox());
            getTextBox().setFocus(focus);
            
            if (fManager != null) {
                fManager.setLastFocus(this);
            }
        }
        else {
            getTextBox().setFocus(focus);
            add(getLabel());
            getLabel().setText(getTextBox().getText());
        }
    }


    public boolean isEmpty() {
        return textBox.getText() == null || textBox.getText().trim().equals("");
    }

    public void setValue(Object obj) {
        setText((String) obj);

    }

    public Integer getIntegerValue(){
        if(StringUtil.isEmpty(getValue())){
            return null;
        }
        return Integer.valueOf(getValue());
    }

    public void setIntegerValue(Integer value){
        if(value == null){
            return;
        }
        setText(String.valueOf(value));
    }

    public String getValue() {
        return textBox.getText();
    }


    public boolean isLeavable(int keyCode) {
        return fManager != null && fManager.isLeavable(getTextBox().getTextBox(), keyCode);
    }

    public void addParent(FocusManager fm) {
        this.fManager = fm;
    }

    public void focusLost() {
        setFocus(false);
    }

    public void setHeight(String height){
        getTextBox().getTextBox().setHeight(height);
        getLabel().getLabel().setHeight(height);
    }


    public void setWidth(String w){
        getTextBox().getTextBox().setWidth(w);
        getLabel().getLabel().setWidth(w);
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
        if(fManager != null  && e != null){
            fManager.setCursorPosition(this, getTextBox().getTextBox(), e);
        }
    }

}