package com.bracketbird.client.model.tournament;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class Round implements Serializable {
    private static final long serialVersionUID = -990956812294369030L;

    private String name;

    public Round(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract List<? extends Match> getMatches();

    @Override
    public String toString() {
        return "\nGroupRound{" +
                "matches=" + getMatches() +
                '}';
    }

    public abstract int indexOf(Match m);

}
