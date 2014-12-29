package com.bracketbird.client.pages.matches;

import com.bracketbird.client.EqualsUtil;
import com.bracketbird.client.Printer;
import com.bracketbird.client.appcontrol.TournamentContext;
import com.bracketbird.client.model.tournament.KnockoutMatch;
import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.rtc.RTC;
import com.bracketbird.client.util.StringUtil;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class ResultBox extends SetEditor {

    private Match match;
    private MatchRow row;
    private MatchesTablePanel table;

    public ResultBox(Match match, MatchesTablePanel table, MatchRow matchRow) {
        this.match = match;
        this.row = matchRow;
        this.table = table;


        setStyleName("matchRow_result");

        getElement().setAttribute("placeholder", "Enter result");

        addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                Printer.println("KeyDown");
                handleKeyEvent(event);
            }
        });

        addFocusHandler(new FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                Printer.println("Focus");
                handleFocus();
            }
        });
        addBlurHandler(new BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                Printer.println("Blur");
                handleBlur();
            }
        });
        addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                Printer.println("ValueChanged");
                handleValueChanged();
            }
        });

        updateStyle();
    }

    public void handleValueChanged() {
        Printer.println("ValueChanged");
        String text = getText();
        if (StringUtil.isEmpty(text)) {
            removeStyleName("matchRow_result_error");
            if(match.getResult() != null) {
                RTC.getInstance().updateMatchResult(match.getId(), null);
            }
        }
        else {
            ResultValidator val = ResultValidator.create(getNumbers(text), !(match instanceof KnockoutMatch));
            if (val.isValid()) {
                removeStyleName("matchRow_result_error");
                //ensuring event is not fired twice
                if(!EqualsUtil.equals(match.getResult(), val.getResult())) {
                    RTC.getInstance().updateMatchResult(match.getId(), val.getResult());
                }
            }
            else {
                addStyleName("matchRow_result_error");
            }
        }

    }

    private void handleBlur() {
        handleValueChanged();
        updateStyle();

    }

    private void updateStyle() {
        if (match.getResult() == null) {
            getElement().setAttribute("placeholder", "Result");
        }
        row.removeStyleName("matchRow_focus");
    }

    private void handleFocus() {
        getElement().removeAttribute("placeholder");
        row.addStyleName("matchRow_focus");

        //if first or last row - ensure visible (extra scroll)
        if(table.getRows().get(0).equals(row) || table.getRows().get(table.getRows().size()-1).equals(row)){
            TournamentContext.get().getPageContainer().ensureVisible(this);
        }
    }

    private void handleKeyEvent(KeyDownEvent event) {
        Printer.println("KeyDown");
        int keyCode = event.getNativeKeyCode();
        if (KeyCodes.KEY_UP == keyCode && !event.isShiftKeyDown()) {
            table.up(row);
        }
        else if (KeyCodes.KEY_DOWN == keyCode && !event.isShiftKeyDown()) {
            table.down(row);
        }
        else if (KeyCodes.KEY_ENTER == keyCode) {
            table.down(row);
        }
    }


}
