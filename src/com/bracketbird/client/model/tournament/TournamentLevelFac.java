package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.StageType;
import com.bracketbird.client.appcontrol.SystemException;
import com.bracketbird.client.model.FindingRankingConstant;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TournamentLevelFac {
    public static Stage create(Tournament t, StageType type) {
        Stage level;
        if (StageType.knockout == type) {
            level = createCup(t);
        }
        else if (StageType.group == type) {
            level = createGroup(t);
        }
        else {
            throw new SystemException("Type of tournament level is not supported. Level=" + type);
        }
        return level;
    }

    public static GroupStage createGroup(Tournament t) {
        GroupStage stage = new GroupStage(t);
        StageSettings ss = new StageSettings();

        ss.setPointsOfVictory(2);
        ss.setPointsOfDraw(1);
        ss.setNumberOfGroups(1);
        ss.setNumberOfRepeats(1);

        List<Integer> rules = new ArrayList<Integer>();
        rules.add(FindingRankingConstant.GOALS_DIFFERENCE.getValue());
        ss.setRankingRules(rules);
        stage.setSettings(ss);

        stage.addStateHandler(t);
        return stage;
    }

    public static KnockoutStage createCup(Tournament t) {
        KnockoutStage stage = new KnockoutStage(t);
        StageSettings ss = new StageSettings();
        ss.setEliminationType(1);//single elim
        stage.setSettings(ss);

        stage.addStateHandler(t);
        return stage;
    }
}
