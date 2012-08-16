package com.bracketbird.client.gui.rtc;


import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class UpdatingTournamentInfoPanel extends HorizontalComponent {
    private LabelComponent labelComponent;
    private static UpdatingTournamentInfoPanel instance;


    private UpdatingTournamentInfoPanel() {
        super();
        add(new ImageComponent("spin.png"), new TextLayout(20, 10, 20, 20));
        add(getLabelComponent(), RTCLayoutFac2.h2().margin(0, 20, 0, 0));

    }

    public static UpdatingTournamentInfoPanel getInstance() {
        if (instance == null) {
            instance = new UpdatingTournamentInfoPanel();
        }
        return instance;
    }

    private LabelComponent getLabelComponent() {
        if (labelComponent == null) {
            labelComponent = new LabelComponent("");
        }
        return labelComponent;
    }

    public void setText(String text) {
        getLabelComponent().setText(text);
    }


}
