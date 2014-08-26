package com.bracketbird.client.gui.rtc.settings;


import com.bracketbird.client.gui.rtc.RTCLayoutFac2;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 *
 */
public class SettingsPage extends FlowPanelPage<SettingsPageController> {

    private VerticalComponent content;
    private SettingsSetupPanel settingsSetupPanel;
    private TournamentSettingsPanel tournamentSettingsPanel;


    public SettingsPage() {
        super();
       // content = new VerticalComponent();
       // add(content);
    }

    public void init() {
        /*StyleIt.add(content, RTCLayoutFac2.CONTENT);

        RTC.getInstance().getTournament().addLevelListener(new TournamentListener<TournamentLevelEvent>() {
            public void onChange(TournamentLevelEvent event) {
                if (event.getAction() == TournamentLevelEvent.LevelAction.create) {
                    createStage(event.getLevel());
                }
                else if (event.getAction() == TournamentLevelEvent.LevelAction.delete) {
                    removeStage();
                }
            }
        });


        content.add(new LabelComponent("Tournament settings"), RTCLayoutFac2.h1().margin(0, 0, 20, 0));
 //       content.add(getSettingsSetupPanel());
        content.add(getTournamentSettingsPanel(), new TextLayout(null, "100%"));
//        getTournamentSettingsPanel().setVisible(false);

*/
    }

    private void removeStage() {
        getTournamentSettingsPanel().removeStage();
        if (getTournamentSettingsPanel().isEmpty()) {
            //showSettings(false);
        }
    }

    private void showSettings(boolean show) {
        getSettingsSetupPanel().setVisible(!show);
        getTournamentSettingsPanel().setVisible(show);

    }

    private void createStage(TournamentLevel level) {
        showSettings(true);
        getTournamentSettingsPanel().createStage(level);
    }


    public TournamentSettingsPanel getTournamentSettingsPanel() {
        if (tournamentSettingsPanel == null) {
            tournamentSettingsPanel = new TournamentSettingsPanel(false);
        }
        return tournamentSettingsPanel;
    }

    public SettingsSetupPanel getSettingsSetupPanel() {
        if (settingsSetupPanel == null) {
            settingsSetupPanel = new SettingsSetupPanel();
        }
        return settingsSetupPanel;
    }

    protected void setSubPageHolder(Page subPage) {
        //ignore
    }


}