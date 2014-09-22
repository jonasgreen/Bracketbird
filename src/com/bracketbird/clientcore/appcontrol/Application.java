package com.bracketbird.clientcore.appcontrol;

import com.bracketbird.clientcore.gui.PopupManager;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 *
 */
public class Application {

    private static Application instance;

    private ApplicationContext activeContext;

    private Application() {
        loadNewContext(FrontPageContext.get());
    }

    public static Application get() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    public void shiftApplicationContext(ApplicationContext context){
        if (activeContext == null) {
            loadNewContext(context);
        }
        else {
            if (activeContext != context) {
                unloadActiveContext();
                loadNewContext(context);
            }
        }
    }

    private void unloadActiveContext() {
        activeContext.getContextWidget().removeFromParent();
    }

    private void loadNewContext(ApplicationContext context) {
        RootLayoutPanel.get().add(context.getContextWidget());
        this.activeContext = context;
    }


    public static void show(PageController pc){
        get().activeContext.show(pc);
    }


    public static void popUp(PageController pc) {
        PopupManager.show(pc);
    }


    public PageController activePageController() {
        return activeContext.getActivePageController();
    }
}
