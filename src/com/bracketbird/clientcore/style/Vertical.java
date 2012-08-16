package com.bracketbird.clientcore.style;

import com.google.gwt.user.client.ui.HasVerticalAlignment;

/**
 *
 */
public enum Vertical {
    TOP(HasVerticalAlignment.ALIGN_TOP),
    MIDDLE(HasVerticalAlignment.ALIGN_MIDDLE),
    BOTTOM(HasVerticalAlignment.ALIGN_BOTTOM);

    private transient HasVerticalAlignment.VerticalAlignmentConstant alignment;

    Vertical(HasVerticalAlignment.VerticalAlignmentConstant align){
        alignment = align;
    }

    public HasVerticalAlignment.VerticalAlignmentConstant getAlignment() {
        return alignment;
    }
}
