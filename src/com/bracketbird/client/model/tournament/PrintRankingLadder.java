package com.bracketbird.client.model.tournament;


import com.bracketbird.client.model.ranking.Ranking;
import com.bracketbird.client.model.ranking.RankingLadder;
import com.bracketbird.client.model.ranking.RankingStep;
import com.bracketbird.client.ranking.TeamStatistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PrintRankingLadder {


    public void print(Ranking r) {
        StringBuilder sb = new StringBuilder();
        print(0, r, sb);
        System.out.println(sb.toString());

    }

    private void print(int tab, Ranking r, StringBuilder sb) {
        if (r instanceof RankingLadder) {
            printLadder(tab + 1, (RankingLadder) r, sb);
        }
        else{
            printStep(tab + 1, (RankingStep) r, sb);
        }
    }

    private void printLadder(int tab, RankingLadder r, StringBuilder sb) {
        printTab(tab, sb);
        sb.append("[").append(r.getId()).append(" ").append(r.getClass().getSimpleName()).append("]\n");

        Map<Integer, Ranking> children = r.getChildren();
        List<Integer> keys = new ArrayList<Integer>(children.keySet());

        Collections.sort(keys, RankingLadder.sortingComp);
        for (Integer key : keys) {
            print(tab + 1, children.get(key), sb);
        }
        sb.append("\n");
    }


    private void printStep(int tab, RankingStep r, StringBuilder sb) {
        printTab(tab, sb);
        sb.append("[").append(r.getId()).append(" ").append(r.getClass().getSimpleName()).append("]\n");
        for (TeamStatistics statistics : r.getTeamStatistics()) {
            printTab(tab+1, sb);
            sb.append(statistics).append("\n");
        }
    }

    private void printTab(int tab, StringBuilder sb){
        int i = 0;
        while (i < tab){
            sb.append("    ");
            i++;
        }
    }
}
