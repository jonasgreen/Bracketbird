package com.bracketbird.client.model.tournament;

import java.util.List;

public class TournamentLevelStateFactory {

    public LevelState createState(Stage level){
        if (level.hasEndingTeams()) {
            return LevelState.finished;
        }
        if (!level.hasMatches()) {
            return LevelState.notReady;
        }
        boolean someIsNonFinished = false;
        boolean someIsFinished = false;

        List<Match> matches = level.getMatches();

        for (Match match : matches) {
            //matches that are walkovers should be treated first
            if (match.isWalkover()) {
                //ignore
            }
            else if (match.isFinish()) {
                someIsFinished = true;
            }
            else {
                someIsNonFinished = true;
            }
        }
        //all matches are finished or is walkover - ending teams had not been set though
        if (!someIsNonFinished) {
            //if(isKnockout()){
            //  return new LevelStateInFinished();
            //}
            return LevelState.donePlaying;
        }
        //if non is finished - matches is layed out.
        else if (!someIsFinished) {
            return LevelState.ready;
        }
        else {
            return LevelState.inProgress;
        }
    }


}
