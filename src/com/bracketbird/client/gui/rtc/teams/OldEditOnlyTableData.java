package com.bracketbird.client.gui.rtc.teams;


import com.bracketbird.client.gui.rtc.RTC;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.*;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class OldEditOnlyTableData extends TextBoxComponent {

    private static long counter = 0;
    private final long id;


    private Timer focusTimer = new Timer() {
        @Override
        public void run() {
            if (getTextBox().getCursorPos() == 1) {
                getTextBox().setSelectionRange(0, 0);
            }
            else if (getTextBox().getCursorPos() == getText().length() - 1) {
                getTextBox().setSelectionRange(getText().length(), 0);
            }
        }
    };

    private OldTableRowNotifier oldTableRowNotifier;

    public OldEditOnlyTableData(final OldTableRowNotifier oldTableRowNotifier) {
        super();
        this.id = counter++;
        this.oldTableRowNotifier = oldTableRowNotifier;

        getTextBox().addKeyDownHandler(new KeyDownHandler() {
            public void onKeyDown(KeyDownEvent event) {
                handleKeyEvent(event);
            }
        });

        getTextBox().addFocusHandler(new FocusHandler() {
            public void onFocus(FocusEvent event) {
                setFocus();
            }
        });

        getTextBox().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                setFocus();
            }
        });

        getTextBox().addBlurHandler(new BlurHandler() {
            public void onBlur(BlurEvent event) {
                commit(getValue());
                oldTableRowNotifier.focusLost();
            }
        });

    }

    private void handleKeyEvent(KeyDownEvent event) {
        int keyCode = event.getNativeKeyCode();
        if (KeyCodes.KEY_UP == keyCode) {
            //commit(getTextBox().getValue());
            oldTableRowNotifier.up();
        }
        else if (KeyCodes.KEY_DOWN == keyCode) {
            //commit(getTextBox().getValue());
            oldTableRowNotifier.down();
        }
        else if (KeyCodes.KEY_RIGHT == keyCode) {
            if (getTextBox().getCursorPos() == getText().length()) {
              //  commit(getTextBox().getValue());
                oldTableRowNotifier.right();
            }
        }
        else if (KeyCodes.KEY_LEFT == keyCode) {
            if (getTextBox().getCursorPos() == 0) {
                //commit(getTextBox().getValue());
                oldTableRowNotifier.left();
            }
        }
        else if (KeyCodes.KEY_ENTER == keyCode) {
            //commit(getTextBox().getValue());
            //TODO - change to continue one row down
            RTC.getInstance().createTeam();
        }
        else if (KeyCodes.KEY_DELETE == keyCode || KeyCodes.KEY_BACKSPACE == keyCode) {
            if (getText() == null || getText().equals("")) {
                oldTableRowNotifier.deleteRow();
            }
        }
    }

    public void setFocus(boolean focus) {
        setFocus();
    }

    public void setFocus() {
        getTextBox().setFocus(true);
        oldTableRowNotifier.focusGained();
        focusTimer.schedule(10);

    }


    private void commit(String value) {
        oldTableRowNotifier.commit(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OldEditOnlyTableData that = (OldEditOnlyTableData) o;

        if (id != that.id) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
