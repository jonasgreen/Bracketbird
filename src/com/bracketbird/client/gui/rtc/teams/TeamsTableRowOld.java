package com.bracketbird.client.gui.rtc.teams;


import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.gui.rtc.event.REventListener;
import com.bracketbird.client.gui.rtc.event.UpdateTeamInfoEvent;
import com.bracketbird.client.gui.rtc.event.UpdateTeamNameEvent;
import com.bracketbird.client.gui.rtc.event.UpdateTeamSeedingEvent;
import com.bracketbird.client.gui.rtc.matches.SeedingPageController;
import com.bracketbird.client.model.Team;
import com.bracketbird.client.table.CellData;
import com.bracketbird.client.table.EditOnlyTableData;
import com.bracketbird.client.table.LabelCellData;
import com.bracketbird.client.table.Table;
import com.bracketbird.client.table.TableRow;
import com.bracketbird.clientcore.appcontrol.Application;
import com.bracketbird.clientcore.style.Name;
import com.bracketbird.clientcore.style.P;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.util.StringUtil;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 *
 */
public class TeamsTableRowOld extends TableRow {

    private static TextLayout focusLayout = new TextLayout().sizeH3().bold().add(P.BACKGROUND_WHITE).colorBlack().border(2).borderColor(new P(Name.COLOR, "#29ABE2"));
    private static TextLayout normalLayout = new TextLayout().sizeNormal().add(P.BACKGROUND_WHITE).border(2).borderColor(P.BACKGROUND_WHITE).add(Name.COLOR, "#5C5C5C");
    private static TextLayout passivLayout = new TextLayout().sizeNormal().add(P.BACKGROUND_F5).border(2).borderColor(P.BACKGROUND_F5).add(Name.COLOR, "#5C5C5C");

    public static String COL_HEIGHT = "20px";

    public static TextLayout teamLayout = new TextLayout(COL_HEIGHT, "56%").alignLeft().add(Name.COLOR, "#5C5C5C");
    public static TextLayout commnetLaypout = new TextLayout(COL_HEIGHT, "40%").alignLeft().add(Name.COLOR, "#5C5C5C");
    public static TextLayout seedingLayout = new TextLayout(COL_HEIGHT, "4%").alignLeft().add(Name.COLOR, "#5C5C5C");

    private long lostFocusTime = 0;

    private Team team;


    private EditOnlyTableData columnTeamName;
    private EditOnlyTableData columnComment;
    private LabelCellData columnSeeding;


    public TeamsTableRowOld(Table table, Team team) {
        super(table, false);
        this.team = team;
        this.addCell(getColumnSeeding(), seedingLayout);
        this.addCell(getColumnTeamName(), teamLayout);
        this.addCell(getColumnComment(), commnetLaypout);

        team.addListener(new REventListener() {
            public void onChange(REvent<?, ?> event) {
                if (!event.isFromClient()) {
                    refreshRowValues();
                }
            }
        }, new UpdateTeamSeedingEvent(), new UpdateTeamNameEvent(), new UpdateTeamInfoEvent());

        refreshRowValues();
        paintFocusLost();
    }

    private void refreshRowValues() {
        getColumnTeamName().setText(getTeam().getName());
        getColumnComment().setText(getTeam().getInfo());
        getColumnSeeding().setText(getTeam().getSeeding() != null ? String.valueOf(getTeam().getSeeding()) : "");
    }


    public void paintFocusLost() {
        lostFocusTime = System.currentTimeMillis();
        super.paintFocusLost();
    }

    public EditOnlyTableData getColumnTeamName() {
        if (columnTeamName == null) {
            columnTeamName = new EditOnlyTableData(this);
        }
        return columnTeamName;
    }


    public EditOnlyTableData getColumnComment() {
        if (columnComment == null) {
            columnComment = new EditOnlyTableData(this);
        }
        return columnComment;
    }


    public LabelCellData getColumnSeeding() {
        if (columnSeeding == null) {
            columnSeeding = new LabelCellData(this, "", new ClickHandler() {
                public void onClick(ClickEvent event) {
                    Application.popUp(SeedingPageController.getInstance());
                }
            });
        }
        return columnSeeding;
    }


    @Override
    public TextLayout getNormalLayout() {
        return normalLayout;
    }

    @Override
    public TextLayout getFocusLayout() {
        return focusLayout;
    }

    @Override
    public TextLayout getPassiveLayout() {
        return passivLayout;
    }

    @Override
    protected void commit(CellData cell) {
        String value = cell.getText().trim();

        if (cell.equals(columnTeamName)) {
            if (!StringUtil.equals(value, team.getName())) {
                RTC.getInstance().updateTeamName(team.getId(), value);
            }
        }
        else if (cell.equals(columnComment)) {
            if (!StringUtil.equals(value, team.getInfo())) {
                RTC.getInstance().updateTeamInfo(team.getId(), value);
            }
        }
        else if (!StringUtil.equals(String.valueOf(team.getSeeding()), value)) {
            try {
                if (value == null || value.equals("")) {
                    RTC.getInstance().updateTeamSeeding(team.getId(), null);
                }
                else {
                    RTC.getInstance().updateTeamSeeding(team.getId(), Integer.valueOf(value));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Team getTeam() {
        return team;
    }

    public long getLostFocusTime() {
        return lostFocusTime;
    }

    @Override
    public void setFocus(boolean focus) {
        if (getAllCells().isEmpty()) {
            return;
        }
        if (lastFocus != null) {
            lastFocus.setFocus(true);
        }
        else {
            getAllCells().get(1).setFocus(true);
        }
    }
}

