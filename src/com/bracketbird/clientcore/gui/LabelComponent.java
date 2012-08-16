package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class LabelComponent extends GuiComponent {
    protected Label label = new Label();

    public LabelComponent(String text) {
        super();
        label.setText(text);
        initWidget(label);
    }

    public Label getLabel() {
        return label;
    }

    public void setText(String text){
        label.setText(text);
    }

    public void addMouseOver(MouseOverHandler handler){
        label.addMouseOverHandler(handler);
    }

    public void addMouseOut(MouseOutHandler handler){
        label.addMouseOutHandler(handler);
    }



    public HandlerRegistration addClickHandler(ClickHandler handler){
        return label.addClickHandler(handler);
    }



}
