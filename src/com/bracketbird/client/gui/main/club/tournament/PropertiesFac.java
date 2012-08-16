package com.bracketbird.client.gui.main.club.tournament;


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


    public static ListContainer<Integer> typeOfTournament(){
        ListContainer<Integer> sc = new ListContainer<Integer>("Type of tournament", KeyValueConstant.TYPE_OF_TOURNAMENT, 0, true, false);
        sc.setTitle("Choose the type of tournament.");
        return sc;
    }

    public static ListContainer<Integer> bestOfSets(){
        ListContainer<Integer> sc = new ListContainer<Integer>("Each match is best of", KeyValueConstant.BEST_OF_SETS, 0, false, true);
        sc.setTitle("This can be tricky. Notice that a normal soccer match is one set (90 min) with a break in the middle. And a soccer match in a cup play is two sets - one personal and one away.");
        return sc;
    }

    public static ListContainer<Boolean> drawPossible(){
        ListContainer<Boolean> sc = new ListContainer<Boolean>("Is draw possible", KeyValueConstant.YES_NO, 0, false, true);
        sc.setTitle("Are a match allowed to end as a draw");
        return sc;
    }

    public static AddRemoveListContainer<Integer> rankingRules(){
        AddRemoveListContainer<Integer> arc = new AddRemoveListContainer<Integer>("Ranking rules when teams have equal points", FindingRankingConstant.LIST, true);
        arc.setTitle("When teams has the same amount of points, theese rules (Starting from the top) define the final ranking of those teams.");
        StyleIt.add(arc.getLabel(), P.WRAP);
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
