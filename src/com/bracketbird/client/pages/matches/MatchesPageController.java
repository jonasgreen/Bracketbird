package com.bracketbird.client.pages.matches;

import com.bracketbird.client.model.event.CreateDeleteHandler;
import com.bracketbird.client.model.event.CreateEvent;
import com.bracketbird.client.model.event.DeleteEvent;
import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.model.tournament.Stage;
import com.bracketbird.client.appcontrol.PageController;

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

    @Override
    public void afterFirstLoad() {

        RTC.getInstance().getTournament().stagesDispatcher.addHandler(new CreateDeleteHandler<Stage>() {
            @Override
            public void onCreate(CreateEvent<Stage> event) {
                getPage().addStagePanel(event.getValue());
            }

            @Override
            public void onDelete(DeleteEvent<Stage> event) {
                getPage().deleteStagePanel(event.getValue());
            }
        });


        for (Stage l : RTC.getInstance().getTournament().getStages()) {
            getPage().addStagePanel(l);
        }
    }




    public MatchesPage newInstance() {
        return new MatchesPage();
    }





}
