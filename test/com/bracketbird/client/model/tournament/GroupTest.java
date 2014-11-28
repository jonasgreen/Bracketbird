package com.bracketbird.client.model.tournament;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupTest extends BracketBirdTest {

    @Test
    public void testOneGroupTwoTeams() throws Exception {
        createTeams(2);
        createGroupStage();
        layoutMatches(getStage(0));

        //all draw
        for (Match match : getStage(0).getMatches()) {
            updateResult(match, 1, 1);
        }


        Assert.assertEquals(rtc().getTournament().getState(), LevelState.donePlaying);

        GroupStage g = (GroupStage) getStage(0);
        Assert.assertEquals(g.getState(), LevelState.donePlaying);

        //all mathces finished
        for (Match match : getStage(0).getMatches()) {
            Assert.assertEquals(match.getState(), LevelState.finished);
        }

        //////// CHANGING MATCH TO HAVE A WINNER

        updateResult(getStage(0).getMatches().get(0), 1, 2);

        Assert.assertEquals(rtc().getTournament().getState(), LevelState.finished);

        g = (GroupStage) getStage(0);
        Assert.assertEquals(g.getState(), LevelState.finished);


        //all mathces finished
        for (Match match : getStage(0).getMatches()) {
            Assert.assertEquals(match.getState(), LevelState.finished);
        }


        System.out.println("done");


    }


}