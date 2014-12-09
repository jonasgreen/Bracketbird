package com.bracketbird.client.model.tournament;


import com.bracketbird.client.model.ranking.RankingStep;

import java.util.List;

public class PrintRanking {

    public void print(GroupRanking groupRanking) {
        StringBuilder sb = new StringBuilder();

        sb.append("Ranking of " + groupRanking.getGroup().getName()).append("\n");
        int i = 1;
        for (RankingStep step : groupRanking.getRanking()) {

            List<TeamStatistics> list = step.getTeamStatistics();
            if (list.size() == 1) {
                sb.append(i++).append("\t: ").append(list.get(0)).append("\n");

            }
            else {
                String rank = "" + i + "-" + (list.size()-1 + i);
                for (TeamStatistics stat : list) {
                    sb.append(rank).append("\t: ").append(stat).append("\n");
                }
                i += list.size();
            }
        }

        System.out.println(sb.toString());

    }


    private String name(LevelState state) {
        return state.getClass().getSimpleName() + "\n";
    }
}
