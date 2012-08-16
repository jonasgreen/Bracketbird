package com.bracketbird.clientcore.gui;

import com.bracketbird.clientcore.style.Layout17;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SimpleFlowComponent extends CellComponent {

    protected FlowPanel flowPanel = new FlowPanel();
    protected Widget content;


    public SimpleFlowComponent() {
        super();
        initWidget(flowPanel);
    }

    public SimpleFlowComponent(GuiComponent component, TextLayout layout) {
        super();
        initWidget(flowPanel);
        add(component, layout);
    }


    public void add(GuiComponent gc, Layout17 l) {
        if(content != null){
            content.removeFromParent();
        }
        content = gc;
        super.add(gc, l);
    }

    public void add(Widget w) {
        if(content != null){
            content.removeFromParent();
        }
        content = w;
        super.add(w);
    }

    public FlowPanel getPanel() {
        return flowPanel;
    }

    public void remove(Widget w) {
        flowPanel.remove(w);
    }

    public void clear(){
        if(content != null){
            content.removeFromParent();
        }
        content = null;
    }


}
