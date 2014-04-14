package com.bracketbird.client.gui.rtc.settings;


import com.bracketbird.client.gui.main.PropertiesFac;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.model.*;

import java.util.*;

/**
 *
 */
public class GroupSettingsPanel extends SettingsPanel {

    public ListContainer<Integer> TIMES_MEET = PropertiesFac.timesEachTeamMeetEachOther();
    public IntegerContainer NUMBER_OF_GROUPS = PropertiesFac.numberOfGroups();
    public IntegerContainer POINTS_OF_VICTORY = PropertiesFac.pointsOfVictory();
    public IntegerContainer POINTS_OF_DRAW = PropertiesFac.pointsOfDraw();

    public AddRemoveListContainer<Integer> RANKING_RULES = PropertiesFac.rankingRules();


    public IntegerContainer ENTERING_TEAMS = PropertiesFac.maximumTeams();


    protected GroupSettingsPanel(TournamentLevel level) {
        super("Group stage settings", level);
        if (level.getType() != TournamentLevelConstant.GROUP.getValue()) {
            throw new SystemException("Setting wrong kind of tournamentLevel. " + level.getClass().getName());
        }

        init();
    }

    private void init() {

        addFlexTable(NUMBER_OF_GROUPS, POINTS_OF_VICTORY, POINTS_OF_DRAW, TIMES_MEET,ENTERING_TEAMS);
        //addAdvFlexTable();
        //RANKING_RULES;
        List<Integer> rules = new ArrayList<Integer>();
        rules.add(FindingRankingConstant.GOALS_DIFFERENCE.getValue());
        rules.add(FindingRankingConstant.MUTUAL_MATCH.getValue());
        rules.add(FindingRankingConstant.GOALS_SCORED.getValue());
        rules.add(FindingRankingConstant.NUMBER_OF_VICTORIES.getValue());

        RANKING_RULES.setValue(rules);
    }


    @Override
    public Integer getType() {
        return TournamentLevelConstant.GROUP.getValue();
    }

    @Override
    public void setValue(LevelSettings ss) {
        POINTS_OF_VICTORY.setValue(ss.getPointsOfVictory());
        POINTS_OF_DRAW.setValue(ss.getPointsOfDraw());
        TIMES_MEET.setValue(ss.getNumberOfRepeats());
        NUMBER_OF_GROUPS.setValue(ss.getNumberOfGroups());
        RANKING_RULES.setValue(ss.getRankingRules());
        ENTERING_TEAMS.setValue(ss.getMaxNumberOfTeams());
    }

    @Override
    public LevelSettings getValue() {
        LevelSettings ss = new LevelSettings();
        ss.setPointsOfVictory(POINTS_OF_VICTORY.getValue());
        ss.setPointsOfDraw(POINTS_OF_DRAW.getValue());
        ss.setNumberOfGroups(NUMBER_OF_GROUPS.getValue());
        ss.setNumberOfRepeats(TIMES_MEET.getValue());
        ss.setRankingRules(RANKING_RULES.getValue());
        ss.setMaxNumberOfTeams(ENTERING_TEAMS.getValue());
        return ss;
    }

}