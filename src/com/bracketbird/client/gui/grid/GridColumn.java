package com.bracketbird.client.gui.grid;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class GridColumn extends FlowPanel {
	private static final ColWidth col = new ColWidth("pure-col");

    protected GridColumn(ColWidth width) {
		addStyleName("pure-col");
		addStyleName(width.get());
	}


    public GridColumn(ColWidth width, Widget w) {
        this(width);
        add(w);
    }
}
