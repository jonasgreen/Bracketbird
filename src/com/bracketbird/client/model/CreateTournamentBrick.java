package com.bracketbird.client.model;

import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.clientcore.model.Creater;
import com.bracketbird.clientcore.model.keys.UserId;

import java.io.Serializable;


/**
 *
 */
public class CreateTournamentBrick extends Creater implements Serializable {

    private static final long serialVersionUID = 1694929877043116638L;
    private UserId userId;

    private TournamentId tournamentId;
    private String tournamentName;

    public CreateTournamentBrick() {
    }


    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public TournamentId getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(TournamentId tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }
}