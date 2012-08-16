package com.bracketbird.client.model.tournament;

import java.io.Serializable;

/**
 *
 */
public abstract class TournamentLevelState implements Serializable{
    protected String name;

    protected TournamentLevelState() {
        this.name = this.getClass().getName();
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

        TournamentLevelState that = (TournamentLevelState) o;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "TournamentLevelState{" +
                "name='" + name + '\'' +
                '}';
    }

    public abstract boolean hasStartet();
}
