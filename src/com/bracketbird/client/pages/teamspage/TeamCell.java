package com.bracketbird.client.pages.teamspage;

import com.bracketbird.client.model.Team;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.TextBox;

/**
 *
 */
public class TeamCell extends TextBox{

    private Team team;
    private String contentOfCellOnKeyDown = "";


    public TeamCell(Team team) {
        this.team = team;
        setStyleName("teamsRow_team");
        setText(team.getName());

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
    }

    private void keyUp(KeyUpEvent event) {
        int key = event.getNativeKeyCode();
        if(KeyCodes.KEY_DELETE == key || KeyCodes.KEY_BACKSPACE == key){
            //To avoid deleting team when use wants to delete name
            if("".equals(contentOfCellOnKeyDown)){
                System.out.println("DELETE TEAM");//TODO
                //delete team
            }
            else{
                //ignore
            }
        }
        contentOfCellOnKeyDown = "";
    }

    private void keyDown(KeyDownEvent event) {
        contentOfCellOnKeyDown = getText();
    }
}
