package com.bracketbird.client.model.tournament;

import com.bracketbird.client.gui.rtc.event.*;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.ranking.LadderWheel;
import com.bracketbird.client.model.ranking.Ranking;
import com.bracketbird.client.model.ranking.RankingType;
import com.google.gwt.event.shared.HandlerRegistration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupRanking {

    private RankingType[] rankingTypes = new RankingType[]{RankingType.point, RankingType.scoreDifference, RankingType.interMatchWinner};

    private Map<Team, TeamStatistics> statisticsMap = new HashMap<Team, TeamStatistics>();
    private StageSettings settings;
    private ModelHandlerList<Match> onChangeHandlers = new ModelHandlerList<Match>();

    private Ranking ranking;

    public GroupRanking(StageSettings settings, List<Match> matches) {
        this.settings = settings;
        this.ranking = new LadderWheel(rankingTypes).first();

        for (final Match match : matches) {
            match.addResultHandler(new ModelEventHandler<Result>() {
                @Override
                public void handleEvent(ModelEvent<Result> event) {
                    resultChanged(match, event);
                }
            });

            //initialize team statistics for all teams
            get(match.getTeamHome());
            get(match.getTeamOut());
        }
    }

    private void resultChanged(Match match, ModelEvent<Result> e) {
        updateRanking(match.getTeamHome(), match, e.getOldValue(), e.getNewValue());
        updateRanking(match.getTeamOut(), match, e.getOldValue(), e.getNewValue());

        onChangeHandlers.fireEvent(new UpdateModelEvent<Match>(e.isFromClient(), match, match));
    }

    private void updateRanking(Team team, Match match, Result oldResult, Result newResult){
        TeamStatistics stat = get(team);
        if(stat == null){
            return;
        }

        ranking.remove(stat);
        stat.update(match, oldResult, newResult);
        ranking.add(stat);
    }

    private TeamStatistics get(Team t){
        TeamStatistics stats = statisticsMap.get(t);
        if(stats == null){
            stats = new TeamStatistics(settings, t);
            statisticsMap.put(t, stats);
        }
        return stats;
    }

    public HandlerRegistration addOnChangeHandler(ModelEventHandler<Match> handler){
        return onChangeHandlers.addHandler(handler);
    }
}
