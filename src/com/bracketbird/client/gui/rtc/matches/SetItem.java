package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.clientcore.util.*;

import java.util.*;

/**
 *
 */
public abstract class SetItem {

    private static int counter = 0;
    protected int id;

    protected SetItem() {
        this.id = counter++;
    }

    public abstract SetItem next(int key, List<SetItem> items, int index);
    public abstract String getStringForReplace();
    public abstract boolean isNextItemValid(SetItem next);

    public boolean isDigit(int keycode){
        return KeyUtil.isDigit(keycode);
    }

    protected boolean isLastItem(List<SetItem> items) {
        return items.indexOf(this) == items.size()-1;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SetItem setItem = (SetItem) o;

        if (id != setItem.id) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
