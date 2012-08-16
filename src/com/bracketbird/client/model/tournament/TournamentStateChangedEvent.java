package com.bracketbird.client.model.tournament;


/**
 *
 */
public class TournamentStateChangedEvent extends TournamentEvent {

    private TournamentState oldState;
    private TournamentState newState;

    public TournamentStateChangedEvent(TournamentState oldState, TournamentState newState, boolean fromClient) {
        super(fromClient);
        this.oldState = oldState;
        this.newState = newState;
      }

    public TournamentState getOldState() {
        return oldState;
    }

    public TournamentState getNewState() {
        return newState;
    }

    public void setOldState(TournamentState oldState) {
        this.oldState = oldState;
    }

    public void setNewState(TournamentState newState) {
        this.newState = newState;
    }

    public boolean hasStateChanged(){
        return oldState.equals(newState);
    }

}