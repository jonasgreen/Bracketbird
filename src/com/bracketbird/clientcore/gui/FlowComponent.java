package com.bracketbird.clientcore.gui;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class FlowComponent extends CellComponent implements ClickHandler, MouseOverHandler {

    private List<MouseOverHandler> handlers = new ArrayList<MouseOverHandler>();
    private List<ClickHandler> clHandlers = new ArrayList<ClickHandler>();

    protected FlowPanel flowPanel = new FlowPanel();



    public FlowComponent() {
        super();
        addDomHandler(this, ClickEvent.getType());
        addDomHandler(this, MouseOverEvent.getType());
        initWidget(flowPanel);
    }

    public FlowComponent(GuiComponent component, TextLayout layout) {
        super();
        addDomHandler(this, ClickEvent.getType());
        addDomHandler(this, MouseOverEvent.getType());
        initWidget(flowPanel);
        add(component, layout);
    }

    public FlowComponent addLeft(GuiComponent comp, TextLayout tl){
        this.getStyleElement().getStyle().setDisplay(Style.Display.INLINE);
        FlowComponent fl = new FlowComponent();
        fl.add(comp, tl);
        fl.getStyleElement().getStyle().setFloat(Style.Float.LEFT);
        add(fl);
        return fl;
    }


    public FlowComponent addRigth(GuiComponent comp, TextLayout tl){
        this.getStyleElement().getStyle().setDisplay(Style.Display.INLINE);
        FlowComponent fl = new FlowComponent();
        fl.add(comp, tl);
        fl.getStyleElement().getStyle().setFloat(Style.Float.RIGHT);
        add(fl);
        return fl;
    }

    public FlowComponent addComp(GuiComponent comp, Layout17 l) {
        FlowComponent fl = new FlowComponent();
        fl.add(comp, l);
        add(fl);
        return fl;
    }

    public FlowPanel getPanel() {
        return flowPanel;
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
        flowPanel.remove(w);

    }


}
