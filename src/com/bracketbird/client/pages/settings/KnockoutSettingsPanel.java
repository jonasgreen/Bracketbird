package com.bracketbird.client.pages.settings;


import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.model.tournament.StageSettings;
import com.bracketbird.client.model.tournament.Stage;
import com.bracketbird.client.rules.LargerThan;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class KnockoutSettingsPanel extends SettingsPanel {

    private Label maxTeamsLabel;
    public TextBox maxTeamsTextBox;
    private String maxTeamsTitle = "Maximal number of teams that can qualify to this stage of the tournament. \n" +
            "4 teams = semi finals, 8 teams = 1/4 finals etc.\n\n" +
            "leave notReady for unlimited teams";


    public Label eliminationTypeLabel = new Label("Elimination type");
    public Label eliminationType;


    public KnockoutSettingsPanel(Stage l) {
        super(l);

        addStyleName("settingsPage_knockoutSettingsPanel");
        addRow(eliminationTypeLabel, getEliminationType());
        addRow(getMaxTeamsLabel(), getMaxTeamsTextBox());
    }

    @Override
    protected void ok() {
        getErrorPanel().clear();
        validator.removeErrorStyles();

        try {
            StageSettings ls = new StageSettings();
            ls.setMaxNumberOfTeams(validator.toInteger(getMaxTeamsTextBox(), getMaxTeamsLabel(), false, new LargerThan(1)));

            RTC.getInstance().updateStageSettings(getLevel().getId(), ls);
            hide();
        }
        catch (SettingsValidationException e) {
            getErrorPanel().addError(e.getErrorMsg());
            e.getHasValueWidget().setFocus(true);
        }
    }

    @Override
    protected void setFocus() {
        getMaxTeamsTextBox().setFocus(true);
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
            Integer mnt = getLevel().getSettings().getMaxNumberOfTeams();
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
