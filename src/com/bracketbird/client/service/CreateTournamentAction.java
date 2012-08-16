package com.bracketbird.client.service;

import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class CreateTournamentAction extends AbstractAction  implements Action<SingleResult>{
    private static final long serialVersionUID = 413483738729224787L;

    private String nameOfTournament;
    private UserId userId;


    public CreateTournamentAction() {
    }

    public CreateTournamentAction(String nameOfTournament, UserId userId) {
        this.userId = userId;
        this.nameOfTournament = nameOfTournament;
    }

    public String getNameOfTournament() {
        return nameOfTournament;
    }

    public void setNameOfTournament(String nameOfTournament) {
        this.nameOfTournament = nameOfTournament;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }
}