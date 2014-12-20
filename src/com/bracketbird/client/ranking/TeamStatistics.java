package com.bracketbird.client.ranking;

import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.tournament.Match;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class TeamStatistics {

    private final Team team;
    private Map<Match, ScoreSheet> scoreSheetMap = new HashMap<>();

    private ScoreSheet totalScoreSheet = new EmptyScoreSheet();

    public TeamStatistics(Team team) {
        this.team = team;
    }

    public void update(Match match, ScoreSheet scoreSheet) {
        removeOldScores(match);
        addNewScores(match, scoreSheet);
    }

    private void addNewScores(Match match, ScoreSheet scoreSheet) {
        totalScoreSheet = totalScoreSheet.add(scoreSheet);
        scoreSheetMap.put(match, scoreSheet);
    }

    private void removeOldScores(Match match) {
        totalScoreSheet = totalScoreSheet.subtract(scoreSheetMap.get(match));
    }

    public Team getTeam() {
        return team;
    }

    public ScoreSheet getTotalScoreSheet() {
        return totalScoreSheet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamStatistics that = (TeamStatistics) o;

        if (!team.equals(that.team)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return team.hashCode();
    }


}
