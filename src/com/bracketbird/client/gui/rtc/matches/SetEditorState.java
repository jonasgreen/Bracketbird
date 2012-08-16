package com.bracketbird.client.gui.rtc.matches;

import java.util.*;

/**
 *
 */
public class SetEditorState {

    private static String setSep = " ";
    private static String scoreSep = "-";

    private int legalStateIndex = 0;

    public enum State {
        unknown,
        homeIsWinning,
        outIsWinning,
        draw,
        reset,
        illegal
    }

    private String text;
    private List<SetItem> items;

    public SetEditorState(String text, List<SetItem> items) {
        this.text = text.trim();
        this.items = items;
    }

    public State getState() {
        if(text == null || text.length() == 0){
            return State.reset;
        }
        if (!validSize()) {
            return State.unknown;
        }
        if (invalidItemOrder()) {
            return SetEditorState.State.illegal;
        }
        if (!validLastItem()) {
            return State.unknown;
        }


        return calculateWinner();
    }

    private boolean invalidItemOrder() {
        SetItem last = null;

        for (SetItem item : items) {
            if (last == null) {
                last = item;
            }
            else {
                if (last.isNextItemValid(item)) {
                    last = item;
                }
                else {
                    return true;
                }
            }
        }
        return false;

    }


    private State calculateWinner() {
        int index = 0;
        int homeWins = 0;
        int outWins = 0;
        while (index < text.length()) {

            String homeResult = text.substring(index, getIndexOf(index, scoreSep));
            index = index + homeResult.length() + 1;

            String outResult = text.substring(index, getIndexOf(index, setSep));
            int h = 0;
            int o = 0;
            try {
                h = Integer.valueOf(homeResult);
                o = Integer.valueOf(outResult);
            }
            catch (NumberFormatException e) {
                return SetEditorState.State.unknown;
            }
            if (h > o) {
                homeWins++;
            }
            else if (h < o) {
                outWins++;
            }
            index = index + outResult.length() + 1;
        }

        if (outWins > homeWins) {
            return State.outIsWinning;
        }
        if (outWins < homeWins) {
            return State.homeIsWinning;

        }
        return State.draw;
    }

    private boolean validLastItem() {
        SetItem lastItem = items.get(items.size() - 1);
        boolean outResult = lastItem instanceof NumberItem && !((NumberItem) lastItem).isHome();
        return outResult || lastItem instanceof SetSepItem;
    }

    private boolean validSize() {
        return items.size() > 3;
    }

    private int getIndexOf(int index, String s) {
        int tempEndIndex = text.indexOf(s, index);
        tempEndIndex = tempEndIndex == -1 ? text.length() : tempEndIndex;
        return tempEndIndex;
    }


}
