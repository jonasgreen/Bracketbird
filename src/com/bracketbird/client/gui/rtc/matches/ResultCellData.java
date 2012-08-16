package com.bracketbird.client.gui.rtc.matches;

import com.bracketbird.client.gui.rtc.Warning;
import com.bracketbird.client.model.tournament.LevelStateAllMatchesPlayed;
import com.bracketbird.client.model.tournament.LevelStateInFinished;
import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.table.PopUpCellData;
import com.bracketbird.client.table.TableRow;
import com.bracketbird.clientcore.gui.OnClose;
import com.bracketbird.clientcore.gui.PopupManager;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.dom.client.Style;

/**
 *
 */
public class ResultCellData extends PopUpCellData {
    private Match match;

    public ResultCellData(TableRow r, Match m) {
        super(r);
        this.match = m;
        setText("");
    }


    private boolean shouldWarn() {
        return match.getLevel().getState() instanceof LevelStateAllMatchesPlayed || match.getLevel().getState() instanceof LevelStateInFinished;
    }


    public void handleClick() {
        showMatch();
    }


    protected void handleEnter() {
        showMatch();
    }

    private void showMatch() {
        if (match.isWalkover()) {
            return;
        }
        if (shouldWarn()) {
            final Warning w = new Warning("Changing the result of this match, will affect the final ranking of this stage and reset any following stages.");
            PopupManager.show(w, new OnClose() {
                public void onClose() {
                    if (w.isProceed()) {
                        doShowMatch();
                    }
                    else {
                        setFocus(true);
                    }
                }
            });

        }
        else {
            doShowMatch();
        }
    }

    private void doShowMatch() {
        PopupManager.show(MatchEditor.getInstance(), new OnClose() {
            public void onClose() {
                setFocus(true);
            }
        });
        MatchEditor.getInstance().showMatch(match);
    }

    public void setText(String text) {
        if (match.isWalkover()) {
            super.setText("- walkover -");
            return;
        }
        if (text == null || text.trim().equals("")) {
            super.setText("Enter result ");
        }
        else {
            super.setText(text);
        }
        fixStyle();
    }

    public void style(TextLayout tl) {
        super.style(tl);
        fixStyle();
    }

    private void fixStyle() {
        if (match.isWalkover()) {
            return;
        }
        if (getText() != null && getText().trim().equalsIgnoreCase("Enter result")) {
            getTextBox().getElement().getStyle().setTextDecoration(Style.TextDecoration.UNDERLINE);
            getTextBox().getElement().getStyle().setColor("#1D9CD3");
        }
        else {
            getTextBox().getElement().getStyle().setTextDecoration(Style.TextDecoration.NONE);
            getTextBox().getElement().getStyle().setColor("#5C5C5C");
        }
    }

}
