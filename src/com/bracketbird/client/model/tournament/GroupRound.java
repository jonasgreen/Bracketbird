package com.bracketbird.client.model.tournament;


import java.util.*;

/**
 *
 */
public class GroupRound extends Round {
    private static final long serialVersionUID = 7856387235373392743L;

    private List<Match> matches = new ArrayList<Match>();


    public GroupRound(String name) {
        super(name);
    }

    public void add(Match m){
        matches.add(m);
    }

    public List<Match> getMatches() {
        return matches;
    }


    @Override
    public int indexOf(Match m) {
        int index = 0;
        for (Match match : matches) {
            if(match.equals(m)){
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "\nGroupRound{" +
                "matches=" + matches +
                '}';
    }

}