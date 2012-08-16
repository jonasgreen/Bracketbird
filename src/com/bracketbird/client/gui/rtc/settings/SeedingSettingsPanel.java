package com.bracketbird.client.gui.rtc.settings;


import com.bracketbird.client.gui.main.club.tournament.PropertiesFac;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.model.*;

/**
 *
 */
public class SeedingSettingsPanel extends SettingsPanel {


    public IntegerContainer ENTERING_TEAMS = PropertiesFac.maximumTeams();
    public IntegerContainer NUMBER_OF_MATCHES = PropertiesFac.numberOfMatches();


    protected SeedingSettingsPanel(TournamentLevel level) {
        super("Seeding stage settings", level);
        if (level.getType() != TournamentLevelConstant.SEEDING.getValue()) {
            throw new SystemException("Setting wrong kind of tournamentLevel. " + level.getClass().getName());
        }

        init();
    }

    private void init() {
        addFlexTable(NUMBER_OF_MATCHES);
        addAdvFlexTable(ENTERING_TEAMS);
    }


    @Override
    public Integer getType() {
        return TournamentLevelConstant.SEEDING.getValue();
    }


    @Override
    public void setValue(LevelSettings ss) {
        ENTERING_TEAMS.setValue(ss.getMaxNumberOfTeams());
        NUMBER_OF_MATCHES.setValue(ss.getNumberOfMatches());
    }

    @Override
    public LevelSettings getValue() {
        LevelSettings ss = new LevelSettings();
        ss.setMaxNumberOfTeams(ENTERING_TEAMS.getValue());
        ss.setNumberOfMatches(NUMBER_OF_MATCHES.getValue());
        return ss;
    }



}