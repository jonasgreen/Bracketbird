package com.bracketbird.client.pages.teamspage;

import com.bracketbird.client.gui.rtc.RTC;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 *
 */
public class EnterTeam extends FlowPanel {

    private TeamsPage page;
    private TextBox textBox = new TextBox();

    public EnterTeam(TeamsPage page) {
        this.page = page;
        setStyleName("enterTeam");

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
    }

    private void focusLost() {
        updatePlaceHolder();
    }

    private void focusGained() {
        updatePlaceHolder();
    }

    public void updatePlaceHolder() {
        textBox.getElement().setAttribute("placeholder", page.getTeamRows().isEmpty() ? "Enter team name" : "Add another team");
    }

    private void keyUp(KeyUpEvent event) {
        if (event.isUpArrow() && !page.getTeamRows().isEmpty()) {
            TeamRow row = page.getTeamRows().get(page.getTeamRows().size() - 1);
            row.getTeamName().setFocus(true);
        }
    }

    private void keyDown(KeyDownEvent event) {
        if (KeyCodes.KEY_ENTER == event.getNativeKeyCode()) {
            RTC.getInstance().createTeam(textBox.getText(), page.getTeamRows().size() + 1);
        }
    }


    public void setText(String text) {
        textBox.setText(text);
        updatePlaceHolder();
    }

    public void setFocus(boolean focus) {
        textBox.setFocus(focus);
    }
}
