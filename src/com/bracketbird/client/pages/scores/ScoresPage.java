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


    private static String COLOR_TEAL = "#009688";
    private static String COLOR_INDIGO = "#3F51B5";
    private static String COLOR_BLUE_GREY = "#607D8B";
    private static String COLOR_DEEP_ORANGE = "#f39c12";
    private static String COLOR_PURPLE = "#9C27B0";

    private String[] colors = new String[]{COLOR_TEAL, COLOR_BLUE_GREY, COLOR_DEEP_ORANGE, COLOR_INDIGO, COLOR_PURPLE};



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
        int index = 0;

        for (Group group : stage.getGroups()) {
            if(index >= colors.length){
                index = 0;
            }
            GroupScoresTable w = new GroupScoresTable(group);
            //w.getHeaderRow().getElement().getStyle().setBackgroundColor(colors[index]);
            //w.getHeaderRow().getElement().getStyle().setBorderColor(colors[index]);
            //w.getElement().getStyle().setBorderColor(colors[index]);

            add(w);
            index++;
        }
    }

    public void showTournamentFinished(Tournament tournament) {

    }

    public void showNothing() {
        clear();
    }
}
