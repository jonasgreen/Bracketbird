package com.bracketbird.clientcore.gui;

import com.google.gwt.dom.client.*;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class DockComponent extends GuiComponent {

    protected DockLayoutPanel panel;

    public DockComponent(Style.Unit unit) {
        super();
        panel = new DockLayoutPanel(unit);
        add(panel);
    }

    public DockLayoutPanel getPanel() {
        return panel;

    }

    

    public void addEast(GuiComponent gc, double size, Layout17 layout) {
        panel.addEast(gc, size);
        gc.layout(layout);
    }

    public void addNorth(Widget gc, double size) {
        panel.addNorth(gc, size);
    }

    public void addNorth(GuiComponent gc, double size, Layout17 layout) {
        panel.addNorth(gc, size);
        gc.layout(layout);
    }

    public void addSouth(GuiComponent gc, double size, Layout17 layout) {
        panel.addSouth(gc, size);
        gc.layout(layout);


    }

    public void addWest(GuiComponent gc, double size, Layout17 layout) {
        panel.addWest(gc, size);
        gc.layout(layout);
    }

    public void add(Widget w){
        panel.add(w);
    }

    public void add(GuiComponent gc, Layout17 layout) {
        panel.add(gc);
        gc.layout(layout);

    }

    public void insertEast(GuiComponent gc, double size, Widget before, Layout17 layout) {
        panel.insertEast(gc, size, before);
        gc.layout(layout);
    }

    public void insertNorth(Widget gc, double size, Widget before, Layout17 layout) {
            panel.insertNorth(gc, size, before);
        }


    public void insertNorth(GuiComponent gc, double size, Widget before, Layout17 layout) {
        panel.insertNorth(gc, size, before);
        gc.layout(layout);
    }

    public void insertSouth(GuiComponent gc, double size, Widget before, Layout17 layout) {
        panel.insertSouth(gc, size, before);
        gc.layout(layout);
    }

    public void insertWest(GuiComponent gc, double size, Widget before, Layout17 layout) {
        panel.insertWest(gc, size, before);
        gc.layout(layout);
    }

}