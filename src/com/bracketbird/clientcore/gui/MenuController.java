package com.bracketbird.clientcore.gui;

import java.util.List;
import java.util.ArrayList;

/**
 *
 */
public class MenuController {


    private List<AbstractMenuPanel> menuPanels = new ArrayList<AbstractMenuPanel>();

    public MenuController() {
    }


    public void add(AbstractMenuPanel vmp){
        menuPanels.add(vmp);
    }

    public void selected(AbstractMenuPanel vmp){
        for (AbstractMenuPanel panel : menuPanels) {
            if(panel.equals(vmp)){
                //ignore
            }
            else{
                for (MenuComponent mc : panel.getAllMenues()) {
                    mc.setSelected(false);
                }
            }
        }

    }
}
