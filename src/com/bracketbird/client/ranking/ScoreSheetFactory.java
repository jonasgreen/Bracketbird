package com.bracketbird.client.ranking;

import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.model.tournament.Result;

public class ScoreSheetFactory {

    private int pointsOfVictory;
    private int pointsOfDraw;

    public ScoreSheetFactory(int pointsOfVictory, int pointsOfDraw) {
        this.pointsOfVictory = pointsOfVictory;
        this.pointsOfDraw = pointsOfDraw;
    }

    public MatchScoreSheets createScoreSheets(Match match) {
        Result r = match.getResult();
        return new MatchScoreSheets(createHomeScoreSheet(r), createOutScoreSheet(r));
    }

    private ScoreSheet createOutScoreSheet(Result r) {
        if(r == null){
            return new EmptyScoreSheet();
        }

        ScoreSheet sheet = new ScoreSheet();
        sheet.setPlayedMatches(1);
        sheet.setScoredGoals(r.getTotalScoreOut());
        sheet.setReceivedGoals(r.getTotalScoreHome());

        if (r.isDraw()) {
            sheet.setDrawMatches(1);
            sheet.setPoints(pointsOfDraw);
        }
        else if (r.outIsWinning()) {
            sheet.setWonMatches(1);
            sheet.setPoints(pointsOfVictory);
        }
        else {
            sheet.setLostMatches(1);
            sheet.setPoints(-pointsOfVictory);
        }

        return sheet;
    }

    private ScoreSheet createHomeScoreSheet(Result r) {
        if(r == null){
            return new EmptyScoreSheet();
        }
        ScoreSheet sheet = new ScoreSheet();
        sheet.setPlayedMatches(1);
        sheet.setScoredGoals(r.getTotalScoreHome());
        sheet.setReceivedGoals(r.getTotalScoreOut());

        if (r.isDraw()) {
            sheet.setDrawMatches(1);
            sheet.setPoints(pointsOfDraw);
        }
        else if (r.homeIsWinning()) {
            sheet.setWonMatches(1);
            sheet.setPoints(pointsOfVictory);
        }
        else {
            sheet.setLostMatches(1);
            sheet.setPoints(-pointsOfVictory);
        }

        return sheet;
    }

}
