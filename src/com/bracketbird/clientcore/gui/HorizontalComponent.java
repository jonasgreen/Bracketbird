package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class HorizontalComponent extends CellComponent implements ClickHandler, MouseOverHandler {

    private List<ClickHandler> handlers = new ArrayList<ClickHandler>();
    private List<MouseOverHandler> mOverHandlers = new ArrayList<MouseOverHandler>();

    protected HorizontalPanel horizontalPanel = new HorizontalPanel();

    public HorizontalComponent() {
        super();
        addDomHandler(this, ClickEvent.getType());
        addDomHandler(this, MouseOverEvent.getType());
        initWidget(horizontalPanel);
    }

    public HorizontalPanel getPanel() {
        return horizontalPanel;
    }


    public void onClick(ClickEvent event) {
        for (ClickHandler handler : handlers) {
            handler.onClick(event);
        }
    }

    public void addClickHandler(ClickHandler handler) {
        handlers.add(handler);
    }

    public void addMouseOverHandler(MouseOverHandler handler){
        mOverHandlers.add(handler);
    }

    public void onMouseOver(MouseOverEvent event) {
        for (MouseOverHandler mOverHandler : mOverHandlers) {
            mOverHandler.onMouseOver(event);

        }
    }
}
