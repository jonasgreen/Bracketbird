package com.bracketbird.clientcore.util;

import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public interface FocusComponentIntfc {

    public void doFocus(FocusKeyDownEvent e);
    public boolean isLeavable(int keyCode);
    public void addParent(FocusManager fm);

    public void focusLost();
}
