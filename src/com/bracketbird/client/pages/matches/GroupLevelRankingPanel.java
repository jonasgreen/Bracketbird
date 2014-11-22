package com.bracketbird.client.pages.matches;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.keys.TeamId;
import com.bracketbird.client.model.tournament.*;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GroupLevelRankingPanel extends FlowPanel {

    private GroupStage level;
    private SimplePanel rankingPanel = new SimplePanel();

    public GroupLevelRankingPanel(GroupStage level) {
        super();
        this.level = level;
    }

    public void build() {
        add(rankingPanel);

        List<Group> grs = level.getGroups();
        List<GroupPositions> allGroupsPositions = new ArrayList<GroupPositions>();

        //get positions of all groups and see if any groups have teams with same position.
        boolean samePosition = false;

        for (Group gr : grs) {
            GroupPositions gp = new GroupPositions(gr, level.getStageSettings());
            allGroupsPositions.add(gp);
            if (gp.hasTeamsWithSamePosition()) {
                samePosition = true;
            }
        }

        if (samePosition) {
            rankingPanel.add(new AllGroupsRankingPanel(allGroupsPositions, new WhenRanked() {
                @Override
                public void onRanked(List<List<Team>> finalGroupRankings) {
                    buildFinalRanking(finalGroupRankings);
                }
            }));
        }
        else {
            List<List<Team>> list = new ArrayList<List<Team>>();

            for (GroupPositions gr : allGroupsPositions) {
                List<Team> teams = new ArrayList<Team>();
                for (Position p : gr.getPositionOfTeams()) {
                    teams.add(p.getPointsCounters().get(0).getTeam());
                }
                list.add(teams);
            }
            buildFinalRanking(list);
        }
    }


    private void buildFinalRanking(List<List<Team>> finalGroupRankings) {
        rankingPanel.clear();
        List<Position> finalPositions = getFinalPositions(level, finalGroupRankings);
        boolean hasEqualPoints = false;
        for (Position fp : finalPositions) {
            if (fp.hasMoreTeams()) {
                hasEqualPoints = true;
            }
        }

        if (hasEqualPoints) {
            add(new FinalRankingDecisionPanel(level, finalPositions));
        }
        else {
            //updateResult model
            List<TeamId> finalRank = new ArrayList<TeamId>();
            for (Position fp : finalPositions) {
                finalRank.add(fp.getPointsCounters().get(0).getTeam().getId());
            }
            RTC.getInstance().levelFinished(level.getId(), convertFinalRank(finalRank));

        }
    }

    private List<TeamId[]> convertFinalRank(List<TeamId> finalRank) {
        List<TeamId[]> list = new ArrayList<TeamId[]>();
        for (TeamId teamId : finalRank) {
            list.add(new TeamId[]{teamId});
        }
        return list;
    }


    private List<Position> getFinalPositions(GroupStage tl, List<List<Team>> finalGroupRankings) {
        List<Match> matches = tl.getMatches();
        List<Position> positions = new ArrayList<Position>();

        //homogenic = all groups have same number of teams.
        //those teams that make it inhomogenic
        List<Team> restTeams = new ArrayList<Team>();

        int minTeams = Integer.MAX_VALUE;
        int maxTeams = 0;

        for (Group aGroup : tl.getGroups()) {
            int teamsCount = aGroup.getTeams().size();
            if (teamsCount < minTeams) {
                minTeams = teamsCount;
            }
            if (teamsCount > maxTeams) {
                maxTeams = teamsCount;
            }
        }
        boolean isHomogen = (minTeams == maxTeams);

        //if not homogen - then collect the restteams
        if (!isHomogen) {
            for (List<Team> fgr : finalGroupRankings) {
                if (fgr.size() == maxTeams) {
                    restTeams.add(fgr.get(maxTeams - 1));//gets the last one
                }
            }
        }


        int count = 0;
        while (count < minTeams) {
            List<Team> verticalList = new ArrayList<Team>();

            //each number one and each number two etc. is collected in verticalList.
            for (List<Team> teams : finalGroupRankings) {
                verticalList.add(teams.get(count));
            }
            RankingSheet rs = new RankingSheet(matches, verticalList, restTeams, tl.getStageSettings());
            positions.addAll(rs.getPositions());
            count++;
        }

        //adding the rest teams
        if (!isHomogen) {
            RankingSheet rs = new RankingSheet(matches, restTeams, new ArrayList<Team>(), tl.getStageSettings());
            positions.addAll(rs.getPositions());
        }

        return positions;

    }


}
