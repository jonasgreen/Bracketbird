package com.bracketbird.client.model.tournament;

import com.bracketbird.clientcore.model.StateModel;

import java.util.List;

public class StateCounter {

    private int countNotReady = 0;
    private int countReady = 0;
    private int countInProgress = 0;
    private int countDonePlaying = 0;
    private int countFinished = 0;


    public void count(NotReady notReady) {
        countNotReady++;
    }

    public void count(Ready ready) {
        countReady++;
    }

    public void count(InProgress inProgress) {
        countInProgress++;
    }

    public void count(DonePlaying donePlaying) {
        countDonePlaying++;
    }

    public void count(Finished finished) {
        countFinished++;
    }

    public int getCountNotReady() {
        return countNotReady;
    }

    public int getCountReady() {
        return countReady;
    }

    public int getCountInProgress() {
        return countInProgress;
    }

    public int getCountDonePlaying() {
        return countDonePlaying;
    }

    public int getCountFinished() {
        return countFinished;
    }

    public boolean allIsReady(List<? extends StateModel> children) {
        return children.size() == countReady;
    }

    public boolean allIsNotReady(List<? extends StateModel> children) {
        return children.size() == countNotReady;
    }

    public boolean allIsBelowInProgress() {
        return countInProgress == 0 && countDonePlaying == 0 && countFinished == 0;
    }

    public boolean allIsAboveInProgress() {
        return countInProgress == 0 && countNotReady == 0 && countReady == 0;
    }
}
