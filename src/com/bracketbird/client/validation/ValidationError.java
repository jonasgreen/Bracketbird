package com.bracketbird.client.validation;

import com.google.gwt.user.client.ui.Widget;

public class ValidationError {
    private Widget labelWidget;
    private Widget valueWidget;

    public ValidationError(Widget labelWidget, Widget valueWidget) {
        this.labelWidget = labelWidget;
        this.valueWidget = valueWidget;
    }

    public Widget getLabelWidget() {
        return labelWidget;
    }

    public Widget getValueWidget() {
        return valueWidget;
    }
}
