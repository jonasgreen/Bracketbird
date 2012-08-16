package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class SimplePanelComponent extends GuiComponent implements MouseOverHandler, ClickHandler, MouseOutHandler {
    private List<MouseOverHandler> mouseOverHandlers = new ArrayList<MouseOverHandler>();
    private List<MouseOutHandler> mouseOutHandlers = new ArrayList<MouseOutHandler>();
    private List<ClickHandler> clickHandlers = new ArrayList<ClickHandler>();

    private SimplePanel panel = new SimplePanel();
    private GuiComponent content = null;

    public SimplePanelComponent() {
        super();
        addDomHandler(this, MouseOverEvent.getType());
        addDomHandler(this, MouseOutEvent.getType());

        addDomHandler(this, ClickEvent.getType());
        initWidget(panel);

    }

    public SimplePanel getSimplePanel() {
        return panel;
    }

    public void add(GuiComponent gc) {
        if (content != null) {
            content.removeFromParent();
        }
        content = gc;
        panel.add(gc);
    }

    public void add(Widget w) {
        panel.add(w);
    }


    public void add(GuiComponent gc, Layout17 l) {
        if (content != null) {
            content.removeFromParent();
        }
        content = gc;
        panel.add(gc);
        gc.layout(l);
    }


    public void addMouseOverHandler(MouseOverHandler handler) {
        this.mouseOverHandlers.add(handler);
    }


    public void addMouseOutHandler(MouseOutHandler handler) {
        this.mouseOutHandlers.add(handler);
    }

    public void onMouseOver(MouseOverEvent event) {
        for (MouseOverHandler handler : mouseOverHandlers) {
            handler.onMouseOver(event);
        }
    }

    public void addClickHandler(ClickHandler handler) {
        clickHandlers.add(handler);
    }


    public void onClick(ClickEvent event) {
        for (ClickHandler handler : clickHandlers) {
            handler.onClick(event);
        }
    }

    public void clear() {
        if (content != null) {
            content.removeFromParent();
            content = null;
        }
    }

    public void onMouseOut(MouseOutEvent event) {
        for (MouseOutHandler h : mouseOutHandlers) {
            h.onMouseOut(event);
        }
    }
}
