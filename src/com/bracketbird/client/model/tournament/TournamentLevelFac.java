package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.LevelType;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.*;

import java.util.*;

/**
 *
 */
public class TournamentLevelFac {
    public static TournamentLevel create(Tournament t, LevelType type) {
        TournamentLevel level;
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

    public static Group createGroup(Tournament t) {
        Group level = new Group(t);
        LevelSettings ss = new LevelSettings();

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

    public static Cup createCup(Tournament t) {
        Cup level = new Cup(t);
        LevelSettings ss = new LevelSettings();
        ss.setEliminationType(1);//single elim

        level.setStageSettings(ss);
        return level;
    }
}
