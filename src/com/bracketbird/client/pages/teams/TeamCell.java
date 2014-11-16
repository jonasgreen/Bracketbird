package com.bracketbird.client.pages.teams;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.gui.rtc.event.REventListener;
import com.bracketbird.client.gui.rtc.event.UpdateTeamNameEvent;
import com.bracketbird.client.model.Team;
import com.bracketbird.clientcore.appcontrol.TournamentContext;
import com.bracketbird.clientcore.util.StringUtil;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.TextBox;

import java.util.List;

/**
 *
 */
public class TeamCell extends TextBox{

    private Team team;
    private TeamRow row;

    //Used to avoid deleting team when user holds backspace down to delete name
    private String contentOfCellOnLastKeyUp = "";


    public TeamCell(TeamRow row, Team team) {
        this.row = row;
        this.team = team;
        setStyleName("teamsRow_team");
        setText(team.getName());
        team.addListener(new REventListener() {
            @Override
            public void onChange(REvent<?, ?> event) {
                setText(TeamCell.this.team.getName());
            }
        }, new UpdateTeamNameEvent());


        addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                keyDown(event);
            }
        });

        addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                keyUp(event);
            }
        });

        addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                valueChanged(event);
            }
        });

    }


    @Override
    public void setText(String text){
        super.setText(text);
        this.contentOfCellOnLastKeyUp = text;
    }

    private void keyUp(KeyUpEvent event) {
        this.contentOfCellOnLastKeyUp = getText();
    }


    @Override
    public void setFocus(boolean focus){
        //if first row - ensure visible (extra scroll)
        List<TeamRow> rows = TeamsPageController.getInstance().getPage().getTeamRows();
        if(rows.get(0).getTeam().equals(team) || rows.get(rows.size()-1).getTeam().equals(team)){
            TournamentContext.get().getPageContainer().ensureVisible(this);
        }
        super.setFocus(focus);
    }

    private void keyDown(KeyDownEvent event){
        int key = event.getNativeKeyCode();
        if(KeyCodes.KEY_DELETE == key || KeyCodes.KEY_BACKSPACE == key){
            if(isReadyToDeleteByKeyboard(event)){
                RTC.getInstance().deleteTeam(team.getId());
                stopEvent(event);
            }
        }
        else if(event.isUpArrow() && !event.isShiftKeyDown()){
            TeamsPageController.getInstance().getPage().moveFocusUp(row);
            stopEvent(event);
        }
        else if(event.isDownArrow() && !event.isShiftKeyDown()){
            TeamsPageController.getInstance().getPage().moveFocusDown(row);
            stopEvent(event);
        }
    }

    private boolean isReadyToDeleteByKeyboard(KeyDownEvent event){
        return "".equals(contentOfCellOnLastKeyUp);
    }


    private void valueChanged(ValueChangeEvent<String> event) {
        if(StringUtil.equals(team.getName(), getText())){
            return;
        }
        System.out.println("Update Team name");
        RTC.getInstance().updateTeamName(team.getId(), getText());
    }

    private void stopEvent(KeyEvent event){
        event.stopPropagation();
        event.preventDefault();
    }

}
