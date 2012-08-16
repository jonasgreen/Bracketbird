package com.bracketbird.client.service;

import com.bracketbird.clientcore.service.AbstractAction;
import com.bracketbird.clientcore.service.Action;

/**
 *
 */
public class GetTournamentAction extends AbstractAction implements Action<TournamentResult>{
    private static final long serialVersionUID = -1095211233453977436L;

    private String tournamentUrl;

    public GetTournamentAction() {
    }

    public GetTournamentAction(String tournamentUrl) {
        this.tournamentUrl = tournamentUrl;
    }

    public String getTournamentUrl() {
        return tournamentUrl;
    }

    public void setTournamentUrl(String tournamentUrl) {
        this.tournamentUrl = tournamentUrl;
    }
}