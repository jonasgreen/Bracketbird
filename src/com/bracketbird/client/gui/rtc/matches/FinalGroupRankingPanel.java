package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.model.tournament.*;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class FinalGroupRankingPanel extends VerticalComponent {

    private Group level;
    private ButtonComponent okButton;
    private SimplePanelComponent buttonPanel = new SimplePanelComponent();
    private SimplePanelComponent rankingPanel = new SimplePanelComponent();
    private SimplePanelComponent errorsPanel = new SimplePanelComponent();
    private List<FinalRankListPanel> rows = new ArrayList<FinalRankListPanel>();

    public FinalGroupRankingPanel(Group level) {
        super();
        this.level = level;
    }

    public void build() {
        add(rankingPanel, new TextLayout(null, "100%"));

        List<AGroup> grs = level.getGroups();
        List<List<Position>> groupsPositions = new ArrayList<List<Position>>();

        //get positions of all groups and see if any groups have teams with same position.
        boolean samePosition = false;
        for (AGroup gr : grs) {
            RankingSheet sheet = new RankingSheet(gr.getMatches(), level.getStageSettings());
            List<Position> list = sheet.getPositions();
            groupsPositions.add(list);
            for (Position position : list) {
                if (position.hasMoreTeams()) {
                    samePosition = true;
                }
            }
        }
        if (groupsPositions.size() != 1 || samePosition) {
            layoutGroupsRanking(groupsPositions);
        }
        else {
            List<List<Team>> finalGroupRankings = new ArrayList<List<Team>>();

            for (List<Position> groupsPosition : groupsPositions) {
                List<Team> teams = new ArrayList<Team>();
                for (Position position : groupsPosition) {
                    teams.add(position.getPointsCounters().get(0).getTeam());
                }
                finalGroupRankings.add(teams);
            }

            buildFinalRanking(finalGroupRankings);
        }
    }

    //only called if one or more teams in a group have the same ranking and ranking decicion has to be made.

    private void layoutGroupsRanking(List<List<Position>> groupsPositions) {
        FlowComponent outer = new FlowComponent();

        List<AGroup> grs = level.getGroups();
        int index = 0;
        for (AGroup gr : grs) {
            FlowComponent fl = new FlowComponent();
            FinalRankListPanel fr = new FinalRankListPanel(gr.getName(), groupsPositions.get(index++));
            rows.add(fr);
            fl.add(fr, new TextLayout(null, "280px"));
            outer.add(fl, new TextLayout(0, 50, 0, 0));
            fl.setStyleName("floatleft");
        }
        rankingPanel.add(outer, new TextLayout(20, 0, 0, 0, null, "100%"));

        add(errorsPanel, new TextLayout(20, 0, 0, 10, null, "100%"));
        add(buttonPanel, new TextLayout(null, "100%"));
        buttonPanel.add(new ButtonPanel(Horizontal.LEFT, getOkButton()), new TextLayout(10, 0, 10, 10, null, "100%"));
    }


    public ButtonComponent getOkButton() {
        if (okButton == null) {
            okButton = new ButtonComponent("Accept ranking");
            okButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    List<String> errors = new ArrayList<String>();
                    for (FinalRankListPanel row : rows) {
                        errors.addAll(row.validate());
                    }
                    if (errors.isEmpty()) {
                        List<List<Team>> finalGroupRankings = new ArrayList<List<Team>>();
                        for (FinalRankListPanel row : rows) {
                            finalGroupRankings.add(row.done());
                        }
                        buildFinalRanking(finalGroupRankings);

                    }
                    else {
                        VerticalComponent vc = new VerticalComponent();
                        TextLayout tl = new TextLayout().sizeSmall().bold().colorRed().noWrap().padding(4);
                        for (String errorMsg : errors) {
                            vc.add(new LabelComponent(errorMsg), tl);
                        }
                        errorsPanel.add(vc, new TextLayout().border(1).borderColor(P.COLOR_DARK_RED));
                    }
                }
            });
        }
        return okButton;
    }


    private void buildFinalRanking(List<List<Team>> finalGroupRankings) {
        errorsPanel.removeFromParent();
        buttonPanel.removeFromParent();

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
            //update model
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


    private List<Position> getFinalPositions(Group tl, List<List<Team>> finalGroupRankings) {
        List<Match> matches = tl.getMatches();
        List<Position> positions = new ArrayList<Position>();

        //homogenic = all groups have same number of teams.
        //those teams that make it inhomogenic
        List<Team> restTeams = new ArrayList<Team>();

        int minTeams = Integer.MAX_VALUE;
        int maxTeams = 0;

        for (AGroup aGroup : tl.getGroups()) {
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
