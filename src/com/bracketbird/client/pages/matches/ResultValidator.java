package com.bracketbird.client.pages.matches;

import com.bracketbird.client.model.tournament.Result;

import java.util.ArrayList;
import java.util.List;

public class ResultValidator {

    private boolean isValid = false;
    private Result result;

    public static ResultValidator create(List<Integer> numbers, boolean drawAllowed) {
        List<Integer> home = new ArrayList<Integer>();
        List<Integer> out = new ArrayList<Integer>();

        boolean isHome = true;
        for (Integer n : numbers) {
            if (isHome) {
                home.add(n);
            }
            else {
                out.add(n);
            }
            isHome = !isHome;
        }
        return create(home, out, drawAllowed);
    }

    public static ResultValidator create(List<Integer> scoresHome, List<Integer> scoresOut, boolean drawAllowed) {
        return new ResultValidator(scoresHome, scoresOut, drawAllowed);
    }

    public ResultValidator(List<Integer> scoresHome, List<Integer> scoresOut, boolean drawAllowed) {
        if (!validatePreConditions(scoresHome, scoresOut)) {
            return;
        }
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
                if (!drawAllowed || scoresHome.size() > 1) {
                    return; // a result can not have more than one set and a draw set.
                }
            }
            i++;
        }
        isValid = scoresHome.size() == 1 || hWon != oWon;
        if(isValid){
            this.result = Result.newInstance(scoresHome, scoresOut);
        }
    }

    public boolean isValid() {
        return isValid;
    }

    private boolean validatePreConditions(List<Integer> scoresHome, List<Integer> scoresOut) {
        if (scoresHome == null || scoresOut == null) {
            return false;
        }
        if (scoresHome.isEmpty() || scoresOut.isEmpty()) {
            return false;
        }
        if (scoresHome.size() != scoresOut.size()) {
            return false;
        }
        return true;
    }

    public Result getResult() {
        return result;
    }

}
