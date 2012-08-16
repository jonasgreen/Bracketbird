package com.bracketbird.clientcore.util;


import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;

import java.util.*;


/**
 *
 */
public abstract class DefaultFocusManager implements FocusManager {


    private List<FocusComponentIntfc> focusables = new ArrayList<FocusComponentIntfc>();
    private FocusManager parent;
    private FocusComponentIntfc lastFocus;

    public DefaultFocusManager() {
    }




    public void addChild(FocusComponentIntfc fm) {
        fm.addParent(this);
        focusables.add(fm);

    }

    public void addParent(FocusManager fc) {
        parent = fc;
    }

    public void doFocus(FocusKeyDownEvent e, int i) {
        if (focusables.size() < i) {
            getLastItem().doFocus(e);
        }
        else {
            getItem(i).doFocus(e);
        }
    }

    public void remove(FocusComponentIntfc f) {
        focusables.remove(f);
        if(lastFocus == f){
            lastFocus = null;
        }
    }

    public void removeAll(){
        focusables = new ArrayList<FocusComponentIntfc>();
        lastFocus = null;
    }

    public void replace(FocusComponentIntfc replace, FocusComponentIntfc with){
        int index = getIndex(replace);
        focusables.remove(index);
        focusables.add(index, with);
        with.addParent(this);
    }

    public abstract void up(FocusKeyDownEvent e);

    public abstract void down(FocusKeyDownEvent e);

    public abstract void right(FocusKeyDownEvent e);

    public abstract void left(FocusKeyDownEvent e);

    public abstract void up(FocusKeyDownEvent e, int index, FocusManager fm);

    public abstract void down(FocusKeyDownEvent e, int index, FocusManager fm);

    public abstract void right(FocusKeyDownEvent e, int index, FocusManager fm);

    public abstract void left(FocusKeyDownEvent e, int index, FocusManager fm);

    public abstract void next(FocusKeyDownEvent e);

    public void onKeyDown(final FocusKeyDownEvent e) {
        if (e.getSource().isLeavable(e.getKeyCode())) {
            if (KeyUtil.isDownArrow(e.getKeyCode())) {
                down(e);
            }
            else if (KeyUtil.isLeftArrow(e.getKeyCode())) {
                left(e);
            }
            else if (KeyUtil.isRightArrow(e.getKeyCode())) {
                right(e);
            }
            else if (KeyUtil.isUpArrow(e.getKeyCode())) {
                up(e);
            }
        }
        /*else if (KeyUtil.isEnter(e.getKeyCode()) && (e.getSource() instanceof TextBoxComponent || e.getSource() instanceof LabelTextBoxComponent)) {
            next(e);
        }*/
        else if(KeyUtil.isTab(e.getKeyCode())){
            Timer t = new Timer() {
                @Override
                public void run() {
                    next(e);
                }
            };
            t.schedule(10);
        }
    }

    public void setFocus(){
        if(lastFocus != null){
            lastFocus.doFocus(null);
        }
        else if(!getFocusables().isEmpty()){
            getFocusables().get(0).doFocus(null);
        }
    }

    public int getIndex(FocusComponentIntfc fc) {
        int i = 0;
        for (FocusComponentIntfc focusable : focusables) {
            if (fc == focusable) {
                return i;
            }
            i++;
        }
        throw new SystemException("Unable to find FocusManager index for component:" + fc.toString());
    }


    public void doFocus(FocusKeyDownEvent e) {
        if (!focusables.isEmpty()) {
            focusables.get(0).doFocus(e);
        }
    }

    public boolean isLeavable(int keyCode) {
        return false;
    }


    public boolean isLeavable(SuggestBox sb, int keyCode) {
        if (sb.isSuggestionListShowing()) {
            return (KeyUtil.isLeftArrow(keyCode) || KeyUtil.isRightArrow(keyCode));
        }
        else {
            return isLeavable(sb.getTextBox(), keyCode);

        }
    }

    public boolean isLeavable(TextArea tb, int keyCode) {

        //noinspection SimplifiableIfStatement
        if ((KeyUtil.isLeftArrow(keyCode) || KeyUtil.isUpArrow(keyCode)) && tb.getCursorPos() == 0) {
            return true;
        }
        return tb.getCursorPos() == tb.getText().length() && (KeyUtil.isRightArrow(keyCode) || KeyUtil.isDownArrow(keyCode));

    }

    public boolean isLeavable(TextBoxBase tbb, int keyCode) {
        if (KeyUtil.isUpArrow(keyCode) || KeyUtil.isDownArrow(keyCode)) {
            return true;
        }
        if (tbb.getCursorPos() == 0 && KeyUtil.isLeftArrow(keyCode)) {
            return true;
        }
        //noinspection RedundantIfStatement
        if (tbb.getCursorPos() == tbb.getText().length() && KeyUtil.isRightArrow(keyCode)) {
            return true;
        }
        return false;
    }

    public FocusManager getParent() {
        return parent;
    }

    public List<FocusComponentIntfc> getFocusables() {
        return focusables;
    }


    protected void focusItem(FocusKeyDownEvent e, int index) {
        getFocusables().get(index).doFocus(e);
    }

    protected void focusFirstItem(FocusKeyDownEvent e) {
        focusItem(e, 0);
    }

    protected void focusLastItem(FocusKeyDownEvent e) {
        focusItem(e, getFocusables().size() - 1);
    }


    public boolean isLastItem(int index) {
        return index == getFocusables().size() - 1;
    }

    public boolean isFirstItem(int index) {
        return index == 0;
    }

    protected FocusComponentIntfc getLastItem() {
        return getFocusables().get(getFocusables().size() - 1);
    }

    protected FocusComponentIntfc getItem(int i) {
        return getFocusables().get(i);
    }

    protected FocusComponentIntfc getBeforeLast() {
        return getFocusables().get(getFocusables().size() - 2);
    }

    public void setLastFocus(FocusComponentIntfc lf) {
        if (getParent() != null) {
            getParent().setLastFocus(lf);
        }
        else {
            if (lastFocus != null && lastFocus != lf) {
                lastFocus.focusLost();
            }
            lastFocus = lf;
        }
    }

    public void setCursorPosition(FocusComponentIntfc fc, final TextBoxBase tbb, final FocusKeyDownEvent e) {
        int cursorPos = -1;
        int fcIndex = getIndex(fc);

        if (KeyUtil.isLeftArrow(e.getKeyCode())) {
                cursorPos = tbb.getText().length();
        }
        else if (KeyUtil.isRightArrow(e.getKeyCode())) {
                cursorPos = 0;
            }

        if (cursorPos != -1) {
            final int cp = cursorPos;
            Timer t = new Timer() {
                @Override
                public void run() {
                    tbb.setCursorPos(cp);
                }
            };
            t.schedule(10);
        }
    }

}
