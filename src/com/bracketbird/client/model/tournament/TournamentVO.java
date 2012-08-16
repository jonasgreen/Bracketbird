package com.bracketbird.client.model.tournament;

import java.io.Serializable;


/**
 *
 */
public class TournamentVO implements Serializable{
    private static final long serialVersionUID = 2179820059866310149L;

    private String name;

    public TournamentVO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}