package com.bracketbird.client.pages.matches;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.event.ModelEvent;
import com.bracketbird.client.gui.rtc.event.ModelEventHandler;
import com.bracketbird.client.model.tournament.Stage;
import com.bracketbird.clientcore.appcontrol.PageController;

/**
 *
 */
public class MatchesPageController extends PageController<MatchesPage> {

    private static MatchesPageController instance;

    private MatchesPageController() {
    }

    public static MatchesPageController getInstance() {
        if (instance == null) {
            instance = new MatchesPageController();
        }
        return instance;
    }

    public void afterLoad() {}

    public void beforeUnload() {}

    @Override
    public void afterFirstLoad() {

        RTC.getInstance().getTournament().stagesEventHandlers.addHandler(new ModelEventHandler<Stage>() {
            @Override
            public void handleEvent(ModelEvent<Stage> event) {
                if (event.isCreate()) {
                    getPage().createMatchesPanel(event.getAfter());
                }
                else if (event.isDelete()) {
                    getPage().deleteMatchesPanel(event.getBefore());
                }
            }
        });


        for (Stage l : RTC.getInstance().getTournament().getStages()) {
            getPage().createMatchesPanel(l);
        }
    }




    public MatchesPage newInstance() {
        return new MatchesPage();
    }





}
