package com.bracketbird.client.gui.grid;

/**
 *
 */
public class GridRowStyle extends Style {

    public static final GridRowStyle row = new GridRowStyle("pure-g");
    public static final GridRowStyle headerRow = new GridRowStyle("pure-g-header");
    public static final GridRowStyle sectionRow = new GridRowStyle("pure-g-section");
    public static final GridRowStyle rowResponsive = new GridRowStyle("pure-g-r");

    public GridRowStyle(String style) {

        super(style);
    }

}
