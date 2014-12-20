package com.bracketbird.client.ranking;

public class MatchScoreSheets {

    private ScoreSheet homeScoreSheet;
    private ScoreSheet outScoreSheet;

    public MatchScoreSheets(ScoreSheet homeScoreSheet, ScoreSheet outScoreSheet) {
        this.homeScoreSheet = homeScoreSheet;
        this.outScoreSheet = outScoreSheet;
    }

    public ScoreSheet getHomeScoreSheet() {
        return homeScoreSheet;
    }

    public ScoreSheet getOutScoreSheet() {
        return outScoreSheet;
    }
}
