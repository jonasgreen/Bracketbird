package com.bracketbird.client.model.tournament;

/**
 *
 */
public class Set {
    private static final long serialVersionUID = 4141737862259833506L;


    private Integer state;
    private Integer scoreHome;
    private Integer scoreOut;

    private boolean drawAllowed;

    public Set(boolean drawAllowed) {
        this.drawAllowed = drawAllowed;
    }

    public Integer getScoreHome() {
        return scoreHome;
    }

    public void setScoreHome(Integer scoreHome) {
        this.scoreHome = scoreHome;
        calculateState();
    }

    public Integer getScoreOut() {
        return scoreOut;
    }

    public void setScoreOut(Integer scoreOut) {
        this.scoreOut = scoreOut;
        calculateState();
    }

    private void calculateState() {

    }

    public boolean isEmpty() {
        return scoreHome == null && scoreOut == null;
    }

    

}