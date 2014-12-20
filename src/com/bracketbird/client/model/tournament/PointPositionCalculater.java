package com.bracketbird.client.model.tournament;

import com.bracketbird.client.ranking.TeamStatistics;

import java.util.List;
import java.util.TreeMap;

/**
 *
 */
public class PointPositionCalculater extends PositionCalculater {


    public PointPositionCalculater() {
    }


    public TreeMap<Integer, Position> sort(List<TeamStatistics> teamStatisticses) {
        TreeMap<Integer, Position> map = new TreeMap<Integer, Position>(positionComp);
        for (TeamStatistics ps : teamStatisticses) {
            Position p = map.get(ps.getTotalScoreSheet().getPoints());
            if (p == null) {
                p = new Position(ps);
                map.put(ps.getTotalScoreSheet().getPoints(), p);
            }
            else {
                p.add(ps);
            }
        }
        return map;
    }


}
