package com.bracketbird.client.model;

import com.bracketbird.client.model.keys.TeamId;


/**
 *
 */
public class SeedingTeam extends Team {
    private static final long serialVersionUID = -8413373363468003258L;
    private static int idCount = 1;

    public SeedingTeam() {
        super("------", -1);
        setId(new TeamId("seedingTeamId "+idCount++));
    }

    public SeedingTeam(int seed) {
        super("Seed "+seed, seed);
        setId(new TeamId("seedingTeamId "+idCount++));
    }


    public boolean isARealTeam(){
        return false;
    }

}
