package com.bracketbird.client.model.tournament;

import com.bracketbird.client.gui.rtc.event.*;
import com.bracketbird.client.model.Team;
import com.google.gwt.event.shared.HandlerRegistration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupStatistics {

    private Map<Team, TeamStatistics> statisticsMap = new HashMap<Team, TeamStatistics>();
    private StageSettings settings;
    private OnChangeHandlerList changeHandlers = new OnChangeHandlerList();

    public GroupStatistics(StageSettings settings, List<Match> matches) {
        this.settings = settings;
        for (final Match match : matches) {
            match.addResultHandler(new ModelEventHandler<Result>() {
                @Override
                public void handleEvent(ModelEvent<Result> event) {
                    resultChanged(match, event.getOldValue(), event.getNewValue());
                }
            });

            //initialize team statistics for all teams
            get(match.getTeamHome());
            get(match.getTeamOut());
        }
    }

    private void resultChanged(Match m, Result oldResult, Result newResult) {
        get(m.getTeamOut()).updateStats(m, oldResult, newResult);
        get(m.getTeamHome()).updateStats(m, oldResult, newResult);
        changeHandlers.fireEvent();
    }

    private TeamStatistics get(Team t){
        TeamStatistics stats = statisticsMap.get(t);
        if(stats == null){
            stats = new TeamStatistics(settings, t);
            statisticsMap.put(t, stats);
        }
        return stats;
    }

    public HandlerRegistration addOnChangeHandler(OnChangeHandler handler){
        return changeHandlers.addHandler(handler);
    }
}
