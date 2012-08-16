package com.bracketbird.client.gui.rtc;

import com.google.gwt.user.client.ui.*;

/**
 *
 */
public class VisualHelpItem{

    private UIObject target;
    private String imageUrl;
    private int extraLeft;
    private int extraTop;

    public VisualHelpItem(UIObject target,int extraLeft, int extraTop, String imageUrl) {
        this.target = target;
        this.imageUrl = imageUrl;
        this.extraLeft = extraLeft;
        this.extraTop = extraTop;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public UIObject getTarget() {
        return target;
    }


    public int getExtraLeft() {
        return extraLeft;
    }

    public void setExtraLeft(int extraLeft) {
        this.extraLeft = extraLeft;
    }

    public int getExtraTop() {
        return extraTop;
    }

    public void setExtraTop(int extraTop) {
        this.extraTop = extraTop;
    }
}
