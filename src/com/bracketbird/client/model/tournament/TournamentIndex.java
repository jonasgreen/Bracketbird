package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.model.*;

/**
 *
 */
public class TournamentIndex extends Model<TournamentId> {
    private static final long serialVersionUID = -2652779883406315048L;

    private String name;

    public TournamentIndex() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TournamentIndex{" +
                ", name='" + name + '\'' +
                '}';
    }
}