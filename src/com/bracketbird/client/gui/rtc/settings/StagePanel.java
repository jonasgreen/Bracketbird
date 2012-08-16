package com.bracketbird.client.gui.rtc.settings;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.gui.rtc.event.REventListener;
import com.bracketbird.client.gui.rtc.event.UpdateLevelEvent;
import com.bracketbird.client.model.tournament.*;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.util.*;

/**
 *
 */
public class StagePanel extends VerticalComponent {

    private TournamentLevel level;
    private String imageUrl;
    private ImageComponent image;
    private SettingsPanel settingsPanel;
    private SettingsPanelWrapper settingsPanelWrapper;

    public StagePanel(TournamentLevel stage, String imageUrl) {
        super();
        this.imageUrl = imageUrl;
        this.level = stage;
        init();
    }

    private void init() {
        add(getImage());
    }

    public ImageComponent getImage() {
        if (image == null) {
            image = new ImageComponent(imageUrl);
            image.getImage().addMouseOverHandler(MouseOver.POINTER);
            image.getImage().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    showSettings();
                }
            });
        }
        return image;
    }

    private void showSettings() {
        PopupManager.show(getSettingsPanelWrapper(), new OnClose() {
            public void onClose() {

            }
        });
    }


    public SettingsPanelWrapper getSettingsPanelWrapper() {
        ButtonComponent save = new ButtonComponent("Save");
        save.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (settingsPanelWrapper.validate()) {
                    PopupManager.hide();
                    RTC.getInstance().updateLevelSettings(level.getId(), settingsPanel.getValue());

                }
            }
        });

        ButtonComponent cancel = new ButtonComponent("Cancel");
        cancel.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                settingsPanelWrapper.clear();
                PopupManager.hide();
            }
        });
        getSettingsPanel().setValue(level.getStageSettings());
        settingsPanelWrapper = new SettingsPanelWrapper(getSettingsPanel(), false, cancel, save);
        return settingsPanelWrapper;
    }


    private SettingsPanel getSettingsPanel() {
        if (settingsPanel == null) {
            if (level.getType() == TournamentLevelConstant.CUP.getValue()) {
                settingsPanel = new CupSettingsPanel(level);
            }
            else if (level.getType() == TournamentLevelConstant.GROUP.getValue()) {
                settingsPanel = new GroupSettingsPanel(level);
            }
            else if (level.getType() == TournamentLevelConstant.SEEDING.getValue()) {
                settingsPanel = new SeedingSettingsPanel(level);
            }
            else {
                throw new SystemException("type of tournament level is not supported. Level=" + level.getType());
            }
            level.addListener(new REventListener() {
                public void onChange(REvent<?, ?> event) {
                    settingsPanel.setValue(level.getStageSettings());
                }
            }, new UpdateLevelEvent());

        }
        return settingsPanel;
    }

    public TournamentLevel getLevel() {
        return level;
    }

    public void setLevel(TournamentLevel level) {
        this.level = level;
    }
}
