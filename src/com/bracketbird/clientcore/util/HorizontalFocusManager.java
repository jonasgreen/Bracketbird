package com.bracketbird.clientcore.util;

import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class HorizontalFocusManager extends DefaultFocusManager {


    public HorizontalFocusManager() {
        super();
    }


    public void up(FocusKeyDownEvent e) {
        if (getParent() != null) {
            getParent().up(e, getIndex(e.getSource()), this);
        }
    }

    public void up(FocusKeyDownEvent e, int index, FocusManager child) {
        if (getParent() != null) {
            getParent().up(e, 0, this);
        }
    }

    public void down(FocusKeyDownEvent e) {
        if (getParent() != null) {
            getParent().down(e, getIndex(e.getSource()), this);
        }
    }

    public void down(FocusKeyDownEvent e, int index, FocusManager child) {
        if (getParent() != null) {
            getParent().down(e, 0, this);
        }
    }


    public void left(FocusKeyDownEvent e) {
        int index = getIndex(e.getSource());
        if (isFirstItem(index)) {
            if (getParent() != null) {
                //you are going into a vertical parent from horizontal, thats why index = 0;
                getParent().left(e, 0, this);
            }
            else {
                //ignore
            }
        }
        else {
            getItem(index - 1).doFocus(e);
        }
    }

    public void next(FocusKeyDownEvent e){
        right(e);
    }

    //coming from vertical manager
    public void left(FocusKeyDownEvent e, int index, FocusManager child) {
        int fmIndex = getIndex(child);

        if (isFirstItem(fmIndex)) {
            if (getParent() != null) {
                getParent().left(e, index, this);
            }
            else {
                //ignore
            }
        }
        else {
            FocusComponentIntfc fc = getItem(fmIndex - 1);
            if (fc instanceof FocusManager) {
                //noinspection CastConflictsWithInstanceof
                ((VerticalFocusManager) fc).doFocus(e,index);
            }
            else {
                fc.doFocus(e);
            }
        }
    }


    //comming from it self
    public void right(FocusKeyDownEvent e) {
        int index = getIndex(e.getSource());
        if (isLastItem(index)) {
            if (getParent() != null) {
                //you are going into a vertical parent from horizontal, thats why index = 0;
                getParent().right(e, 0, this);
            }
            else {
                //ignore
            }
        }
        else {
            getItem(index + 1).doFocus(e);
        }
    }


    //coming from vertical manager
    public void right(FocusKeyDownEvent e, int index, FocusManager child) {
        int fmIndex = getIndex(child);

        if (isLastItem(fmIndex)) {
            if (getParent() != null) {
                getParent().right(e, index, this);
            }
            else {
                //ignore
            }
        }
        else {
            FocusComponentIntfc fc = getItem(fmIndex + 1);
            if (fc instanceof FocusManager) {
                //noinspection CastConflictsWithInstanceof
                ((VerticalFocusManager) fc).doFocus(e,index);
            }
            else {
                fc.doFocus(e);
            }
        }
    }


    public void focusLost() {

    }
}