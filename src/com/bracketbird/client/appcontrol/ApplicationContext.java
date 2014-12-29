package com.bracketbird.client.appcontrol;

import com.bracketbird.client.HistorySupport;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class ApplicationContext<PAGE_CONTAINER extends Panel> {

    private PageController activePageController;
    private Widget contextWidget;
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


    protected abstract Widget createContextWidget();
    protected abstract PAGE_CONTAINER createPageContainer();

    protected Widget getContextWidget() {
        if (contextWidget == null) {
            contextWidget = createContextWidget();
            contextWidget.setStyleName("text");
        }
        return contextWidget;
    }

    public PAGE_CONTAINER getPageContainer() {
        if (pageContainer == null) {
            pageContainer = createPageContainer();
        }
        return pageContainer;
    }


    private void loadNewPage(PageController pc) {
        pc.handleBeforeLoad();

        PageChangedEvent event = new PageChangedEvent(activePageController, pc);

        activePageController = pc;

        //PopupManager.hide();
        //InfoManager.hideInfo();

        addToPageContainer(pc);
        HistorySupport.getInstance().addHistory(pc);


        pc.handleAfterLoad();
        notifyListeners(event);
    }

    protected void addToPageContainer(PageController pc) {
        getPageContainer().add(pc.getPage());
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

    public void addPageChangeListener(PageChangedListener listener){
        pageChangedListeners.add(listener);
    }
}
