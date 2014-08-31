package com.bracketbird.client.model;

import com.bracketbird.client.model.keys.TeamId;


/**
 *
 */
public class SeedingTeam extends Team {
    private static final long serialVersionUID = -8413373363468003258L;
    private static int idCount = 1;

    protected SeedingTeam() {
    }

    public SeedingTeam(String sname) {
        name = sname;
        setId(new TeamId("seedingTeamId "+idCount++));
    }

    public SeedingTeam(int seed) {
        super();
        this.seeding = seed;
        this.name = "Seed "+seed;
        setId(new TeamId("seedingTeamId "+idCount++));
    }


    public boolean isARealTeam(){
        return false;
    }

}
