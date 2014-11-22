package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.LevelType;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.*;

import java.util.*;

/**
 *
 */
public class TournamentLevelFac {
    public static TournamentStage create(Tournament t, LevelType type) {
        TournamentStage level;
        if (LevelType.knockout == type) {
            level = createCup(t);
        }
        else if (LevelType.group == type) {
            level = createGroup(t);
        }
        else {
            throw new SystemException("Type of tournament level is not supported. Level=" + type);
        }
        return level;
    }

    public static GroupStage createGroup(Tournament t) {
        GroupStage level = new GroupStage(t);
        StageSettings ss = new StageSettings();

        ss.setPointsOfVictory(2);
        ss.setPointsOfDraw(1);
        ss.setNumberOfGroups(1);
        ss.setNumberOfRepeats(1);

        List<Integer> rules = new ArrayList<Integer>();
        rules.add(FindingRankingConstant.GOALS_DIFFERENCE.getValue());
        ss.setRankingRules(rules);

        level.setStageSettings(ss);
        return level;
    }

    public static KnockoutStage createCup(Tournament t) {
        KnockoutStage level = new KnockoutStage(t);
        StageSettings ss = new StageSettings();
        ss.setEliminationType(1);//single elim

        level.setStageSettings(ss);
        return level;
    }
}
