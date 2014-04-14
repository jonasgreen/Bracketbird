package com.bracketbird.client.model;

import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.model.keys.*;

import java.io.*;


/**
 *
 */
public class CreateTournamentChannel extends Creater implements Serializable {


    private static final long serialVersionUID = 8958408831555130826L;
    private TournamentId tournamentId;

    public CreateTournamentChannel() {
    }

    public TournamentId getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(TournamentId tournamentId) {
        this.tournamentId = tournamentId;
    }

    
}