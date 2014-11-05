package com.bracketbird.client.pages.settings;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.LevelSettings;
import com.bracketbird.client.model.tournament.TournamentLevel;
import com.bracketbird.client.rules.LargerThan;
import com.bracketbird.clientcore.gui.OnClose;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class KnockoutSettingsPanel extends SettingsPanel {

    private Label maxTeamsLabel;
    public TextBox maxTeamsTextBox;
    private String maxTeamsTitle = "Maximal number of teams that can qualify to this stage of the tournament. \n" +
            "4 teams = semi finals, 8 teams = 1/4 finals etc.\n\n" +
            "leave empty for unlimited teams";


    public Label eliminationTypeLabel = new Label("Elimination type");
    public Label eliminationType;


    public KnockoutSettingsPanel(TournamentLevel l, OnClose onClose) {
        super(l, onClose);

        addStyleName("knockoutSettingsPanel");
        addRow(eliminationTypeLabel, getEliminationType());
        addRow(getMaxTeamsLabel(), getMaxTeamsTextBox());
        addButtons();
    }

    @Override
    protected void save() {
        getErrorPanel().clear();
        validator.removeErrorStyles();

        try {
            LevelSettings ls = new LevelSettings();
            ls.setMaxNumberOfTeams(validator.toInteger(getMaxTeamsTextBox(), getMaxTeamsLabel(), false, new LargerThan(1)));

            RTC.getInstance().updateLevelSettings(getLevel().getId(), ls);
            hide();
        }
        catch (SettingsValidationException e) {
            getErrorPanel().addError(e.getErrorMsg());
            e.getHasValueWidget().setFocus(true);
        }
    }


    public Label getMaxTeamsLabel() {
        if (maxTeamsLabel == null) {
            maxTeamsLabel = new Label("Maximum number of teams");
            maxTeamsLabel.setTitle(maxTeamsTitle);
        }
        return maxTeamsLabel;
    }


    public TextBox getMaxTeamsTextBox() {
        if (maxTeamsTextBox == null) {
            maxTeamsTextBox = new TextBox();
            maxTeamsTextBox.setTitle(maxTeamsTitle);
            maxTeamsTextBox.setStyleName("settingsIntegerInput");
            Integer mnt = getLevel().getLevelSettings().getMaxNumberOfTeams();
            if(mnt != null){
                maxTeamsTextBox.setValue(""+mnt);
            }
        }
        return maxTeamsTextBox;
    }

    public Label getEliminationType() {
        if (eliminationType == null) {
            eliminationType = new Label("Single elimination");
            eliminationType.setStyleName("noneEditableValue");
        }
        return eliminationType;
    }




}