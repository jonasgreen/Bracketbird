package com.bracketbird.clientcore.gui;

import com.bracketbird.clientcore.util.FocusComponentIntfc;


public class FocusKeyDownEvent {

    private int keyCode;
    private FocusComponentIntfc source;


    public FocusKeyDownEvent(FocusComponentIntfc fc, int keyCode) {
        this.keyCode = keyCode;
        this.source = fc;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public FocusComponentIntfc getSource() {
        return source;
    }

    public void setSource(FocusComponentIntfc source) {
        this.source = source;
    }
}