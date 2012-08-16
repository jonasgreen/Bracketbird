package com.bracketbird.client.model;

import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.model.*;

import java.util.*;

/**
 *
 */
public class CreatePlayingTeam extends Creater {
    private static final long serialVersionUID = -4889634072512241251L;

    private List<MemberId> members = new ArrayList<MemberId>();
    private List<String> nonMembers = new ArrayList<String>();


    private TournamentId tournamentId;
    private String name;
    private Integer seeding;


    public CreatePlayingTeam() {
        super();
    }

    public List<MemberId> getMembers() {

        return members;
    }

    public void setMembers(List<MemberId> members) {
        this.members = members;
    }

    public List<String> getNonMembers() {
        return nonMembers;
    }

    public void setNonMembers(List<String> nonMembers) {
        this.nonMembers = nonMembers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeeding() {
        return seeding;
    }

    public void setSeeding(Integer seeding) {
        this.seeding = seeding;
    }

    public TournamentId getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(TournamentId tournamentId) {
        this.tournamentId = tournamentId;
    }
}
