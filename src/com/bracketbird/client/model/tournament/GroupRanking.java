package com.bracketbird.client.model.tournament;

import com.bracketbird.client.gui.rtc.event.ModelEvent;
import com.bracketbird.client.gui.rtc.event.ModelEventHandler;
import com.bracketbird.client.gui.rtc.event.ModelHandlerList;
import com.bracketbird.client.gui.rtc.event.UpdateModelEvent;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.ranking.*;
import com.google.gwt.event.shared.HandlerRegistration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupRanking {

    private RankingLadderType[] rankingTypes = new RankingLadderType[]{RankingLadderType.point, RankingLadderType.scoreDifference, RankingLadderType.scoreTotal};

    private Map<Team, TeamStatistics> statisticsMap = new HashMap<Team, TeamStatistics>();
    private Group group;
    private ModelHandlerList<Match> onChangeHandlers = new ModelHandlerList<Match>();

    private RankingLadder ranking;

    public GroupRanking(Group group, List<Match> matches) {
        this.group = group;

        LadderFactory ladderFactory = AbstractLadderFactory.get().getLinkedFactories(rankingTypes);
        ranking = ladderFactory.create(null, null);

        for (final Match match : matches) {
            match.addResultHandler(new ModelEventHandler<Result>() {
                @Override
                public void handleEvent(ModelEvent<Result> event) {
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
        updateRanking(match.getTeamHome(), match, e.getOldValue(), e.getNewValue());
        updateRanking(match.getTeamOut(), match, e.getOldValue(), e.getNewValue());

        //new PrintRanking().print(this);
        //new PrintRankingLadder().print(this.ranking);
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
            stats = new TeamStatistics(group.getStage().getSettings(), t);
            statisticsMap.put(t, stats);
        }
        return stats;
    }

    public HandlerRegistration addOnChangeHandler(ModelEventHandler<Match> handler){
        return onChangeHandlers.addHandler(handler);
    }

    public Group getGroup() {
        return group;
    }

    public List<RankingStep> getRanking(){
        return ranking.getTotalRanking();
    }
}
