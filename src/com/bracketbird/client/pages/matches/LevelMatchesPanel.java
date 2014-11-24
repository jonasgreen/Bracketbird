package com.bracketbird.client.pages.matches;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.event.ModelEvent;
import com.bracketbird.client.gui.rtc.event.ModelEventHandler;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.model.tournament.*;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LevelMatchesPanel extends FlowPanel {

    private Stage level;

    private SimplePanel levelEmptyPanelHolder = new SimplePanel();
    private SimplePanel matchesHolder = new SimplePanel();
    private SimplePanel finalRankingHolder = new SimplePanel();

    private int levelCount;

    private void reset() {
        levelEmptyPanelHolder.clear();
        matchesHolder.clear();
        finalRankingHolder.clear();
    }

    public LevelMatchesPanel(Stage l, int levelCount) {
        super();
        this.levelCount = levelCount;
        this.level = l;

        add(levelEmptyPanelHolder);
        add(matchesHolder);
        add(finalRankingHolder);

        level.stateHandlers.addHandler(new ModelEventHandler<LevelState>() {
            @Override
            public void handleEvent(ModelEvent<LevelState> event) {
                handleStateChange();
            }
        });

        handleStateChange();
    }

    private void handleStateChange() {
        if (level.isReady()) {
            showMatchesLayedoutPanel();
        }
        else if (level.isNotReady()) {
            showLevelEmptyPanel();
        }
        else if (level.isDonePlaying()) {
            if(!matchesHolder.iterator().hasNext()){
                showMatchesLayedoutPanel();
            }
            showAllMatchesPlayedRanking();
        }
        else if (level.isFinished()) {
            if(!matchesHolder.iterator().hasNext()){
                showMatchesLayedoutPanel();
            }
            //showFinalRanking(level.getEndingTeams());
        }
        else if (level.isInProgress()) {
            showMatchesLayedoutPanel();
        }
    }

    public void showAllMatchesPlayedRanking() {
        if (level.isKnockoutStage()) {
            //find ranking and set ending teams
            List<TeamId[]> endingTeams = new ArrayList<TeamId[]>();
            for (Round round : level.getRounds()) {
                List<? extends Match> matches = round.getMatches();
                List<Team> losingTeams = new ArrayList<Team>();
                for (Match match : matches) {
                    Team t = match.getLosingTeam();
                    if (t.isARealTeam()) {
                        losingTeams.add(t);
                    }
                }
                int ii = 0;
                TeamId[] teams = new TeamId[losingTeams.size()];
                for (Team losingTeam : losingTeams) {
                    teams[ii++] = losingTeam.getId();
                }
                endingTeams.add(0, teams);
            }

            Team winner = level.getRounds().get(level.getRounds().size() - 1).getMatches().get(0).getWinningTeam();
            endingTeams.add(0, new TeamId[]{winner.getId()});
            RTC.getInstance().levelFinished(level.getId(), endingTeams);
        }
        else {
            GroupLevelRankingPanel fr = new GroupLevelRankingPanel((GroupStage) level);
            finalRankingHolder.clear();
            finalRankingHolder.add(fr);
            fr.build();
        }

    }

    public void showFinalRanking(List<Team[]> finalRankOfTeams) {
        if (level.isKnockoutStage()) {
            buildFinalRankingCup(finalRankOfTeams);
        }
        else {
            buildFinalRankingGroup(finalRankOfTeams);
        }

    }


    private void buildFinalRankingGroup(List<Team[]> finalRankOfTeams) {
        FlowPanel panel = new FlowPanel();
        panel.add(new Label("Final ranking group level"));

        int positionCount = 1;

        for (Team[] teams : finalRankOfTeams) {
            for (Team team : teams) {
                panel.add(new FinalRankRow(team, positionCount++));
            }
        }
        finalRankingHolder.clear();
        finalRankingHolder.add(panel);

    }


    private void buildFinalRankingCup(List<Team[]> finalRankOfTeams) {
        FlowPanel panel = new FlowPanel();
        panel.add(new Label("Final ranking cup level"));

        int index = 1;
        for (Team[] teams : finalRankOfTeams) {
            if (teams.length == 1) {
                panel.add(new FinalRankRow(teams[0], index++));
            }
            else {
                for (Team team : teams) {
                    panel.add(new FinalRankRow(team, index + "-" + (index + teams.length - 1)));
                }
                index = index + teams.length;
            }
        }
        finalRankingHolder.clear();
        finalRankingHolder.add(panel);
    }


    private Label getHeaderLabel() {
        Label l = new Label("Matches " + levelCount + ". stage");
        l.setStyleName("levelMatchesHeader_label");
        return l;
    }

    public void showMatchesLayedoutPanel() {
        if(!matchesHolder.iterator().hasNext()){
            reset();
            matchesHolder.add(new MatchesTablePanel(level));
        }
    }

    public void showLevelEmptyPanel() {
        reset();
        levelEmptyPanelHolder.add(new LevelEmptyPanel(level));
    }


    public Stage getLevel() {
        return level;
    }
}
