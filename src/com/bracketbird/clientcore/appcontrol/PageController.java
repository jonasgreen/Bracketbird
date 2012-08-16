package com.bracketbird.clientcore.appcontrol;


import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public abstract class PageController<P extends Page> {

    private PageController childController = null;


    protected Layout17 defaultLayout = new Layout17(0, 0, 0, 0, Horizontal.LEFT, null);
    protected P page;
    protected String historyName;
    protected PageController parent;
    protected MenuComponent menuComponent;

    protected PageController(PageController parent, String historyName) {
        this.parent = parent;
        this.historyName = historyName;
    }

    public String getHistoryName() {
        return historyName;
    }

    public abstract P newInstance();

    public void setFocus() {
        getPage().setFocus(true);
    }

    public void afterLoad() {
    }

    public void unload() {
    }


    public abstract boolean makeHistory();


    public void afterLoadFromHistory() {
        afterLoad();
    }

    public PageController getParent() {
        return parent;
    }

    //lazy loading
    public P getPage() {
        if (page == null) {
            page = newInstance();
            page.setController(this);
            page.init();
        }
        return page;
    }

    protected abstract MenuComponent newMenuInstance();

    public Layout17 getLayout() {
        return defaultLayout;
    }

    public MenuComponent getMenu() {
        if (menuComponent == null) {
            menuComponent = newMenuInstance();
        }
        return menuComponent;
    }

    //returns the state that this site has permission to be shown in.
    //public abstract UserStateConstant getLegalState();


    public boolean pagePermission(PageController toLoad) {
 /*       String[] errMsg = PermissionManager.getInstance().pagePermission(toLoad);
        if (errMsg != null) {
            DialogComponent.showSimpleOk("Permission denied", errMsg).addCloseHandler(new CloseHandler<PopupPanel>() {
                public void onClose(CloseEvent<PopupPanel> popupPanelCloseEvent) {
                    //the old menu is set again
                    if (getChild() != null) {
                        setSelectedMenu(getChild().getMenu());
                    }
                }
            });
            return false;

        }*/
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }



    public PageController getChild() {
        return childController;
    }

    public void setChild(PageController pc) {
        childController = pc;
    }

    public void setSelectedMenu(MenuComponent mc) {
        if (mc != null) {
            mc.setSelected(true);
        }
    }

    protected void runGoogleAnalytics(String siteName) {
       // ApplicationController.runGoogleAnalytics(siteName);
    }


    public void clear() {
        /*if (menuComponent != null) {
            menuComponent.removeFromParent();
        }

        
        if (page != null) {
            page.removeFromParent();
        }
        */
        childController = null;
        page = null;
        //menuComponent = null;
    }


    public void loadParams(HashMap<String, String> pMap) {
        //throw new SystemException("loadParams not implemented by PageController + " + this.getClass().getName());
    }



}
