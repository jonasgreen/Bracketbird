package com.bracketbird.client.pages.matches;

import com.bracketbird.client.model.tournament.Result;
import com.bracketbird.client.util.KeyUtil;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.TextBox;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class SetEditor extends TextBox {

    public static final char SET_SEP = ' ';
    public static final char SCORE_SEP = '-';

    public SetEditor() {
        super();

        addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent event) {
                handleKeyUp(event);
            }
        });
    }

    private void handleKeyUp(KeyUpEvent event) {
        int key = event.getNativeKeyCode();
        if(KeyCodes.KEY_SPACE == key){
            formatText();
        }
    }

    public void formatText() {
        int cursorPos = getCursorPos();


        boolean useScoreDel = true;
        boolean addDel = false;

        StringBuilder sb = new StringBuilder();

        for (Integer number : getNumbers()) {
            if(addDel){
                sb.append(useScoreDel ? SCORE_SEP : SET_SEP);
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

    protected List<Integer> getNumbers() {
        List<Integer> list = new ArrayList<Integer>();
        for (String s : getText().split("[^\\d]+")) {
            try{
                list.add(new Integer(s));
            }
            catch (NumberFormatException e){
                //ignore
            }
        }
        return list;
    }

    public void load(Result r) {
        if (r == null) {
        	setText("");
            return;
        }
        List<Integer> scoresHome = r.getScoresHome();
        List<Integer> scoresOut = r.getScoresOut();

        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Integer home : scoresHome) {
            if(index > 0){
                sb.append(SET_SEP);
            }
            sb.append(home);
            sb.append(SCORE_SEP);
            sb.append(scoresOut.get(index));
            index++;
        }
        setText(sb.toString());
    }



    private boolean lastCharIsDigit(String s) {
        return s.length() > 0 && KeyUtil.isDigit(s.charAt(s.length()-1));
    }


}
