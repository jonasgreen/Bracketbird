package com.bracketbird.client.model;

import java.util.Map;
import java.util.HashMap;

/**
 *
 */
public class FindingRankingConstant extends IntegerConstant{
    private static final long serialVersionUID = -9111326844795214267L;

    //filled in constructor
    public static Map<Integer, FindingRankingConstant> itemMap = new HashMap<Integer, FindingRankingConstant>();

    public static FindingRankingConstant MOST_POINTS = new FindingRankingConstant("Most points", 0);

    public static FindingRankingConstant GOALS_DIFFERENCE = new FindingRankingConstant("Goal difference", 10);
    public static FindingRankingConstant GOALS_SCORED = new FindingRankingConstant("Goals scored", 20);
    public static FindingRankingConstant NUMBER_OF_VICTORIES = new FindingRankingConstant("Victories", 30);
    public static FindingRankingConstant MUTUAL_MATCH = new FindingRankingConstant("Mutual match", 40);
    public static FindingRankingConstant BEATEN_TEAMS_RANKING = new FindingRankingConstant("Ranking of opponents", 50);



    public static KeyValueList<Integer> LIST = new KeyValueList<Integer>();
    static{
        LIST.add(GOALS_DIFFERENCE);
        LIST.add(GOALS_SCORED);
        //LIST.add(MUTUAL_MATCH);
        //LIST.add(NUMBER_OF_VICTORIES);
        //LIST.add(BEATEN_TEAMS_RANKING);                
    }

    public FindingRankingConstant() {
    }

    public static void init(){

    }

    private FindingRankingConstant(String text, Integer value) {
        super(text, value);
        itemMap.put(value, this);

    }


}