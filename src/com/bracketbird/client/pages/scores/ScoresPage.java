package com.bracketbird.client.pages.scores;


import com.bracketbird.client.model.tournament.Stage;
import com.bracketbird.client.model.tournament.Tournament;
import com.bracketbird.clientcore.appcontrol.Page;


/**
 *
 */
public class ScoresPage extends Page<ScoresPageController> {


    @Override
    protected void init() {
        setStyleName("scoresPage");
    }


    public void show(Stage stage) {

    }

    public void showTournamentFinished(Tournament tournament) {

    }

    public void showNothing() {

    }
}
