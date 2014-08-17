package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class Duo<T extends Widget> {

    private Label label;
    private T widget;

    public Duo(Label label, T widget) {
        this.label = label;
        this.widget = widget;
    }

    public Label getLabel() {
        return label;
    }

    public T getWidget() {
        return widget;
    }
}
