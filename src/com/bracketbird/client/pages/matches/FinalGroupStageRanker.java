package com.bracketbird.client.pages.matches;

import com.bracketbird.client.model.Team;
import com.bracketbird.client.model.tournament.*;

import java.util.ArrayList;
import java.util.List;

public class FinalGroupStageRanker {

    //TODO - THIS IS CRAPPY - rewrite

    private List<Position> positions;

    public FinalGroupStageRanker(GroupStage stage) {
        this.positions = getFinalPositions(stage);
    }

    public boolean allTeamsHasUniquePositions(){
        for (Position position : positions) {
            if(position.hasMoreTeams()){
                return true;
            }
        }
        return false;
    }

    private List<Position> getFinalPositions(GroupStage stage) {
        List<Match> matches = stage.getMatches();
        List<Position> positions = new ArrayList<Position>();

        //homogenic = all groups have same number of teams.
        //those teams that make it inhomogenic
        List<Team> restTeams = new ArrayList<Team>();

        int minTeams = Integer.MAX_VALUE;
        int maxTeams = 0;

        for (Group aGroup : stage.getGroups()) {
            int teamsCount = aGroup.getTeams().size();
            if (teamsCount < minTeams) {
                minTeams = teamsCount;
            }
            if (teamsCount > maxTeams) {
                maxTeams = teamsCount;
            }
        }
        boolean isHomogen = (minTeams == maxTeams);

        //if not homogen - then collect the restteams
        if (!isHomogen) {
            for (Group g : stage.getGroups()) {
                if (g.getEndingTeams().size() == maxTeams) {
                    restTeams.add(g.getEndingTeams().get(maxTeams - 1));//gets the last one
                }
            }
        }


        int count = 0;
        while (count < minTeams) {
            List<Team> verticalList = new ArrayList<Team>();

            //each number one and each number two etc. is collected in verticalList.
            for (Group g: stage.getGroups()) {
                verticalList.add(g.getEndingTeams().get(count));
            }
            RankingSheet rs = new RankingSheet(matches, verticalList, restTeams, stage.getSettings());
            positions.addAll(rs.getPositions());
            count++;
        }

        //adding the rest teams
        if (!isHomogen) {
            RankingSheet rs = new RankingSheet(matches, restTeams, new ArrayList<Team>(), stage.getSettings());
            positions.addAll(rs.getPositions());
        }

        return positions;

    }


    public List<Position> getPositions() {
        return positions;
    }
}
