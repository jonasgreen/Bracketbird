package com.bracketbird.client.gui.main;


import com.bracketbird.client.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class PropertiesFac {

    public static IntegerContainer maximumTeams(){
        IntegerContainer sc = new IntegerContainer("Maximum number of teams", false);

        sc.setTitle("How many teams are allowed to enter this part of the tournament. Empty indicates unlimited.");
        return sc;
    }

    public static IntegerContainer pointsOfVictory(){
        IntegerContainer sc = new IntegerContainer("Points for winning a match", true);
        sc.setTitle("How many points does the winning team get.");
        return sc;
    }


    public static IntegerContainer pointsOfDraw(){
        IntegerContainer sc = new IntegerContainer("Points for a draw match", true);
        sc.setTitle("How many points does the teams get for a drawing match.");
        return sc;
    }

    public static IntegerContainer numberOfGroups(){
        IntegerContainer sc = new IntegerContainer("Number of groups", true);
        sc.setTitle("You can easily change this when starting up the tournament.");
        return sc;
    }


    public static IntegerContainer numberOfMatches(){
        IntegerContainer sc = new IntegerContainer("Number of matches pr. team", true);
        sc.setTitle("How many matches does each team have to play.");
        return sc;
    }

    public static AddRemoveListContainer<Integer> rankingRules(){
        AddRemoveListContainer<Integer> arc = new AddRemoveListContainer<Integer>("Ranking rules when teams have equal points", FindingRankingConstant.LIST, true);
        arc.setTitle("When teams has the same amount of points, theese rules (Starting from the top) define the final ranking of those teams.");
        //StyleIt.add(arc.getLabel(), P.WRAP);
        return arc;
    }

    public static ListContainer<Integer> timesEachTeamMeetEachOther(){
        ListContainer<Integer> sc = new ListContainer<Integer>("Each team meets all other teams", KeyValueConstant.TIMES_TEAM_MEET_EACH_OTHER, 0, false, true);
        sc.setTitle("How many times all the mathces in the group are played through.");
        return sc;
    }

    public static ListContainer<Integer> eliminationType(){
        ListContainer<Integer> sc = new ListContainer<Integer>("Elimination type", KeyValueConstant.ELIMINATION_TYPE, 0, false, true);
        sc.setTitle("Single elimination is the normal kind of cup system.");
        return sc;
    }





}
