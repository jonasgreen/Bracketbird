package com.bracketbird.client.model.tournament;

import com.bracketbird.client.gui.rtc.event.UpdateMatchFieldEvent;
import com.bracketbird.client.gui.rtc.event.UpdateMatchResultEvent;
import com.bracketbird.client.model.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.model.*;

import java.util.*;

/**
 *
 */
public class Match extends Model<MatchId> {
    private static final long serialVersionUID = -8624209794497350221L;

    private transient List<TournamentListener<MatchEvent>> matchListener = new ArrayList<TournamentListener<MatchEvent>>();

    private transient MatchState state = new MatchNotReady();


    private TournamentLevel level;

    //used in group-play
    private String groupName;

    //what round does this match belong to
    private Long round;

    //the order of the match in a subtournament
    private Integer order;

    //used in knockout - to reference between mathces (who meets who).
    private String name;

    private Team teamHome;

    private Team teamOut;


    private Date createdDate;

    private Date lastChangeDate;

    private Result result;

    private String field;


    public Match() {
    }

    public Team getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(Team teamHome) {
        this.teamHome = teamHome;
    }

    public Team getTeamOut() {
        return teamOut;
    }

    public void setTeamOut(Team teamOut) {
        this.teamOut = teamOut;

    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getRound() {
        return round;
    }

    public void setRound(Long round) {
        this.round = round;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public void addMatchChangedListener(TournamentListener<MatchEvent> l) {
        this.matchListener.add(l);
    }

    private void fireMatchChangedEvent(boolean fromClient) {
        MatchEvent event = new MatchEvent(fromClient, this);
        for (TournamentListener<MatchEvent> l : matchListener) {
            l.onChange(event);
        }
    }


    public String getField() {
        return field;
    }

    public void setField(String f) {
        this.field = f;
    }


    public boolean hasResult() {
        return result != null;
    }

    public Team getWinningTeam() {
        if (!isFinish()) {
            return null;
        }
        if (isWalkover()) {
            return getTeamHome() instanceof WalkOverTeam ? getTeamOut() : getTeamHome();
        }
        return homeIsWinning() ? getTeamHome() : getTeamOut();
    }

    public Team getLosingTeam() {
        if (!isFinish()) {
            return null;
        }
        if (isWalkover()) {
            return getTeamHome() instanceof WalkOverTeam ? getTeamHome() : getTeamOut();
        }
        return homeIsWinning() ? getTeamOut() : getTeamHome();
    }


    public boolean homeIsWinning() {
        return hasResult() && result.homeIsWinning();
    }

    public boolean outIsWinning() {
        return hasResult() && result.outIsWinning();
    }

    public String resultAsString() {
        return !hasResult() ? "" : result.asString();
    }


    public Result getResult() {
        return result;
    }

    public boolean isFinish() {
        return result != null || isWalkover();
    }

    public TournamentLevel getLevel() {
        return level;
    }

    public void setLevel(TournamentLevel level) {
        this.level = level;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isWalkover() {
        return getTeamHome() instanceof WalkOverTeam || getTeamOut() instanceof WalkOverTeam;
    }


    protected void update(UpdateMatchResultEvent event) {
        this.result = Result.newInstance(event.getHomeScores(), event.getOutScores());
        this.state = calculateState();
        fireMatchChangedEvent(event.isFromClient());
    }

    void update(UpdateMatchFieldEvent event) {
        this.field = event.getField();
        this.state = calculateState();
        fireMatchChangedEvent(event.isFromClient());
    }

    public void updateOutTeam(Team out, boolean fromClient) {
        this.teamOut = out == null ? new SeedingTeam("") : out;
        this.state = calculateState();
        fireMatchChangedEvent(fromClient);
    }

    public void updateHomeTeam(Team home, boolean fromClient) {
        this.teamHome = home == null ? new SeedingTeam("") : home;
        this.state = calculateState();
        fireMatchChangedEvent(fromClient);
    }


    private MatchState calculateState(){
        if(teamHome.isSeedingTeam() || teamOut.isSeedingTeam()){
            return new MatchNotReady();
        }
        if(teamHome.isWalkover() || teamOut.isWalkover()){
             return new MatchFinished();
        }
        if(result != null){
            return new MatchFinished();
        }
        return (field == null || field.isEmpty()) ? new MatchReady() : new MatchReady();
    }


    @Override
    public String toString() {
        return "Match{" + teamHome.getName() + " - " + teamOut.getName() + '}';
    }



}