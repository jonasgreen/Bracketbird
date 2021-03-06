package com.bracketbird.client.model.tournament;

import com.bracketbird.client.EqualsUtil;
import com.bracketbird.client.model.*;
import com.bracketbird.client.model.event.UpdateDispatcher;
import com.bracketbird.client.model.event.UpdateEvent;
import com.bracketbird.client.model.event.UpdateHandler;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.util.StringUtil;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 *
 */
public class Match extends LevelStateModel<MatchId> {
    private static final long serialVersionUID = -8624209794497350221L;

    public UpdateDispatcher<Match> matchHandlers = new UpdateDispatcher<>();
    public UpdateDispatcher<Result> resultDispatcher = new UpdateDispatcher<>();


    //the order of the match in a subtournament
    private int matchNo;

    //used in knockout - to reference between mathces (who meets who).
    private String name;

    private Team teamHome;

    private Team teamOut;


    private Result result;

    private String field;

    private Integer countId;

    private Round round;

    public Match(Round round, int matchNo) {
        super();
        this.round = round;
        this.matchNo = matchNo;
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


    private void fireMatchChangedEvent(boolean fromClient) {
        matchHandlers.fireEvent(this, this, fromClient);
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
        if (EqualsUtil.equals(newResult, result)) {
            return;
        }
        Result oldResult = this.result;
        this.result = newResult;

        resultDispatcher.fireEvent(oldResult, result, fromClient);
        fireMatchChangedEvent(fromClient);
        updateState(fromClient);
    }

    public void updateField(String field, boolean isFromClient) {
        if (StringUtil.equals(this.field, field)) {
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

    public void initState() {
        this.state = calculateState();
    }


    public LevelState calculateState() {
        if (teamHome.isSeedingTeam() || teamOut.isSeedingTeam()) {
            return LevelState.notReady;
        }
        if (teamHome.isWalkover() || teamOut.isWalkover() || result != null) {
            return LevelState.finished;
        }
        return (field == null || field.isEmpty()) ? LevelState.ready : LevelState.inProgress;
    }


    @Override
    public void onUpdate(UpdateEvent<LevelState> event) {
        //Ignore - never called
    }

    public Round getRound() {
        return round;
    }

    @Override
    public String toString() {
        return "Match ["+getState()+"]{" + teamHome + " - " + teamOut +  ", "+result+"}";
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

    public boolean isTeamWinning(Team team) {
        if(result == null || result.isDraw()){
            return false;
        }
        if(team.equals(getTeamHome()) && result.homeIsWinning()){
            return true;
        }
        if(team.equals(getTeamOut()) && result.outIsWinning()){
            return true;
        }
        return false;
    }

    public boolean isHomeTeam(Team team) {
        return team.equals(teamHome);
    }

    public HandlerRegistration addResultHandler(UpdateHandler<Result> handler){
        return resultDispatcher.addHandler(handler);
    }
}
