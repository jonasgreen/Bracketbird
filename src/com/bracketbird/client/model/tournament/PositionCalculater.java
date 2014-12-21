package com.bracketbird.client.model.tournament;


import com.bracketbird.client.appcontrol.SystemException;
import com.bracketbird.client.model.FindingRankingConstant;
import com.bracketbird.client.ranking.TeamStatistics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

/**
 *
 */
public abstract class PositionCalculater {

    protected Comparator positionComp = new Comparator<Integer>() {
        public int compare(Integer i1, Integer i2) {
            return -i1.compareTo(i2);
        }
    };


    private PositionCalculater next = null;


    public void addNext(PositionCalculater next) {
        if (this.next != null) {
            this.next.addNext(next);
        }
        else {
            this.next = next;
        }
    }

    public List<Position> calculate(List<TeamStatistics> list) {
        List<Position> positions = new ArrayList<Position>();

        for (Position p : sort(list).values()) {
            //if more than one team has the same position. - use the next calculater to sort them
            if (p.hasMoreTeams() && getNext() != null) {
                List<Position> nextPositions = getNext().calculate(p.getPointsCounters());
                for (Position position : nextPositions) {
                    positions.add(position);
                }
            }
            else {
                positions.add(p);
            }
        }
        return positions;
    }

    protected abstract TreeMap<Integer, Position> sort(List<TeamStatistics> list);


    public PositionCalculater getNext() {
        return next;
    }


    public static PositionCalculater create(Integer rankingConstant) {
        if (FindingRankingConstant.GOALS_DIFFERENCE.getValue() == rankingConstant) {
            return new ScoredGoalsDiffPositionCalculater();
        }
        else if (FindingRankingConstant.GOALS_SCORED.getValue() == rankingConstant) {
            return new ScoredGoalsPositionCalculater();
        }
        throw new SystemException("Positioncalculater with value = " + rankingConstant + " is not supported");


    }
}
