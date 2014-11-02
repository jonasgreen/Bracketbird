package com.bracketbird.client.pages.settings;

import com.google.gwt.user.client.ui.*;

public class SettingsValidationException extends Exception {
    private static final long serialVersionUID = -4666797965056758651L;

    private Label label;
    private Focusable hasValueWidget;
    private String errorMsg;

    public SettingsValidationException(Label label, Focusable hasValue, String errorMsg) {
        this.label = label;
        this.hasValueWidget = hasValue;
        this.errorMsg = errorMsg;
    }

    public Label getLabel() {
        return label;
    }

    public Focusable getHasValueWidget() {
        return hasValueWidget;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
