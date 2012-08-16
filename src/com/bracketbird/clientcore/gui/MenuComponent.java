package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.*;

/**
 *
 */
public abstract class MenuComponent extends HorizontalComponent implements MouseOverHandler, MouseOutHandler {


    private boolean selected = false;
    private boolean active = true;

    AbstractMenuPanel parentPanel;

    public MenuComponent() {
        this(null);
    }

    public MenuComponent(AbstractMenuPanel panel) {
        super();
        this.parentPanel = panel;
        addDomHandler(this, MouseOverEvent.getType());
        addDomHandler(this, MouseOutEvent.getType());
    }

    public void onMouseOver(MouseOverEvent event) {
        if (!isSelected() && isActive()) {
            event.getRelativeElement().getStyle().setProperty("cursor", "pointer");
            mouseOver();
        }
    }

    public void onMouseOut(MouseOutEvent event) {
        event.getRelativeElement().getStyle().setProperty("cursor", "auto");
        mouseOut();
    }


    public abstract void mouseOver();

    public abstract void mouseOut();

    public abstract void update();


 
    public void setSelected(boolean sel) {
        selected = sel;
        if (selected && (parentPanel != null)) {
            parentPanel.selected(this);
        }
        update();
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        update();
    }

    public AbstractMenuPanel getParentPanel() {
        return parentPanel;
    }

    public void setParentPanel(AbstractMenuPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    

}