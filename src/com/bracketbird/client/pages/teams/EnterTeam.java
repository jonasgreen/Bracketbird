package com.bracketbird.client.pages.teams;

import com.bracketbird.client.Css;
import com.bracketbird.client.gui.rtc.RTC;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 *
 */
public class EnterTeam extends FlowPanel {

    private TeamsPage page;
    private TextBox textBox = new TextBox();
    private Button addTeamButton;

    private int top = 0;
    private int left = 0;

    public EnterTeam(TeamsPage page) {
        this.page = page;
        Css.style(this, "enterTeam", "flex_alignItems_baseline");

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
        if (KeyCodes.KEY_ENTER == event.getNativeKeyCode() && event.getSource() == textBox) {
            createTeam();
            stopEvent(event);
        }
    }

    public Button getAddTeamButton() {
        if (addTeamButton == null) {
            addTeamButton = new Button("Add Team");
            addTeamButton.setStyleName("primaryButton");
            addTeamButton.addStyleName("enterTeam_addButton");
            addTeamButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    createTeam();
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
        RTC.getInstance().createTeam(textBox.getText(), 0);
    }


    public void setText(String text) {
        textBox.setText(text);
        updatePlaceHolder();
    }

    public void setFocus(boolean focus) {
        //TournamentContext.get().getPageContainer().ensureVisible(this);
        textBox.setFocus(focus);
    }


    private void stopEvent(KeyEvent event){
        event.stopPropagation();
        event.preventDefault();
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
        getElement().getStyle().setTop(top, Style.Unit.PX);
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
        getElement().getStyle().setLeft(left, Style.Unit.PX);
    }
}
