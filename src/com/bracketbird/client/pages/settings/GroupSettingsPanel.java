package com.bracketbird.client.pages.settings;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.LevelSettings;
import com.bracketbird.client.model.tournament.TournamentLevel;
import com.bracketbird.client.rules.AtLeast;
import com.bracketbird.client.rules.LargerThan;
import com.bracketbird.clientcore.gui.OnClose;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class GroupSettingsPanel extends SettingsPanel {

    private Label numberOfGroupsLabel;
    public TextBox numberOfGroupsTextBox;
    public String numberOfGroupsTitle = "Number of groups the teams are divided into";

    private Label pointsWinningLabel;
    public TextBox pointsWinningTextBox;

    private Label pointsDrawLabel;
    public TextBox pointsDrawTextBox;

    private Label maxTeamsLabel;
    public TextBox maxTeamsTextBox;
    private String maxTeamsTitle = "Maximal number of teams that can qualify to this stage of the tournament. \n" +
            "leave empty for unlimited teams";



    public GroupSettingsPanel(TournamentLevel l, OnClose onClose) {
        super(l, onClose);

        addStyleName("groupSettingsPanel");
        addRow(getNumberOfGroupsLabel(), getNumberOfGroupsTextBox());
        addRow(getPointsWinningLabel(), getPointsWinningTextBox());
        addRow(getPointsDrawLabel(), getPointsDrawTextBox());
        addEmptyLine();
        addRow(getMaxTeamsLabel(), getMaxTeamsTextBox());
        addButtons();

    }

    public Label getPointsDrawLabel() {
        if (pointsDrawLabel == null) {
            pointsDrawLabel = new Label("Points for at draw");
        }
        return pointsDrawLabel;
    }

    public TextBox getPointsDrawTextBox() {
        if (pointsDrawTextBox == null) {
            pointsDrawTextBox = new TextBox();
            pointsDrawTextBox.setStyleName("settingsIntegerInput");
            pointsDrawTextBox.setValue(getLevel().getLevelSettings().getPointsOfDraw()+"");
        }
        return pointsDrawTextBox;
    }

    public Label getPointsWinningLabel() {
        if (pointsWinningLabel == null) {
            pointsWinningLabel = new Label("Points for a victory");
        }
        return pointsWinningLabel;
    }

    public TextBox getPointsWinningTextBox() {
        if (pointsWinningTextBox == null) {
            pointsWinningTextBox = new TextBox();
            pointsWinningTextBox.setStyleName("settingsIntegerInput");
            pointsWinningTextBox.setValue(getLevel().getLevelSettings().getPointsOfVictory()+"");
        }
        return pointsWinningTextBox;
    }

    public Label getNumberOfGroupsLabel() {
        if (numberOfGroupsLabel == null) {
            numberOfGroupsLabel = new Label("Number of groups");
            numberOfGroupsLabel.setTitle(numberOfGroupsTitle);
        }
        return numberOfGroupsLabel;
    }

    public TextBox getNumberOfGroupsTextBox() {
        if (numberOfGroupsTextBox == null) {
            numberOfGroupsTextBox = new TextBox();
            numberOfGroupsTextBox.setTitle(numberOfGroupsTitle);
            numberOfGroupsTextBox.setStyleName("settingsIntegerInput");
            numberOfGroupsTextBox.setValue(""+getLevel().getLevelSettings().getNumberOfGroups());
        }
        return numberOfGroupsTextBox;
    }

    @Override
    protected void save() {
        getErrorPanel().clear();
        validator.removeErrorStyles();

        try {
            LevelSettings ls = new LevelSettings();
            ls.setNumberOfGroups(validator.toInteger(getNumberOfGroupsTextBox(), getNumberOfGroupsLabel(), true, new LargerThan(0)));
            ls.setPointsOfVictory(validator.toInteger(getPointsWinningTextBox(), getPointsWinningLabel(), true, new LargerThan(0)));
            ls.setPointsOfDraw(validator.toInteger(getPointsDrawTextBox(), getPointsDrawLabel(), true, new AtLeast(0)));
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





}