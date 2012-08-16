package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.Team;


/**
 *
 */
public class TournamentTeamEvent extends TournamentEvent {

    public static enum Action {
        create,
        delete,
        update
    }

    private Team team;
    private Action action;

    public TournamentTeamEvent(boolean fromClient, Team pt, Action a) {
        super(fromClient);
        this.team = pt;
        this.action = a;
    }

    public Team getTeam() {
        return team;
    }

    public Action getAction() {
        return action;
    }
}