package com.bracketbird.client.model;


import com.bracketbird.client.model.keys.TournamentBrickId;
import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.clientcore.model.Model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class TournamentBrick extends Model<TournamentBrickId> implements Serializable {

    private static final long serialVersionUID = -2644976792626990334L;

    private String tournamentName;
    private Date lastActivityDate;
    private TournamentId tournamentId;

    //todo - tournament type

    public TournamentBrick() {
    }


    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public Date getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public TournamentId getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(TournamentId tournamentId) {
        this.tournamentId = tournamentId;
    }
}