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
        innerPanel.addStyleName("beginComponent_innerPanel");

        Label l = new Label("Begin");
        l.setStyleName("levelComponent_name");
        innerPanel.add(l);
        add(innerPanel);

        FlowPanel rightEar = new FlowPanel();
        rightEar.setStyleName("levelComponent_ear");
        rightEar.addStyleName("levelComponent_rightEar");
        add(rightEar);

        FlowPanel separationLine = new FlowPanel();
        separationLine.setStyleName("levelComponent_sep");
        separationLine.add(new Label(""));
        add(separationLine);

  }


}
