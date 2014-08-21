package com.bracketbird.clientcore.gui;


import com.bracketbird.clientcore.appcontrol.*;

/**
 *
 */
public class PopUpDataPageController extends PageController<PopUpDataPage> {

    private static PopUpDataPageController instance = null;


    private PopUpDataPageController() {
    }

    public static PopUpDataPageController getInstance() {
        if (instance == null) {
            instance = new PopUpDataPageController();
        }
        return instance;
    }

    public PopUpDataPage newInstance() {
        return new PopUpDataPage();
    }

    public MenuComponent newMenuInstance() {
        return null;
    }

    public void afterLoad() {
        getPage();
        doLoad();
    }

    private void doLoad() {
        page.setVisible(true);
        page.setFocus();
    }

    public void beforeUnload() {

    }

    public boolean makeHistory() {
        return false;
    }



}