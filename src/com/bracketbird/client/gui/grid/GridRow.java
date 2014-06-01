package com.bracketbird.client.gui.grid;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class GridRow extends FlowPanel {

    public GridRow() {
        addStyleName("pure-g");
    }

    public GridRow addCell(ColWidth width, Widget w) {
        return this.addCell(width, w, null);
    }

    public GridRow addCell(ColWidth width, Widget w, String style) {
        add(new GridColumn(width, w));
        if (style != null) {
            w.addStyleName(style);
        }
        return this;
    }


    public GridRow addCell(int width, Widget one) {
        return addCell(width, one, null);
    }

    public GridRow addCell(int width, Widget w, String style) {
        FlowPanel col = new FlowPanel();
        col.setStyleName("pure-col");
        col.setWidth(width+"%");
        col.add(w);
        if(style != null){
            w.addStyleName(style);
        }
        add(col);
        return this;
    }


    public void addKeyDownHandler(KeyDownHandler keyDownHandler) {
        addDomHandler(keyDownHandler, KeyDownEvent.getType());
    }

    public void addClickHandler(ClickHandler clickHandler) {
        addDomHandler(clickHandler, ClickEvent.getType());
    }

}
