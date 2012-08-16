package com.bracketbird.client.model;


import com.bracketbird.client.model.tournament.Round;

import java.util.List;


public abstract class Scheduler<K extends Round> {



    public Scheduler() {
    }

    public abstract List<K> getRounds();


    @Override
    public String toString() {
        return "GroupScheduler{" +
                "\nrounds=" + getRounds() +

                '}';
    }
}
