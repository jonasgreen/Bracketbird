package com.bracketbird.clientcore.util;

import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class VerticalFocusManager extends DefaultFocusManager {


    public VerticalFocusManager() {
    }

    public void next(FocusKeyDownEvent e){
        down(e);
    }



    public void right(FocusKeyDownEvent e,int index, FocusManager child) {
        if (getParent() != null) {
            getParent().right(e, 0, this);
        }
    }

    public void right(FocusKeyDownEvent e) {
        if (getParent() != null) {
            getParent().right(e, getIndex(e.getSource()), this);
        }
    }

    public void left(FocusKeyDownEvent e,int index, FocusManager child) {
        if (getParent() != null) {
            getParent().left(e,0, this);
        }
    }

    public void left(FocusKeyDownEvent e) {
        if (getParent() != null) {
            getParent().left(e,getIndex(e.getSource()), this);
        }
    }


    public void up(FocusKeyDownEvent e) {
        int index = getIndex(e.getSource());
        if (isFirstItem(index)) {
            if (getParent() != null) {
                //you are going into a Horizontal parent from vertical, thats why index = 0;
                getParent().up(e, 0, this);
            }
            else {
                //ignore - no flip over
            }
        }
        else {
            getItem(index - 1).doFocus(e);
        }
    }

    //coming from vertical manager
    public void up(FocusKeyDownEvent e,int index, FocusManager child) {
        int fmIndex = getIndex(child);

        if (isFirstItem(fmIndex)) {
            if (getParent() != null) {
                getParent().up(e,index, this);
            }
            else {
                //ignore - no flip over
            }
        }
        else {
            FocusComponentIntfc fc = getItem(fmIndex - 1);
            if (fc instanceof FocusManager) {
                //noinspection CastConflictsWithInstanceof
                ((HorizontalFocusManager) fc).doFocus(e,index);
            }
            else {
                fc.doFocus(e);
            }
        }
    }


    //comming from it self
    public void down(FocusKeyDownEvent e) {
        int index = getIndex(e.getSource());
        if (isLastItem(index)) {
            if (getParent() != null) {
                //you are going into a horizontal parent from vertical, thats why index = 0;
                getParent().down(e, 0, this);
            }
            else {
                //ignore - no flip
            }
        }
        else {
            getItem(index + 1).doFocus(e);
        }
    }


    //coming from horizontal manager
    public void down(FocusKeyDownEvent e, int index, FocusManager child) {
        int fmIndex = getIndex(child);

        if (isLastItem(fmIndex)) {
            if (getParent() != null) {
                getParent().down(e, index, this);
            }
            else {
                //ignore - no flip over
            }
        }
        else {
            FocusComponentIntfc fc = getItem(fmIndex + 1);
            if (fc instanceof FocusManager) {
                //noinspection CastConflictsWithInstanceof
                ((HorizontalFocusManager) fc).doFocus(e,index);
            }
            else {
                fc.doFocus(e);
            }
        }
    }


    public void focusLost() {
    }
}