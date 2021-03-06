package com.bracketbird.client.pages.settings;

import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.model.tournament.Stage;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 *
 */
public class LevelComponent extends FlowPanel {

    protected FlowPanel innerPanel;
    private Label nameLabel;

    private FlowPanel separationLine;
    private FlowPanel rightEar;
    private FlowPanel leftEar;

    private Stage level;
    private FlowPanel deleteIcon;

    public LevelComponent(Stage level) {
        setStyleName("flex_center_center");
        this.level = level;
        setTitle("Edit stage");
        getNameLabel().setText(level == null ? "?" : "");
        add(getLeftEar());
        add(getInnerPanel());
        add(getRightEar());
        add(getSeparationLine());
        addDomHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                LevelComponent.this.onMouseOver();
            }
        }, MouseOverEvent.getType());
        addDomHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                LevelComponent.this.onMouseOut();
            }
        }, MouseOutEvent.getType());
    }

    protected void onMouseOut() {
        getDeleteIcon().setVisible(false);
        removeStyleName("settingsPage_stageComp_mouseOver");
    }

    protected void onMouseOver() {
        getDeleteIcon().setVisible(true);
        addStyleName("settingsPage_stageComp_mouseOver");
    }


    public FlowPanel getInnerPanel() {
        if (innerPanel == null) {
            innerPanel = new FlowPanel();
            innerPanel.setStyleName("flex_center_center");
            innerPanel.addStyleName("settingsPage_stageComp_innerPanel");
            innerPanel.add(getNameLabel());
            innerPanel.add(getDeleteIcon());
            innerPanel.addDomHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    onclick();
                }
            }, ClickEvent.getType());

        }
        return innerPanel;
    }

    private void onclick() {
        onMouseOut();
        if (level != null) {
            CloseHandler<PopupPanel> onClose = new CloseHandler<PopupPanel>() {
                @Override
                public void onClose(CloseEvent event) {
                    getInnerPanel().setWidth("80px");
                    getInnerPanel().removeStyleName("settingsPage_stageComp_innerPanel_edit");
                }
            };

            final SettingsPanel p = level.isKnockoutStage() ? new KnockoutSettingsPanel(level) : new GroupSettingsPanel(level);
            p.addCloseHandler(onClose);
            getInnerPanel().setWidth("340px");
            getInnerPanel().addStyleName("settingsPage_stageComp_innerPanel_edit");

            Timer t = new Timer() {
                @Override
                public void run() {
                    p.setWidth("340px");
                    p.setPopupPosition(getInnerPanel().getAbsoluteLeft(), getInnerPanel().getAbsoluteTop() + 50);
                    p.show();
                }
            };

            t.schedule(300);



        }
    }


    public Label getNameLabel() {
        if (nameLabel == null) {
            nameLabel = new Label();
            nameLabel.setStyleName("settingsPage_stageComp_name");
        }
        return nameLabel;
    }

    public FlowPanel getSeparationLine() {
        if (separationLine == null) {
            separationLine = new FlowPanel();
            separationLine.setStyleName("settingsPage_stageComp_sep");
            separationLine.add(new Label(""));
        }
        return separationLine;
    }

    public FlowPanel getRightEar() {
        if (rightEar == null) {
            rightEar = new FlowPanel();
            rightEar.setStyleName("settingsPage_stageComp_ear");
            rightEar.addStyleName("settingsPage_stageComp_rightEar");
        }
        return rightEar;
    }

    public FlowPanel getLeftEar() {
        if (leftEar == null) {
            leftEar = new FlowPanel();
            leftEar.setStyleName("settingsPage_stageComp_ear");
            leftEar.addStyleName("settingsPage_stageComp_leftEar");
        }
        return leftEar;
    }


    public FlowPanel getDeleteIcon() {
        if (deleteIcon == null) {
            deleteIcon = new FlowPanel();
            deleteIcon.setStyleName("icon-uniE600");
            deleteIcon.addStyleName("settingsPage_stageComp_deleteIcon");
            deleteIcon.setVisible(false);
            deleteIcon.setTitle("Delete stage");
            deleteIcon.addDomHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    deleteIcon.setVisible(false);
                    RTC.getInstance().deleteStage(level.getId());
                    event.getNativeEvent().stopPropagation();
                    event.getNativeEvent().preventDefault();
                }
            }, ClickEvent.getType());
        }
        return deleteIcon;
    }


    public Stage getLevel() {
        return level;
    }
}
