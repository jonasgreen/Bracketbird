package com.bracketbird.client.model.tournament;

import java.io.Serializable;

/**
 *
 */
public abstract class MatchState implements Serializable{

    protected String name;

    protected MatchState() {
    }

    protected MatchState(String name) {
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

        MatchState that = (MatchState) o;

        if (!name.equals(that.name)) {
            return false;
        }

        return true;
    }


    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "MatchState{" +
                "name='" + name + '\'' +
                '}';
    }
}

