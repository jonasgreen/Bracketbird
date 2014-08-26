package com.bracketbird.client.gui.rtc.ranking;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.RTCLayoutFac2;
import com.bracketbird.client.gui.rtc.matches.FinalRankRow;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.TextLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 *
 */
public class RankingViewPage extends FlowPanelPage<RankingViewPageController> {

    private SimpleFlowComponent content;
    private TournamentLevel activeLevel;
    private RankingPanel rankingPanel;

    public RankingViewPage() {
        super();
       // content = new SimpleFlowComponent();
       // add(content);
      //  content.add(new ViewTeamsPanel());//show teams
    }

    public void init() {
       /* RTC.getInstance().getTournament().addStateListener(new TournamentListener<TournamentStateChangedEvent>() {
            public void onChange(TournamentStateChangedEvent event) {
                repaint(event);
            }
        });


        RTC.getInstance().getTournament().addLevelListener(new TournamentListener<TournamentLevelEvent>() {
            public void onChange(TournamentLevelEvent event) {
                if (event.getAction() == TournamentLevelEvent.LevelAction.create) {
                    final TournamentLevel level = event.getLevel();

                    level.addStateListener(new TournamentListener<LevelStateEvent>() {
                        public void onChange(LevelStateEvent event) {
                            if (event.getNewState() instanceof LevelStateMatchesLayedOut) {
                                matchesLayedOut(level);
                            }
                            else if (event.getNewState() instanceof LevelStateInFinished) {
                                levelFinished(level);
                            }
                            else if(event.getNewState() instanceof LevelStateInProgress){
                                if (activeLevel != null && !activeLevel.equals(event.getLevel())) {
                                    matchesLayedOut(event.getLevel());
                                }
                            }
                        }

                    });
                }
                else if (event.getAction() == TournamentLevelEvent.LevelAction.delete) {
                    if (activeLevel != null && activeLevel.equals(event.getLevel())) {
                        clear();
                    }
                }

            }
        });
*/
    }

    private void levelFinished(TournamentLevel level) {

    }

    public void clear() {
        content.clear();
        activeLevel = null;
        rankingPanel = null;
    }

    private void matchesLayedOut(TournamentLevel level) {
        clear();
        activeLevel = level;
        rankingPanel = createPanel(activeLevel);
        content.add(rankingPanel.getContent());
        if (getContentPanel().isAttached()) {
            rankingPanel.relayout();
        }
        //   paint();
    }


    protected void setSubPageHolder(Page subPage) {
        //ignore
    }


    public void repaint(TournamentStateChangedEvent event) {
        Tournament t = RTC.getInstance().getTournament();
        if (t.isReady() || t.isNotReady()) {
            content.add(new ViewTeamsPanel());//show teams
        }

        else if (t.isFinish()) {
            List<Team[]> allTeams = new ArrayList<Team[]>();

            TournamentLevel previous = null;
            for (int i = t.getLevels().size() - 1; i >= 0; i--) {
                TournamentLevel level = t.getLevels().get(i);
                if(previous != null){
                    allTeams.addAll(removeTeams(level, previous));
                }
                else{
                    allTeams.addAll(level.getEndingTeams());
                }
                previous =level;
            }


            content.add(buildFinalRankingCup(allTeams));
        }
        else {
            TournamentLevel levelInPogress = t.getLevelInPogress();
            if (levelInPogress != null) {
                matchesLayedOut(levelInPogress);
            }
        }
    }

    private Collection<? extends Team[]> removeTeams(TournamentLevel level, TournamentLevel previous) {
        List<Team[]> teams = new ArrayList<Team[]>();
        if(previous.getStartingTeams().size() == level.getStartingTeams().size()){
            return teams;
        }

        HashSet<Team> teamsToRemove = new HashSet<Team>();
        teamsToRemove.addAll(previous.getStartingTeams());

        for (Team[] ts : level.getEndingTeams()) {
            List<Team> list = new ArrayList<Team>();
            for (Team t : ts) {
                if(!teamsToRemove.contains(t)){
                    list.add(t);
                }
            }
            if(!list.isEmpty()){
                int i = 0;
                Team[] toAdd = new Team[list.size()];
                for (Team team : list) {
                    toAdd[i++] = team;
                }
                teams.add(toAdd);
            }
        }

        return teams;
    }


    private VerticalComponent buildFinalRankingCup(List<Team[]> finalRankOfTeams) {

        VerticalComponent vc = new VerticalComponent();
        vc.add(new LabelComponent("Final ranking"), RTCLayoutFac2.h1().padding(20));
        TextLayout rLayout = new TextLayout().padding(2, 5, 2, 20);

        int index = 1;
        for (Team[] teams : finalRankOfTeams) {
            if (teams.length == 1) {
                vc.add(new FinalRankRow(teams[0], index++), rLayout);
            }
            else {
                for (Team team : teams) {
                    vc.add(new FinalRankRow(team, index + "-" + (index + teams.length - 1)), rLayout);
                }
                index = index + teams.length;
            }
        }

        return vc;
    }


    private RankingPanel createPanel(TournamentLevel level) {
        if (level.isCup()) {
            return new CupRankingPanel(level);
        }
        else {
            return new GroupRankingPanel(level);
        }
    }

    public RankingPanel getRankingPanel() {
        return rankingPanel;
    }
}