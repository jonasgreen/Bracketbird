package com.bracketbird.clientcore.appcontrol;

import com.bracketbird.client.HistorySupport;
import com.bracketbird.clientcore.gui.InfoManager;
import com.bracketbird.clientcore.gui.PopupManager;
import com.google.gwt.user.client.ui.ComplexPanel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class ApplicationContext<PAGE_CONTAINER extends ComplexPanel> {

    private PageController activePageController;
    private PAGE_CONTAINER pageContainer;

    private List<PageChangedListener> pageChangedListeners = new ArrayList<PageChangedListener>();


    public void show(PageController pc) {
        if (activePageController == null) {
            loadNewPage(pc);
        }
        else {
            if (activePageController != pc) {
                unloadActivePage();
                loadNewPage(pc);
            }
        }
    }


    protected abstract PAGE_CONTAINER createPageContainer();

    protected PAGE_CONTAINER getPageContainer() {
        if (pageContainer == null) {
            pageContainer = createPageContainer();
        }
        return pageContainer;
    }


    private void loadNewPage(PageController pc) {
        PageChangedEvent event = new PageChangedEvent(activePageController, pc);

        activePageController = pc;

        PopupManager.hide();
        InfoManager.hideInfo();

        getPageContainer().add(pc.getPage());
        HistorySupport.getInstance().addHistory(pc);


        pc.afterLoad();
        notifyListeners(event);
    }


    private void notifyListeners(PageChangedEvent event) {
        for (PageChangedListener l : pageChangedListeners) {
            l.onPageChange(event);
        }
    }


    private void unloadActivePage() {
        activePageController.beforeUnload();
        activePageController.getPage().removeFromParent();
    }


    public PageController getActivePageController() {
        return activePageController;
    }

    public void addListener(PageChangedListener listener){
        pageChangedListeners.add(listener);
    }
}
