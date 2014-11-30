package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.StageType;

public class BracketBirdTest {

    protected RTCTest rtc(){
        return RTCTest.getInstance();
    }

    protected void createTeams(int noOfTeams) {
        int count = 0;
        while (count++ < noOfTeams) {
            rtc().createTeam("Team " + count, count);
        }

    }

    protected void createGroupStage(){
        rtc().createStage(StageType.group);
    }

    protected void layoutMatches(Stage stage){
        rtc().layoutMatches(stage.getId());
    }


    protected Stage getStage(int index){
        return rtc().getTournament().getStages().get(index);
    }

    protected void update(Match m, int homeScore, int outScore){
        rtc().updateMatchResult(m.getId(), Result.newInstance(new int[]{homeScore}, new int[]{outScore}));
    }

    protected void update(Stage stage, StageSettings settings) {
        RTCTest.getInstance().updateStageSettings(stage.getId(), settings);

    }


}
