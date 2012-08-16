package com.bracketbird.client.gui.rtc;


import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.P;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 *
 */
public class OkWarning extends Warning {

    private ButtonComponent okButton;

    public OkWarning(String text) {
        super(text);
    }

    public ButtonPanel getButtonPanel() {
        if (buttonPanel == null) {
            ButtonComponent ok = getOkButton();
            buttonPanel = new ButtonPanel(ok);
        }
        return buttonPanel;
    }

    public ButtonComponent getOkButton() {

        if (okButton == null) {
            okButton = new ButtonComponent("Ok");

            okButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    PopupManager.hide();
                }
            });
            okButton.getButton().setFocus(true);
        }
        return okButton;
    }


    protected TextLayout getBackgroundLayout() {
        return new TextLayout(null, "100%").add(P.BACKGROUND_BLUE);
    }

    protected TextLayout getTextLayout() {
        return new TextLayout(null, "500px").sizeNormal().colorWhite().padding(20);
    }


    public LabelComponent getHeader() {
        if (header == null) {
            header = new LabelComponent("");
        }
        return header;
    }

}
