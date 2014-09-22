package com.bracketbird.client.pages.teamspage;

import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.clientcore.appcontrol.TournamentContext;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

/**
 *
 */
public class EnterTeam extends FlowPanel {

    private TeamsPage page;
    private TextBox textBox = new TextBox();
    private Button addTeamButton;

    public EnterTeam(TeamsPage page) {
        this.page = page;
        setStyleName("enterTeam");

        textBox.getElement().getStyle().setWidth(200, Style.Unit.PX);
        textBox.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                keyDown(event);
            }
        });

        textBox.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                keyUp(event);
            }
        });
        textBox.addFocusHandler(new FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                focusGained();
            }
        });

        textBox.addBlurHandler(new BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                focusLost();
            }
        });
        add(textBox);
        add(getAddTeamButton());
    }

    private void keyUp(KeyUpEvent event) {
        if (KeyCodes.KEY_ENTER == event.getNativeKeyCode()) {
            createTeam();
            stopEvent(event);

        }
    }

    public Button getAddTeamButton() {
        if (addTeamButton == null) {
            addTeamButton = new Button("Add Team");
            addTeamButton.setStyleName("primaryButton");
            addTeamButton.addStyleName("enterTeam_addButton");
            addTeamButton.getElement().getStyle().setMarginLeft(20, Style.Unit.PX);
            addTeamButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    createTeam();
                    textBox.setFocus(true);
                }
            });
            addTeamButton.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);
            addTeamButton.getElement().getStyle().setTop(-4, Style.Unit.PX);
        }
        return addTeamButton;
    }

    private void focusLost() {
        updatePlaceHolder();
    }

    private void focusGained() {
        updatePlaceHolder();
    }

    public void updatePlaceHolder() {
        textBox.getElement().setAttribute("placeholder", page.getTeamRows().isEmpty() ? "Enter team name" : "Enter another team");
    }

    private void keyDown(KeyDownEvent event) {
        if (event.isUpArrow() && !event.isShiftKeyDown() && !page.getTeamRows().isEmpty()) {
            TeamRow row = page.getTeamRows().get(page.getTeamRows().size() - 1);
            stopEvent(event);
            row.getTeamName().setFocus(true);
        }
    }

    private void createTeam() {
        RTC.getInstance().createTeam(textBox.getText(), page.getTeamRows().size() + 1);
    }


    public void setText(String text) {
        textBox.setText(text);
        updatePlaceHolder();
    }

    public void setFocus(boolean focus) {
        TournamentContext.get().getPageContainer().ensureVisible(this);
        textBox.setFocus(focus);
    }


    private void stopEvent(KeyEvent event){
        event.stopPropagation();
        event.preventDefault();
    }
}
