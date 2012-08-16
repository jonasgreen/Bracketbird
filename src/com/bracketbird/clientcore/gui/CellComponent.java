package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public abstract class CellComponent extends PanelComponent {

    public CellComponent() {
        super();
    }



    

    public void setCellHorizontalAlignment(Widget w, HasHorizontalAlignment.HorizontalAlignmentConstant align) {
        ((CellPanel) getPanel()).setCellHorizontalAlignment(w, align);
    }

    public void setCellVerticalAlignment(Widget w, HasVerticalAlignment.VerticalAlignmentConstant align) {
        ((CellPanel) getPanel()).setCellVerticalAlignment(w, align);
    }

    public void setCellWidth(Widget w, String width) {
        ((CellPanel) getPanel()).setCellWidth(w, width);
    }

    public void setCellHeight(Widget w, String height) {
        ((CellPanel) getPanel()).setCellHeight(w, height);
    }

}
