package com.bracketbird.client.pages.matches;

import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.clientcore.appcontrol.TournamentContext;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.TextBox;

public class ResultBox extends TextBox {

    private Match match;
    private MatchRow row;
    private MatchesTable table;

    public ResultBox(Match match, MatchesTable table,  MatchRow matchRow) {
        this.match = match;
        this.row = matchRow;
        this.table = table;

        setStyleName("matchRow_result");

        addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                handleKeyEvent(event);
            }
        });

        addFocusHandler(new FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                handleFocus();
            }
        });
        addBlurHandler(new BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                handleBlur();
            }
        });
        handleBlur();
    }

    private void handleBlur() {
        if(match.getResult() == null) {
            getElement().setAttribute("placeholder", "Result");
        }
        row.removeStyleName("matchRow_focus");
    }

    private void handleFocus() {
        getElement().removeAttribute("placeholder");
        row.addStyleName("matchRow_focus");
        TournamentContext.get().getPageContainer().ensureVisible(this);
    }

    private void handleKeyEvent(KeyDownEvent event) {
        int keyCode = event.getNativeKeyCode();
        if (KeyCodes.KEY_UP == keyCode) {
            table.up(row);
        }
        else if (KeyCodes.KEY_DOWN == keyCode) {
            table.down(row);
        }
        else if (KeyCodes.KEY_ENTER == keyCode) {
            table.down(row);
            event.stopPropagation();
            event.preventDefault();
        }
    }


}
