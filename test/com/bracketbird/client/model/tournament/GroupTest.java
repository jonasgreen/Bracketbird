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
            update(match, 1, 1);
        }

        Assert.assertEquals(rtc().getTournament().getState(), LevelState.donePlaying);

        GroupStage g = (GroupStage) getStage(0);
        Assert.assertEquals(g.getState(), LevelState.donePlaying);

        //all mathces finished
        for (Match match : getStage(0).getMatches()) {
            Assert.assertEquals(match.getState(), LevelState.finished);
        }

        //////// CHANGING MATCH TO HAVE A WINNER

        update(getStage(0).getMatches().get(0), 1, 2);

        Assert.assertEquals(rtc().getTournament().getState(), LevelState.finished);

        g = (GroupStage) getStage(0);
        Assert.assertEquals(g.getState(), LevelState.finished);


        //all mathces finished
        for (Match match : getStage(0).getMatches()) {
            Assert.assertEquals(match.getState(), LevelState.finished);
        }

        //////// CHANGING MATCH BACK TO DRAW
        update(getStage(0).getMatches().get(0), 1, 1);
        Assert.assertEquals(rtc().getTournament().getState(), LevelState.donePlaying);

        Assert.assertEquals(g.getState(), LevelState.donePlaying);

        //all mathces finished
        for (Match match : getStage(0).getMatches()) {
            Assert.assertEquals(match.getState(), LevelState.finished);
        }


        System.out.println("done");


    }


    @Test
    public void testTwoGroupsFourTeams() throws Exception {
        createTeams(4);
        createGroupStage();

        GroupStage stage = (GroupStage) getStage(0);

        StageSettings settings = stage.getSettings();
        settings.setNumberOfGroups(2);

        update(stage, settings);

        layoutMatches(stage);

        //all draw
        for (Match match : stage.getMatches()) {
            update(match, 1, 1);
        }

        Assert.assertEquals(rtc().getTournament().getState(), LevelState.donePlaying);

        GroupStage g = (GroupStage) stage;
        Assert.assertEquals(g.getState(), LevelState.donePlaying);

        //all mathces finished
        for (Match match : stage.getMatches()) {
            Assert.assertEquals(match.getState(), LevelState.finished);
        }

        //////// CHANGING MATCH IN GROUP ONE TO HAVE A WINNER

        Group groupOne = stage.getGroups().get(0);
        Group groupTwo = stage.getGroups().get(1);


        update(groupOne.getMatches().get(0), 1, 2);

        Assert.assertEquals(groupOne.getState(), LevelState.finished);
        Assert.assertEquals(groupTwo.getState(), LevelState.donePlaying);
        Assert.assertEquals(rtc().getTournament().getState(), LevelState.donePlaying);


        //all mathces still finished
        for (Match match : stage.getMatches()) {
            Assert.assertEquals(match.getState(), LevelState.finished);
        }


        //////// CHANGING BOTH MATCHES TO HAVE A WINNER - and different scores

        update(groupTwo.getMatches().get(0), 10, 3);

        Assert.assertEquals(groupOne.getState(), LevelState.finished);
        Assert.assertEquals(groupTwo.getState(), LevelState.finished);
        Assert.assertEquals(rtc().getTournament().getState(), LevelState.finished);


        //all mathces still finished
        for (Match match : stage.getMatches()) {
            Assert.assertEquals(match.getState(), LevelState.finished);
        }

        System.out.println("done");


    }



}