package com.bracketbird.client.pages.settings;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class BeginLevelComponent extends FlowPanel{


    public BeginLevelComponent() {
        setStyleName("flex_center_center");

        FlowPanel innerPanel = new FlowPanel();
        innerPanel.setStyleName("flex_center_center");
        innerPanel.addStyleName("settingsPage_beginComponent_innerPanel");

        Label l = new Label("Begin");
        l.setStyleName("settingsPage_stageComp_name");
        innerPanel.add(l);
        add(innerPanel);

        FlowPanel rightEar = new FlowPanel();
        rightEar.setStyleName("settingsPage_stageComp_ear");
        rightEar.addStyleName("settingsPage_stageComp_rightEar");
        add(rightEar);

        FlowPanel separationLine = new FlowPanel();
        separationLine.setStyleName("settingsPage_stageComp_sep");
        separationLine.add(new Label(""));
        add(separationLine);

  }


}
