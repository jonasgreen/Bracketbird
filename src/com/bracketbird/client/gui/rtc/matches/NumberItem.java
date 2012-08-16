package com.bracketbird.client.gui.rtc.matches;

import java.util.*;

/**
 *
 */
public class NumberItem extends SetItem{

    private boolean isHome;

    public NumberItem(boolean home) {
        isHome = home;
    }

    @Override
    public SetItem next(int key, List<SetItem> items, int index) {
         if(isDigit(key)){
             return new NumberItem(isHome);
         }
         if(!isLastItem(items)){
            return null;
         }
         return isHome ? new NumberSepItem() : new SetSepItem();
    }

    public boolean isHome() {
        return isHome;
    }

    public void setHome(boolean home) {
        isHome = home;
    }

    @Override
    public String getStringForReplace() {
        return null;
    }

    @Override
    public boolean isNextItemValid(SetItem next) {
        if(isHome){
            return next instanceof NumberItem || next instanceof NumberSepItem;
        }
        return next instanceof NumberItem || next instanceof SetSepItem;

    }



    @Override
    public String toString() {
        return isHome ? "HomeItem" : "OutItem";
    }
}