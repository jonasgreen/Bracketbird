package com.bracketbird.client.model.tournament;

import java.io.Serializable;

/**
 *
 */
public abstract class TournamentState implements Serializable{

    protected String name;

    protected TournamentState() {
    }

    protected TournamentState(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TournamentState that = (TournamentState) o;

        if (!name.equals(that.name)) {
            return false;
        }

        return true;
    }

    public abstract boolean isStartet();

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "TournamentState{" +
                "name='" + name + '\'' +
                '}';
    }
}
