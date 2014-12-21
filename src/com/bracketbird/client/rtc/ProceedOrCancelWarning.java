package com.bracketbird.client.rtc;


import com.bracketbird.client.PopupBracketBird;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class ProceedOrCancelWarning extends PopupBracketBird {

    public ProceedOrCancelWarning(String text) {
        super(false, true);
        setGlassEnabled(true);
        setGlassStyleName("glass");
        addStyleName("shadow");
        addStyleName("proceedWarning");
        getHeaderLabel().setText("Watch out!");
        getContentPanel().add(new Label(text));
        getOkButton().setText("Proceed");
    }

    @Override
    protected void setFocus() {
        //ignore
    }
}
