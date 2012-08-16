package com.bracketbird.client.applicationstarter;

import com.bracketbird.client.gui.main.AppPageController;
import com.bracketbird.client.gui.main.personal.personal.CreateTournamentPageController;
import com.bracketbird.clientcore.appcontrol.PageFlow;
import com.bracketbird.clientcore.model.keys.UserId;

/**
 *
 */
public class CreateTournamentStarter extends ApplicationStarter {
    private UserId userId;

    public CreateTournamentStarter(UserId userId) {
        this.userId = userId;
    }

    public void start() {
        PageFlow.activeController = AppPageController.getInstance();
        CreateTournamentPageController.getInstance().setUserId(userId);
        PageFlow.popUp(CreateTournamentPageController.getInstance());
    }





}
