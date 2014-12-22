package com.bracketbird.client.ranking;

import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.event.UpdateDispatcher;
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

    public UpdateDispatcher<ScoreSheet> scoreSheetDispatcher = new UpdateDispatcher<>();


    public TeamStatistics(Team team) {
        this.team = team;
    }

    public void update(Match match, ScoreSheet scoreSheet, boolean isFromClient) {
        ScoreSheet old = totalScoreSheet;

        //remove old score sheet
        ScoreSheet s = scoreSheetMap.get(match);
        totalScoreSheet = totalScoreSheet.subtract(s);

        //add new score sheet
        totalScoreSheet = totalScoreSheet.add(scoreSheet);
        scoreSheetMap.put(match, scoreSheet);

        scoreSheetDispatcher.fireEvent(old, totalScoreSheet, isFromClient);
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
