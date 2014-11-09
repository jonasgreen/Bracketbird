package com.bracketbird.client.model.tournament;


import com.bracketbird.clientcore.appcontrol.SystemException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Result implements Serializable {
    private static final long serialVersionUID = -399777321034136811L;


    //indirectly gives the result by set score.
    private List<Integer> scoresHome;
    private List<Integer> scoresOut;

    private int setWonByHome;
    private int setWonByOut;

    public Result() {
    }

    private Result(List<Integer> scoresHome, List<Integer> scoresOut) {
        this.scoresHome = scoresHome;
        this.scoresOut = scoresOut;

        int i = 0;
        int hWon = 0;
        int oWon = 0;
        for (Integer sHome : scoresHome) {
            if (sHome > scoresOut.get(i)) {
                hWon++;
            }
            else if (sHome < scoresOut.get(i)) {
                oWon++;
            }
            else {
                if (scoresHome.size() > 1) {
                    throw new SystemException("Draw is not allowed in matches with more than one set");
                }
            }
            i++;
        }
        setWonByHome = hWon;
        setWonByOut = oWon;

    }

    public static Result newInstance(int[] homeScores, int[] outScores){
        if(homeScores == null){
            return null;
        }
        return newInstance(convert(homeScores), convert(outScores));
    }

    private static List<Integer> convert(int[] arr){
        List<Integer> list = new ArrayList<Integer>();
        for (int i : arr) {
            list.add(i);
        }
        return list;
    }

    public static Result newInstance(List<Integer> homeSetResults, List<Integer> outSetResults) {
        if (homeSetResults == null || outSetResults == null) {
            throw new SystemException("Cannot create a result with no set values");
        }
        if (homeSetResults.isEmpty() || outSetResults.isEmpty()) {
            throw new SystemException("Cannot create a result where personal sets and out sets are empty.");
        }
        if (homeSetResults.size() != homeSetResults.size()) {
            throw new SystemException("Cannot create a result where personal sets and out sets are of different size");
        }


        return new Result(homeSetResults, outSetResults);
    }


    public List<Integer> getScoresHome() {
        return scoresHome;
    }

    public List<Integer> getScoresOut() {
        return scoresOut;
    }


    public boolean homeIsWinning(){
        return setWonByHome > setWonByOut;
    }

    public boolean outIsWinning(){
        return setWonByHome < setWonByOut;
    }

    public int getSetWonByHome() {
        return setWonByHome;
    }

    public int getSetWonByOut() {
        return setWonByOut;
    }

    public boolean isDraw() {
        return scoresHome.size() == 1 && (scoresHome.get(0) == scoresOut.get(0).intValue());
    }

    public String asString() {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (Integer sHome : getScoresHome()) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(sHome).append("-").append(getScoresOut().get(i++));
        }
        return sb.toString();
    }

    public String asHtmlString(String winnerColor){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        List<Integer> scoresOut = getScoresOut();

        for (Integer sHome : getScoresHome()) {
            if (i != 0) {
                sb.append(", ");
            }
            int sOut = scoresOut.get(i++);
            if(sHome > sOut){
                addScoreWithColor(sb, sHome, winnerColor);
                sb.append("-").append(sOut);
            }
            else if(sOut > sHome){
                sb.append(sHome);
                sb.append("-");
                addScoreWithColor(sb, sOut, winnerColor);
            }
            else{
                sb.append(sHome).append("-").append(sOut);
            }
        }
        return sb.toString();
    }

    private void addScoreWithColor(StringBuilder sb, int score, String color){
        sb.append("<span style='color: ").append(color).append("'>").append(score).append("</span>");
    }



    public boolean equals(Object obj){
        if(obj instanceof Result){
            Result r = (Result) obj;
            if(r.getScoresHome().size() != getScoresHome().size()){
                return false;
            }

            int i = 0;
            //noinspection UnusedDeclaration
            for (Integer integer : scoresHome) {
                if((scoresHome.get(i).intValue() != r.getScoresHome().get(i)) || (scoresOut.get(i).intValue() != r.getScoresOut().get(i))){
                    return false;
                }
                i++;
            }

            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Result{" +
                "scoresHome=" + scoresHome +
                ", scoresOut=" + scoresOut +
                ", setWonByHome=" + setWonByHome +
                ", setWonByOut=" + setWonByOut +
                '}';
    }

    public void setScoresHome(List<Integer> scoresHome) {
        this.scoresHome = scoresHome;
    }

    public void setScoresOut(List<Integer> scoresOut) {
        this.scoresOut = scoresOut;
    }

    public void setSetWonByHome(int setWonByHome) {
        this.setWonByHome = setWonByHome;
    }

    public void setSetWonByOut(int setWonByOut) {
        this.setWonByOut = setWonByOut;
    }
}
