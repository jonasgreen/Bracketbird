package com.bracketbird.client.model.tournament;

import com.bracketbird.client.EqualsUtil;
import com.bracketbird.client.gui.rtc.event.*;
import com.bracketbird.client.model.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.util.StringUtil;

/**
 *
 */
public abstract class Match extends PlayableModel<MatchId> implements HasLevelState{
    private static final long serialVersionUID = -8624209794497350221L;

    public transient ModelHandlerList<Match> matchEventHandlers;


    private Round round;

    //the order of the match in a subtournament
    private int matchNo;

    //used in knockout - to reference between mathces (who meets who).
    private String name;

    private Team teamHome;

    private Team teamOut;


    private Result result;

    private String field;

    private Integer countId;


    public Match(Round round, int matchNo) {
        this.round = round;
        this.matchNo = matchNo;

        matchEventHandlers = new ModelHandlerList<Match>("Match "+matchNo + " (matchHandler)");
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

    @Override
    public Round getParent() {
        return round;
    }


    private void fireMatchChangedEvent(boolean fromClient) {
        matchEventHandlers.fireEvent(new UpdateModelEvent<Match>(fromClient, this, this));
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


    public String getName() {
        return name;
    }

    public boolean isWalkover() {
        return getTeamHome() instanceof WalkOverTeam || getTeamOut() instanceof WalkOverTeam;
    }

    public void updateResult(int[] homeScores, int[] outScores, boolean fromClient) {
        Result newResult = Result.newInstance(homeScores, outScores);
        if(EqualsUtil.equals(newResult, result)){
            return;
        }
        this.result = newResult;
        fireMatchChangedEvent(fromClient);
        updateState(fromClient);
    }

    public void updateField(String field, boolean isFromClient) {
        if(StringUtil.equals(this.field, field)){
            return;
        }
        this.field = field;
        fireMatchChangedEvent(isFromClient);
        updateState(isFromClient);
    }

    public int getMatchNo() {
        return matchNo;
    }

    public void setMatchNo(int matchNo) {
        this.matchNo = matchNo;
    }

    public void updateOutTeam(Team out, boolean fromClient) {
        this.teamOut = out == null ? new SeedingTeam() : out;
        fireMatchChangedEvent(fromClient);
        updateState(fromClient);
    }

    public void updateHomeTeam(Team home, boolean fromClient) {
        this.teamHome = home == null ? new SeedingTeam() : home;
        fireMatchChangedEvent(fromClient);
        updateState(fromClient);
    }

    public void initState(){
        this.state = calculateState();
    }

    public LevelState calculateState(){
        if(teamHome.isSeedingTeam() || teamOut.isSeedingTeam()){
            return LevelState.notReady;
        }
        if(teamHome.isWalkover() || teamOut.isWalkover() || result != null){
             return LevelState.finished;
        }
        return (field == null || field.isEmpty()) ? LevelState.ready : LevelState.inProgress;
    }


    @Override
    public String toString() {
        return "Match{" + teamHome.getName() + " - " + teamOut.getName() + '}';
    }


    public Integer getCountId() {
        return countId;
    }

    public void setCountId(Integer countId) {
        this.countId = countId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Round getRound() {
        return round;
    }

    @Override
    protected LevelState stateChanged(LevelState old, LevelState newState) {
        return newState;
    }
}