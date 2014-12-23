package com.bracketbird.client.pages.settings;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class EndLevelComponent extends FlowPanel{

    public EndLevelComponent() {
        setStyleName("flex_center_center");

        FlowPanel leftEar = new FlowPanel();
        leftEar.setStyleName("settingsPage_stageComp_ear");
        leftEar.addStyleName("settingsPage_stageComp_leftEar");
        add(leftEar);

        FlowPanel innerPanel = new FlowPanel();
        innerPanel.setStyleName("flex_center_center");
        innerPanel.addStyleName("settingsPage_endComponent_innerPanel");

        Label l = new Label("End");
        l.setStyleName("settingsPage_stageComp_name");
        innerPanel.add(l);
        add(innerPanel);

    }
}
