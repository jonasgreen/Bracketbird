package com.bracketbird.clientcore.gui;


import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class InfoPanel extends HorizontalComponent {
    private LabelComponent label;

    public InfoPanel() {
        super();

        setBackgroundColor(Color.backgroundCompl());
        add(getLabel(), new Layout17(10, 20, 10, 20, Horizontal.CENTER, null));
    }


    public LabelComponent getLabel() {
        if (label == null) {
            label = new LabelComponent("No Info");
            label.getLabel().setWordWrap(true);
            label.setStyle(P.FONT_WEIGHT_BOLD);
        }
        return label;
    }


}
