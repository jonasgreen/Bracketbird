package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.RTCLayoutFac2;
import com.bracketbird.client.model.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class LevelResultPanel extends VerticalComponent {


    private TournamentLevel level;
    private SimplePanelComponent levelEmptyPanelHolder = new SimplePanelComponent();
    private SimplePanelComponent matchesHolder = new SimplePanelComponent();
    private SimplePanelComponent finalRankingHolder = new SimplePanelComponent();
    private int levelCount;

    private TournamentListener<LevelStateEvent> stateListener = new TournamentListener<LevelStateEvent>() {
        public void onChange(LevelStateEvent event) {
            if (event.getNewState() instanceof LevelStateMatchesLayedOut) {
                showMatchesLayedoutPanel();
            }
            else if (event.getNewState() instanceof LevelStateEmpty) {
                showLevelEmptyPanel();
            }
            else if (event.getNewState() instanceof LevelStateAllMatchesPlayed) {
                showAllMatchesPlayedRanking(event.isClientEvent());
            }
            else if (event.getNewState() instanceof LevelStateInFinished) {
                showFinalRanking(level.getEndingTeams());
            }
            else if (event.getNewState() instanceof LevelStateInProgress) {
                showLevelInProgress();
            }
        }


    };


    private void reset() {
        levelEmptyPanelHolder.clear();
        matchesHolder.clear();
        finalRankingHolder.clear();
    }

    public LevelResultPanel(TournamentLevel l, int levelCount) {
        super();
        this.levelCount = levelCount;
        this.level = l;
        init();
    }

    private void init() {
        level.addStateListener(stateListener);
        add(getHeaderLabel(), RTCLayoutFac2.h1());
        add(levelEmptyPanelHolder);
        add(matchesHolder, new TextLayout(10, 0, 30, 0, null, "100%"));
        add(finalRankingHolder, new TextLayout(0, 0, 30, 0, null, "100%"));
        initialSetup();
    }

    public void showAllMatchesPlayedRanking(boolean fromClient) {
        if (level.isKnockout() && fromClient) {

            //find ranking and set ending teams
            List<TeamId[]> endingTeams = new ArrayList<TeamId[]>();
            for (Round round : level.getRounds()) {
                List<? extends Match> matches = round.getMatches();
                List<Team> losingTeams = new ArrayList<Team>();
                for (Match match : matches) {
                    Team t = match.getLosingTeam();
                    if(t.isARealTeam()){
                        losingTeams.add(t);
                    }
                }
                int ii = 0;
                TeamId[] teams = new TeamId[losingTeams.size()];
                for (Team losingTeam : losingTeams) {
                    teams[ii++] = losingTeam.getId();
                }
                endingTeams.add(0,teams);
            }

            Team winner = level.getRounds().get(level.getRounds().size()-1).getMatches().get(0).getWinningTeam();
            endingTeams.add(0, new TeamId[]{winner.getId()});
            RTC.getInstance().levelFinished(level.getId(), endingTeams);
        }
        else {
            FinalGroupRankingPanel fr = new FinalGroupRankingPanel((Group) level);
            finalRankingHolder.add(fr, new TextLayout(null, "100%").backgroundGreyLight().paddingBottom(30));
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
        VerticalComponent vc = new VerticalComponent();
        vc.add(new LabelComponent("Final ranking group level"), FinalRankListPanel.titleLayout);

        int positionCount = 1;
        TextLayout rLayout = new TextLayout(null, "350px").padding(2, 5, 2, 10);

        for (Team[] teams : finalRankOfTeams) {
            for (Team team : teams) {
                vc.add(new FinalRankRow(team, positionCount++), rLayout);
            }
        }
        finalRankingHolder.add(vc, new TextLayout(null, "100%").backgroundGreyLight().paddingBottom(30));

    }


    private void buildFinalRankingCup(List<Team[]> finalRankOfTeams) {
        VerticalComponent vc = new VerticalComponent();
        vc.add(new LabelComponent("Final ranking cup level"), FinalRankListPanel.titleLayout);

        TextLayout rLayout = new TextLayout(null, "350px").padding(2, 5, 2, 10);

        int index = 1;
        for (Team[] teams : finalRankOfTeams) {
            if(teams.length == 1){
                vc.add(new FinalRankRow(teams[0], index++), rLayout);
            }
            else{
                for (Team team : teams) {
                    vc.add(new FinalRankRow(team, index+"-"+(index + teams.length-1)), rLayout);
                }
                index = index+teams.length;
            }
        }

        finalRankingHolder.add(vc, new TextLayout(null, "100%").backgroundGreyLight().paddingBottom(30));
    }


    private LabelComponent getHeaderLabel() {
        return new LabelComponent("Matches " + levelCount + ". stage (" + level.getName() + ")");
    }

    private void initialSetup() {
        if (level.isEmpty()) {
            showLevelEmptyPanel();
        }
        else {
            showMatchesLayedoutPanel();
        }
    }


    public void showMatchesLayedoutPanel() {
        reset();
        matchesHolder.add(new LevelMatchesLayedOutPanel(level), new TextLayout(null, "100%"));
    }

    public void showLevelEmptyPanel() {
        reset();
        //levelEmptyPanelHolder.add(new LevelEmptyPanel(level));
    }

    private void showLevelInProgress() {
        finalRankingHolder.clear();
    }


}
