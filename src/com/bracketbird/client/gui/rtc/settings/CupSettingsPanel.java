package com.bracketbird.client.gui.rtc.settings;


import com.bracketbird.client.gui.main.club.tournament.PropertiesFac;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.model.*;

/**
 *
 */
public class CupSettingsPanel extends SettingsPanel {


    public IntegerContainer ENTERING_TEAMS = PropertiesFac.maximumTeams();
    public ListContainer<Integer> ELIMINATION_TYPE = PropertiesFac.eliminationType();


    protected CupSettingsPanel(TournamentLevel level) {
        super("Cup stage settings", level);
        if (level.getType() != TournamentLevelConstant.CUP.getValue()) {
            throw new SystemException("Setting wrong kind of tournamentLevel. " + level.getClass().getName());
        }
        init();
    }

    private void init() {
        addFlexTable(ELIMINATION_TYPE, ENTERING_TEAMS);
    }


    public LevelSettings getValue() {
        LevelSettings ss = new LevelSettings();
        ss.setEliminationType(ELIMINATION_TYPE.getValue());
        ss.setMaxNumberOfTeams(ENTERING_TEAMS.getValue());
        return ss;
    }


    @Override
    public Integer getType() {
        return TournamentLevelConstant.CUP.getValue();
    }

    public void setValue(LevelSettings ss) {
        ENTERING_TEAMS.setValue(ss.getMaxNumberOfTeams());
        ELIMINATION_TYPE.setValue(ss.getEliminationType());
    }


}