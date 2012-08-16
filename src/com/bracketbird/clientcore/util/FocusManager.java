package com.bracketbird.clientcore.util;

import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public interface FocusManager extends FocusComponentIntfc {

    public int getIndex(FocusComponentIntfc fc);

    public boolean isFirstItem(int index);

    public boolean isLastItem(int index);

    public void doFocus(FocusKeyDownEvent e, int index);

    public void onKeyDown(FocusKeyDownEvent e);

    public void setFocus();


    public void addChild(FocusComponentIntfc f);

    public void remove(FocusComponentIntfc f);

    public void replace(FocusComponentIntfc replace, FocusComponentIntfc with);


    void up(FocusKeyDownEvent e, int index, FocusManager fm);

    void down(FocusKeyDownEvent e, int index, FocusManager fm);

    void right(FocusKeyDownEvent e, int index, FocusManager fm);

    void left(FocusKeyDownEvent e, int index, FocusManager fm);


    public boolean isLeavable(SuggestBox sb, int keyCode);

    public boolean isLeavable(TextArea tb, int keyCode);

    public boolean isLeavable(TextBoxBase tbb, int keyCode);

    void setLastFocus(FocusComponentIntfc lastFocus);

    public void setCursorPosition(FocusComponentIntfc fc, TextBoxBase tbb, FocusKeyDownEvent e);

}