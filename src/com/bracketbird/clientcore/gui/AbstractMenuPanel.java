package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.appcontrol.*;

import java.util.*;

/**
 *
 */
public abstract class AbstractMenuPanel {

    private MenuController controller;
    private List<MenuComponent> allMenues = new ArrayList<MenuComponent>();

    public AbstractMenuPanel(MenuController controller) {
        super();
        this.controller = controller;
        if (controller != null) {
            controller.add(this);
        }
    }

    public AbstractMenuPanel() {
        this(null);
    }

    public void remove(MenuComponent mc) {
        allMenues.remove(mc);
        mc.removeFromParent();
    }

    public void removeAll() {
        for (MenuComponent m : allMenues) {
            m.removeFromParent();
        }
        allMenues = new ArrayList<MenuComponent>();
    }

    public void addMenuItem(final PageController pc) {
        allMenues.add(pc.getMenu());
        add(pc.getMenu());
        pc.getMenu().setParentPanel(this);


        pc.getMenu().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (controller != null) {
                    controller.selected(AbstractMenuPanel.this);
                }
                Application.show(pc);
            }
        });
    }

    public void addAnacisticMenuItem(MenuComponent mc) {
        allMenues.add(mc);
        add(mc);
        mc.setParentPanel(this);
    }


    public abstract void addSectionTitle(String title);


    protected abstract void add(MenuComponent mc);


    void selected(MenuComponent mc) {
        for (MenuComponent m : allMenues) {
            if (!mc.equals(m)) {
                m.setSelected(false);
            }
        }
        if (controller != null) {
            controller.selected(this);
        }
    }

    public void deselectAll(){
        for (MenuComponent m : allMenues) {
            m.setSelected(false);
        }
    }

    public List<MenuComponent> getAllMenues() {
        return allMenues;
    }
}