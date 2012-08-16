package com.bracketbird.client.model.tournament;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

/**
 *
 */
public class PointPositionCalculater extends PositionCalculater {


    public PointPositionCalculater() {
    }


    public TreeMap<Integer, Position> sort(List<TeamResultSum> teamResultSums) {
        TreeMap<Integer, Position> map = new TreeMap<Integer, Position>(positionComp);
        for (TeamResultSum ps : teamResultSums) {
            Position p = map.get(ps.getPoints());
            if (p == null) {
                p = new Position(ps);
                map.put(ps.getPoints(), p);
            }
            else {
                p.add(ps);
            }
        }
        return map;
    }


}
