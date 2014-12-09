package com.bracketbird.client.model.tournament;

import org.testng.annotations.Test;

public class MaxTest extends BracketBirdTest {

    @Test
    public void testOneGroupTwoTeams() throws Exception {
        long start = System.currentTimeMillis();
        createTeams(200);
        createGroupStage();


        StageSettings settings = getStage(0).getSettings();
        settings.setNumberOfGroups(10);

        System.out.println("time 1: " + (System.currentTimeMillis() - start));
        layoutMatches(getStage(0));
        System.out.println("time 2: " + (System.currentTimeMillis() - start));

        System.out.println("Matches: "+getStage(0).getMatches().size());

        //all draw
        for (Match match : getStage(0).getMatches()) {
            update(match, 1, 1);
        }
        System.out.println("time 3: "+(System.currentTimeMillis()-start));


    }



}