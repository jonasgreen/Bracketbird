package com.bracketbird.client.pages.matches;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.matches.FinalGroupRankingPanel;
import com.bracketbird.client.gui.rtc.matches.FinalRankRow;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.model.tournament.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LevelMatchesPanel extends FlowPanel {

    private TournamentLevel level;

    private SimplePanel levelEmptyPanelHolder = new SimplePanel();
    private SimplePanel matchesHolder = new SimplePanel();
    private SimplePanel finalRankingHolder = new SimplePanel();

    private int levelCount;

    private void reset() {
        levelEmptyPanelHolder.clear();
        matchesHolder.clear();
        finalRankingHolder.clear();
    }

    public LevelMatchesPanel(TournamentLevel l, int levelCount) {
        super();
        this.levelCount = levelCount;
        this.level = l;


        add(levelEmptyPanelHolder);
        add(matchesHolder);
        add(finalRankingHolder);

        level.addStateListener(new TournamentListener<LevelStateEvent>() {
            public void onChange(LevelStateEvent event) {
                handleStateChange();
            }
        });

        handleStateChange();
    }

    private void handleStateChange() {
        if (level.isMatchesLayedOut()) {
            showMatchesLayedoutPanel();
        }
        else if (level.isEmpty()) {
            showLevelEmptyPanel();
        }
        else if (level.isAllMatchesPlayed()) {
            showAllMatchesPlayedRanking();
        }
        else if (level.isFinish()) {
            showFinalRanking(level.getEndingTeams());
        }
        else if (level.isInProgress()) {
            showMatchesLayedoutPanel();
        }
    }

    public void showAllMatchesPlayedRanking() {
        if (level.isKnockout()) {

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
            FinalGroupRankingPanel fr = new FinalGroupRankingPanel((Group) level);
            finalRankingHolder.add(fr);
            fr.build();
        }

    }

    public void showFinalRanking(List<Team[]> finalRankOfTeams) {
        if (level.isKnockout()) {
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
            matchesHolder.add(new MatchesTable(level));
        }
    }

    public void showLevelEmptyPanel() {
        reset();
        //TODO - if one stage - only layout matches + add listeners
        Button b = new Button("Layout matches - "+ (level.isKnockout() ? "knockout" : "'group'") +" stage");
        b.setStyleName("primaryButton");
        b.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                RTC.getInstance().layoutMatches(level.getId());
            }
        });

        levelEmptyPanelHolder.add(b);
    }


    public TournamentLevel getLevel() {
        return level;
    }
}
