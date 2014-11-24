package com.bracketbird.client.model;

import com.bracketbird.client.model.keys.TeamId;


/**
 *
 */
public class WalkOverTeam extends Team {
    
    private static final long serialVersionUID = 2645960958030026466L;

    public static int idCount = 1;


    public WalkOverTeam(String wName) {
        super(wName, -1);
        setId(new TeamId("walkoverTeamId "+idCount++));
    }


    public boolean isARealTeam(){
        return false;
    }



}
