package com.bracketbird.clientcore.model;

/**
 *
 */
public enum RTCEventType {
    //0
    createTournament(0),

    //1-10 : team
    addTeam(1),
    updateTeam(2),
    deleteTeam(3),

    //11-20 : level
    addLevel(11),
    updateLevel(12),
    deleteLevel(13),
    finishedLevel(14),

    //21-20
    layoutMatches(21),
    updateMatch(22),    

    //100-199
    tournamentNameChange(100),



    //1000-? - state changes
    tournamentStateChange(1000);


    private Integer value;
    RTCEventType(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }
}
