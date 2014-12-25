package com.bracketbird.client.pages.scores;


import com.bracketbird.client.model.tournament.Group;
import com.bracketbird.client.model.tournament.GroupStage;
import com.bracketbird.client.model.tournament.Stage;
import com.bracketbird.client.model.tournament.Tournament;
import com.bracketbird.client.appcontrol.Page;


/**
 *
 */
public class ScoresPage extends Page<ScoresPageController> {


    @Override
    protected void init() {
        setStyleName("scoresPage");
    }


    public void show(Stage stage) {
        clear();
        if(stage.isGroupStage()){
            showGroupStage((GroupStage) stage);
        }
        else{
            showKnockoutStage(stage);
        }
    }

    private void showKnockoutStage(Stage stage) {

    }

    private void showGroupStage(GroupStage stage) {
        for (Group group : stage.getGroups()) {
            add(new GroupScoresTable(group));
        }

    }

    public void showTournamentFinished(Tournament tournament) {

    }

    public void showNothing() {
        clear();
    }
}
