package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class VerticalComponent extends CellComponent implements ClickHandler, MouseOverHandler {

    private List<MouseOverHandler> handlers = new ArrayList<MouseOverHandler>();
    private List<ClickHandler> clHandlers = new ArrayList<ClickHandler>();

    protected VerticalPanel verticalPanel = new VerticalPanel();

    public VerticalComponent() {
        super();
        addDomHandler(this, ClickEvent.getType());
        addDomHandler(this, MouseOverEvent.getType());
        initWidget(verticalPanel);
    }


    public VerticalPanel getPanel() {
        return verticalPanel;
    }

    public void onClick(ClickEvent event) {
        for (ClickHandler handler : clHandlers) {
            handler.onClick(event);
        }
    }


    public void addMouseOverHandler(MouseOverHandler handler) {
        this.handlers.add(handler);
    }

    public void addClickHandler(ClickHandler handler) {
        clHandlers.add(handler);
    }


    public void onMouseOver(MouseOverEvent event) {
        for (MouseOverHandler handler : handlers) {
            handler.onMouseOver(event);
        }

    }

    public void insert(int index, GuiComponent gc, Layout17 l) {
        getPanel().insert(gc, index);
        gc.layout(l);
    }

    public void remove(Widget w) {
        verticalPanel.remove(w);

    }


}
