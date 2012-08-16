package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FocusComponent extends PanelComponent implements MouseOverHandler {

    private List<MouseOverHandler> handlers = new ArrayList<MouseOverHandler>();

    protected FocusPanel panel = new FocusPanel();

    public FocusComponent() {
        super();
        addDomHandler(this, MouseOverEvent.getType());
        initWidget(panel);
    }


    public FocusPanel getPanel() {
        return panel;
    }


    public void addMouseOverHandler(MouseOverHandler handler) {
        this.handlers.add(handler);
    }


    public void onMouseOver(MouseOverEvent event) {
        for (MouseOverHandler handler : handlers) {
            handler.onMouseOver(event);
        }

    }

    public void remove(Widget w) {
        panel.remove(w);
    }


}