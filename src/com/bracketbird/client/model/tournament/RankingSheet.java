package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.Team;
import com.bracketbird.client.ranking.TeamStatistics;

import java.util.*;
import java.util.Set;

/**
 *
 */
public class RankingSheet {

    private Map<Team, TeamStatistics> teamResultMap = new HashMap<Team, TeamStatistics>();
    private StageSettings settings;
    private List<Position> positions = new ArrayList<Position>();


    //creates positioning only for the given teams - as long as the match does not contain any illigal teams
    public RankingSheet(List<Match> matches, List<Team> teams, List<Team> illegalTeams, StageSettings settings){
        initTeamResultMap(teams);
        this.settings = settings;
        for (Match m : matches) {
            if(!isIllegal(m, illegalTeams)){
                for (Team team : teams) {
                    if(m.getTeamHome().equals(team)){
                        updateRanking(m, team, true);
                    }
                    else if(m.getTeamOut().equals(team)){
                        updateRanking(m, team, false);
                    }
                }
            }
        }
        //updateField list
        findPosistions();
    }

    //creates all the teams in the given matches
    public RankingSheet(List<Match> matches, StageSettings settings) {
    	java.util.Set<Team> teams = new HashSet<Team>();
        for (Match match : matches) {
            addTeam(teams, match.getTeamHome());
            addTeam(teams, match.getTeamOut());
        }
    	initTeamResultMap(new ArrayList<Team>(teams));
         
    	this.settings = settings;
        for (Match m : matches) {
            updateRanking(m, m.getTeamHome(), true);
            updateRanking(m, m.getTeamOut(), false);
        }

        //updateField list
        findPosistions();
    }

    private void addTeam(Set<Team> teams, Team team) {
        if(team.isEmpty() || team.isSeedingTeam() || team.isWalkover()){
            return;
        }
        teams.add(team);
    }


    private boolean isIllegal(Match m, List<Team> illegalTeams) {
        for (Team it : illegalTeams) {
            if(it.equals(m.getTeamHome()) || it.equals(m.getTeamOut())){
                return true;
            }
        }
        return false;
    }

    private void findPosistions() {
        List<TeamStatistics> teamStatistics = new ArrayList<TeamStatistics>();
        for (TeamStatistics resultSum : teamResultMap.values()) {
            teamStatistics.add(resultSum);
        }


        PositionCalculater calculater = new PointPositionCalculater();
        List<Integer> rankRules = settings.getRankingRules();
        for (Integer r : rankRules) {
            calculater.addNext(PositionCalculater.create(r));
        }

        positions = calculater.calculate(teamStatistics);
    }

    private void initTeamResultMap(List<Team> teams) {
        for (Team team : teams) {
            teamResultMap.put(team, new TeamStatistics(team));
        }
    }


    public List<Position> getPositions() {
        return positions;
    }


    private void updateRanking(Match m, Team t, boolean isTeamHome) {
        if(!m.isFinish()){
            return;
        }
        TeamStatistics p = getTeamResultSum(t);
        Result r = m.getResult();
        /*p.setPlayedMatches(p.getPlayedMatches()+1);

        if (r.homeIsWinning()) {
            if (isTeamHome) {
                p.setWonMatches(p.getWonMatches()+1);
                p.setPoints(p.getPoints() + settings.getPointsOfVictory());
            }
            else {
                p.setLostMatches(p.getLostMatches() + 1);
            }
        }
        else if (r.outIsWinning()) {
            if (isTeamHome) {
                p.setLostMatches(p.getLostMatches() + 1);
            }
            else {
                p.setWonMatches(p.getWonMatches()+1);
                p.setPoints(p.getPoints() + settings.getPointsOfVictory());
            }
        }
        else {
            p.setDrawMatches(p.getDrawMatches() + 1);
            p.setPoints(p.getPoints()+ settings.getPointsOfDraw());
        }
        for (Integer g : r.getScoresHome()) {
            if (isTeamHome) {
                p.setScoredGoals(p.getScoredGoals()+ g);
            }
            else {
                p.setReceivedGoals(p.getReceivedGoals() + g);
            }

        }
        for (Integer g : r.getScoresOut()) {
            if (isTeamHome) {
                p.setReceivedGoals(p.getReceivedGoals()+ g);
            }
            else {
                p.setScoredGoals(p.getScoredGoals() + g);
            }
        }
*/

    }


    private TeamStatistics getTeamResultSum(Team t) {
        TeamStatistics p = teamResultMap.get(t);
        if (p == null) {
            p = new TeamStatistics(t);
            teamResultMap.put(t, p);
        }

        return p;
    }


}
