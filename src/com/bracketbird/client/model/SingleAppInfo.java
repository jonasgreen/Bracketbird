package com.bracketbird.client.model;


import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.client.service.rtc.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.model.keys.*;

import java.io.*;
import java.util.*;

/**
 *
 */
public class SingleAppInfo extends Model implements Serializable {
    private static final long serialVersionUID = 8451785089290918393L;

    private User user;
    private Tournament tournament;
    private Club club;
    private List<REvent> tournamentEventLog;

    public EntityId getId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public SingleAppInfo() {
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SingleAppInfo{" +
                "user=" + user +
                ", tournament=" + tournament +
                ", club=" + club +
                '}';
    }

    public void setTournamentEventLog(List<REvent> tournamentEventLog) {
        this.tournamentEventLog = tournamentEventLog;
    }

    public List<REvent> getTournamentEventLog() {
        return tournamentEventLog;
    }
}