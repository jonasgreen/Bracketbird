package com.bracketbird.client.pages.livescores;

import com.bracketbird.clientcore.appcontrol.PageController;

/**
 *
 */
public class LiveScoresPageController extends PageController<LiveScoresPage> {

    private static LiveScoresPageController instance;

    private LiveScoresPageController() {
    }

    public static LiveScoresPageController getInstance() {
        if (instance == null) {
            instance = new LiveScoresPageController();
        }
        return instance;
    }

    public void afterLoad() {}

    public void beforeUnload() {}

    @Override
    public void afterFirstLoad() {

    }




    public LiveScoresPage newInstance() {
        return new LiveScoresPage();
    }





}
