package com.bracketbird.clientcore.style;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;

/**
 *
 */
public enum Horizontal {
    RIGHT(HasHorizontalAlignment.ALIGN_RIGHT),
    CENTER(HasHorizontalAlignment.ALIGN_CENTER),
    LEFT(HasHorizontalAlignment.ALIGN_LEFT);

    private transient HasHorizontalAlignment.HorizontalAlignmentConstant alignment;

    Horizontal(HasHorizontalAlignment.HorizontalAlignmentConstant align){
        alignment = align;
    }

    public HasHorizontalAlignment.HorizontalAlignmentConstant getAlignment() {
        return alignment;
    }

}
