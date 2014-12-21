package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.ranking.*;
import com.bracketbird.client.ranking.MatchScoreSheets;
import com.bracketbird.client.ranking.ScoreSheet;
import com.bracketbird.client.ranking.ScoreSheetFactory;
import com.bracketbird.client.ranking.TeamStatistics;
import com.bracketbird.client.rtc.event.*;
import com.google.gwt.event.shared.HandlerRegistration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupRanking {

    private RankingLadderType[] rankingTypes = new RankingLadderType[]{RankingLadderType.point, RankingLadderType.scoreDifference, RankingLadderType.scoreTotal};

    private Group group;
    private UpdateDispatcher<Match> matchDispatcher = new UpdateDispatcher<>();

    private ScoreSheetFactory scoreSheetFactory;
    private Map<Team, TeamStatistics> statisticsMap = new HashMap<Team, TeamStatistics>();

    private RankingLadder ranking;

    public GroupRanking(Group group, List<Match> matches) {
        this.group = group;

        StageSettings settings = group.getStage().getSettings();
        this.scoreSheetFactory = new ScoreSheetFactory(settings.getPointsOfVictory(), settings.getPointsOfDraw());

        LadderFactory ladderFactory = AbstractLadderFactory.get().getLinkedFactories(rankingTypes);
        ranking = ladderFactory.create(null, null);

        for (final Match match : matches) {
            match.addResultHandler(new UpdateHandler<Result>() {
                @Override
                public void onUpdate(UpdateEvent<Result> event) {
                    resultChanged(match, event);
                }
            });

        }

        //initialize team statistics for all teams
        for (Team team : group.getTeams()) {
            ranking.add(get(team));
        }
    }


    private void resultChanged(Match match, ModelEvent<Result> e) {
        MatchScoreSheets mss = scoreSheetFactory.createScoreSheets(match);
        updateRanking(match, match.getTeamHome(), mss.getHomeScoreSheet());
        updateRanking(match, match.getTeamOut(), mss.getOutScoreSheet());

        //new PrintRanking().print(this);
        //new PrintRankingLadder().print(this.ranking);
        matchDispatcher.fireEvent(match, match, e.isFromClient());
    }

    private void updateRanking(Match match, Team team, ScoreSheet scoreSheet){
        TeamStatistics stat = get(team);
        if(stat == null){
            return;
        }

        ranking.remove(stat);
        stat.update(match, scoreSheet);
        ranking.add(stat);
    }

    private TeamStatistics get(Team t){
        TeamStatistics stats = statisticsMap.get(t);
        if(stats == null){
            stats = new TeamStatistics(t);
            statisticsMap.put(t, stats);
        }
        return stats;
    }

    public HandlerRegistration addOnChangeHandler(UpdateHandler<Match> handler){
        return matchDispatcher.addHandler(handler);
    }

    public Group getGroup() {
        return group;
    }

    public List<RankingStep> getRanking(){
        return ranking.getTotalRanking();
    }
}
