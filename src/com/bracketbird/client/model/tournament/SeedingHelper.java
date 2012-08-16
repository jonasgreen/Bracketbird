package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.*;

import java.util.*;

/**
 *
 */
public class SeedingHelper {


    public List<Team> seed(List<Team> teams) {
        //validate seeding
        Map<Integer, Team> seedMap = new HashMap<Integer, Team>();


        for (Team team : teams) {
            Integer seeding = team.getSeeding();
            if (seeding != null) {
                seedMap.put(team.getSeeding(), team);
            }
        }
        
        List<Team> seededTeam = new ArrayList<Team>();

        int index = 0;
        while(index < teams.size()){
            Team pt = seedMap.get(index+1);
            seededTeam.add(pt != null ? pt : teams.get(index));
            index++;
        }
        return seededTeam;
    }
}
