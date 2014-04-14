package com.bracketbird.client.service;

import com.bracketbird.clientcore.model.keys.UserId;
import com.bracketbird.clientcore.service.AbstractAction;
import com.bracketbird.clientcore.service.Action;
import com.bracketbird.clientcore.service.SingleResult;

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