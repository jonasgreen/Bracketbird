package com.bracketbird.client.pages.settingspage;

import com.bracketbird.client.Flex;
import com.bracketbird.client.model.LevelType;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class LevelComponent extends FlowPanel{

    protected FlowPanel innerPanel;
    private Label nameLabel;

    private FlowPanel separationLine;
    private FlowPanel rightEar;
    private FlowPanel leftEar;

    private LevelType levelType;

    public LevelComponent(LevelType levelType) {
        this.levelType = levelType;
        setStyleName(Flex.FLEX_CENTER);
        getNameLabel().setText(levelType.getLevelName());
        add(getLeftEar());
        add(getInnerPanel());
        add(getRightEar());
        add(getSeparationLine());
    }


    public FlowPanel getInnerPanel() {
        if (innerPanel == null) {
            innerPanel = new FlowPanel();
            innerPanel.setStyleName(Flex.FLEX_CENTER);
            innerPanel.addStyleName("levelComponent_innerPanel");
            innerPanel.add(getNameLabel());
        }
        return innerPanel;
    }

    public Label getNameLabel() {
        if (nameLabel == null) {
            nameLabel = new Label();
            nameLabel.setStyleName("levelComponent_name");
        }
        return nameLabel;
    }

    public FlowPanel getSeparationLine() {
        if (separationLine == null) {
            separationLine = new FlowPanel();
            separationLine.setStyleName("levelComponent_sep");
            separationLine.add(new Label(""));
        }
        return separationLine;
    }

    public FlowPanel getRightEar() {
        if (rightEar == null) {
            rightEar = new FlowPanel();
            rightEar.setStyleName("levelComponent_ear");
            rightEar.addStyleName("levelComponent_rightEar");
        }
        return rightEar;
    }

    public FlowPanel getLeftEar() {
        if (leftEar == null) {
            leftEar = new FlowPanel();
            leftEar.setStyleName("levelComponent_ear");
            leftEar.addStyleName("levelComponent_leftEar");
        }
        return leftEar;
    }

    public LevelType getLevelType() {
        return levelType;
    }
}
