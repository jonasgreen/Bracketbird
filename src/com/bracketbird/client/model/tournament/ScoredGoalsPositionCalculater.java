package com.bracketbird.client.model.tournament;

import java.util.List;
import java.util.TreeMap;

/**
 *
 */
public class ScoredGoalsPositionCalculater extends PositionCalculater {

    public ScoredGoalsPositionCalculater() {
    }


    public TreeMap<Integer, Position> sort(List<TeamResultSum> list) {
        TreeMap<Integer, Position> map = new TreeMap<Integer, Position>(positionComp);
        for (TeamResultSum ps : list) {
            Position p = map.get(ps.getScoredGoals());
            if (p == null) {
                p = new Position(ps);
                map.put(ps.getScoredGoals(), p);
            }
            else {
                p.add(ps);
            }
        }
        return map;
    }


}
