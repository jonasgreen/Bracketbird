package com.bracketbird.client.model.tournament;

import com.bracketbird.client.ranking.TeamStatistics;

import java.util.List;
import java.util.TreeMap;

/**
 *
 */
public class ScoredGoalsPositionCalculater extends PositionCalculater {

    public ScoredGoalsPositionCalculater() {
    }


    public TreeMap<Integer, Position> sort(List<TeamStatistics> list) {
        TreeMap<Integer, Position> map = new TreeMap<Integer, Position>(positionComp);
        for (TeamStatistics ps : list) {
            Position p = map.get(ps.getTotalScoreSheet().getScoredGoals());
            if (p == null) {
                p = new Position(ps);
                map.put(ps.getTotalScoreSheet().getScoredGoals(), p);
            }
            else {
                p.add(ps);
            }
        }
        return map;
    }


}
