package com.bracketbird.client;

import com.bracketbird.client.appcontrol.PageController;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class HistorySupport {
    private static Map<String, PageController> controllers = new HashMap<String, PageController>();

    private static HistorySupport instance = null;

    private boolean allowHistory = false;

    private HistorySupport() {
        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            public void onValueChange(ValueChangeEvent<String> event) {
                if (allowHistory) {
                    updateState(event.getValue(), true);
                }
            }
        });

    }


    public static HistorySupport getInstance() {
        if (instance == null) {
            instance = new HistorySupport();
            initMaps();
        }
        return instance;
    }


    private static void initMaps() {

        // add(FrontPageController.HISTORY_NAME, FrontPageController.getInstance());
    }


    private static void add(String name, PageController c) {
        controllers.put(name, c);
    }


    public void updateState(String historyToken, boolean fromHistory) {
        PageController c = controllers.get(historyToken);
        if (c != null) {
            doLoad(c, fromHistory);
        }
    }

    private void doLoad(PageController toLoad, boolean fromHistory) {
        if (fromHistory) {
           // ApplicationController.getInstance().loadPageFromHistory(toLoad);
        }
        else {
            //ApplicationController.getInstance().loadPage(toLoad);
        }
    }

    public void addHistory(PageController controller) {
        if (allowHistory) {
            History.newItem(controller.getClass().getSimpleName(), false);
        }
    }

    public void setAllowHistory(boolean allowHistory) {
        this.allowHistory = allowHistory;
    }
}