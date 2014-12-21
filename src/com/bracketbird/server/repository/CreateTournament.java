package com.bracketbird.server.repository;

import com.bracketbird.client.model.keys.TournamentId;

/**
 *
 */
public class CreateTournament extends Creater {

    private static final long serialVersionUID = 2658776839782397621L;

    private String nameOfTournament;
    private TournamentId tournamentId;

    public CreateTournament() {
    }

    public CreateTournament(String nameOfTournament, TournamentId tId) {
        this.nameOfTournament = nameOfTournament;
        this.tournamentId = tId;
    }

    public String getNameOfTournament() {
        return nameOfTournament;
    }

    public void setNameOfTournament(String nameOfTournament) {
        this.nameOfTournament = nameOfTournament;
    }

    public TournamentId getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(TournamentId tournamentId) {
        this.tournamentId = tournamentId;
    }
}