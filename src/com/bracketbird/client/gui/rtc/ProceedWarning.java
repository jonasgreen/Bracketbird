package com.bracketbird.client.gui.rtc;


import com.bracketbird.client.PopupBracketBird;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class ProceedWarning extends PopupBracketBird {

    public ProceedWarning(String text) {
        super(false, true);
        setGlassEnabled(true);
        setGlassStyleName("glass");
        addStyleName("shadow");
        addStyleName("proceedWarning");
        getHeaderLabel().setText("Warning");
        getContentPanel().add(new Label(text));
        getOkButton().setText("Proceed");
    }

}
