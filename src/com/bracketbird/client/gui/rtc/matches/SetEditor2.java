package com.bracketbird.client.gui.rtc.matches;

import com.bracketbird.client.model.tournament.Result;
import com.bracketbird.clientcore.util.KeyUtil;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.TextBox;

import java.util.List;


/**
 *
 */
public class SetEditor2 extends TextBox {

    public static final char SET_SEP = ' ';
    public static final char SCORE_SEP = '-';

    public SetEditor2() {
        super();
        addKeyDownHandler(new KeyDownHandler() {
            public void onKeyDown(KeyDownEvent event) {
                handleKeyDown(event);
            }
        });

        addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent event) {
                handleKeyUp(event);
            }
        });

    }

    private void handleKeyUp(KeyUpEvent event) {
        int key = event.getNativeKeyCode();
        int cursorPos = getCursorPos();
        System.out.println("HandleKeyUp: "+ key);

        if(KeyCodes.KEY_SPACE != key){
            return;//always ok
        }

        //perform formatting
        String[] numbers = getText().split("[^\\d]+");
        boolean useScoreDel = true;
        boolean addDel = false;

        StringBuilder sb = new StringBuilder();

        for (String number : numbers) {
            if(addDel){
                sb.append(useScoreDel ? "-" : " ");
                useScoreDel = !useScoreDel;
            }
            sb.append(number);
            addDel = true;
        }

        if(!lastCharIsDigit(getText())){
            sb.append(useScoreDel ? "-" : " ");
        }

        setText(sb.toString());
        if(cursorPos < getText().length()){
            setCursorPos(cursorPos);
        }

    }

    public void load(Result r) {
        setText("");
        if (r == null) {
            return;
        }
        List<Integer> scoresHome = r.getScoresHome();
        List<Integer> scoresOut = r.getScoresOut();

        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Integer home : scoresHome) {
            if(index > 0){
                sb.append(SetSepItem.SEP_VALUE);
            }
            sb.append(home);
            sb.append(NumberSepItem.SEP_VALUE);
            sb.append(scoresOut.get(index));
            index++;
        }
        setText(sb.toString());
        setFocus(true);

    }


    private void handleKeyDown(KeyDownEvent event) {
    }

    private boolean lastCharIsDigit(String s) {
        return s.length() > 0 && KeyUtil.isDigit(s.charAt(s.length()-1));
    }

    private boolean cursorPositionAtEnd(int cursorPos) {
        return getText().length() == cursorPos;
    }

    private boolean isSeparator(int key) {
        return KeyUtil.isSpace(key) || KeyUtil.isSepLine(key)|| KeyUtil.isComma(key);
    }

    public Result getResult() {
        return null;
    }
}
