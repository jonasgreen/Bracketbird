package com.bracketbird.client.model.tournament;

import java.util.List;
import java.util.TreeMap;

/**
 *
 */
public class ScoredGoalsDiffPositionCalculater extends PositionCalculater {

    public ScoredGoalsDiffPositionCalculater() {
    }


    public TreeMap<Integer, Position> sort(List<TeamStatistics> list) {
        TreeMap<Integer, Position> map = new TreeMap<Integer, Position>(positionComp);
        for (TeamStatistics ps : list) {
            Position p = map.get(ps.getGoalDifference());
            if (p == null) {
                p = new Position(ps);
                map.put(ps.getGoalDifference(), p);
            }
            else {
                p.add(ps);
            }
        }
        return map;
    }


}
