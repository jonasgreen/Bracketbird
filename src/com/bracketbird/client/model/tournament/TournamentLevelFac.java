package com.bracketbird.client.model.tournament;

import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.*;

import java.util.*;

/**
 *
 */
public class TournamentLevelFac {
    public static TournamentLevel create(Tournament t, Integer type) {
        TournamentLevel level;
        if (type == TournamentLevelConstant.CUP.getValue()) {
            level = createCup(t);
        }
        else if (type == TournamentLevelConstant.GROUP.getValue()) {
            level = createGroup(t);
        }
        else if (type == TournamentLevelConstant.SEEDING.getValue()) {
            level = createSeeding(t);
        }
        else {
            throw new SystemException("Type of tournament level is not supported. Level=" + type);
        }
        return level;
    }

    public static Seeding createSeeding(Tournament t) {
        Seeding level = new Seeding(t);
        level.setType(TournamentLevelConstant.SEEDING.getValue());

        LevelSettings ss = new LevelSettings();
        ss.setNumberOfMatches(5);

        level.setStageSettings(ss);
        return level;
    }

    public static Group createGroup(Tournament t) {
        Group level = new Group(t);
        level.setType(TournamentLevelConstant.GROUP.getValue());
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
        level.setType(TournamentLevelConstant.CUP.getValue());
        LevelSettings ss = new LevelSettings();
        ss.setEliminationType(1);//single elim

        level.setStageSettings(ss);
        return level;
    }
}