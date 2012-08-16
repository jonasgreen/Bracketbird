package com.bracketbird.client.table;

import com.bracketbird.clientcore.gui.GuiComponent;
import com.bracketbird.clientcore.style.TextLayout;

/**
 *
 */
public interface CellData {

    public long getId();
    public GuiComponent getComponent();
    public boolean isFocusable();
    public void setFocus(boolean focus);
    public void style(TextLayout tl);
    public String getText();
}
