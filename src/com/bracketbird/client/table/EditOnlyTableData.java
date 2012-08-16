package com.bracketbird.client.table;


import com.bracketbird.clientcore.gui.GuiComponent;
import com.bracketbird.clientcore.gui.TextBoxComponent;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Timer;

/**
 *
 */
public class EditOnlyTableData extends TextBoxComponent implements CellData {

    private final long id;
    protected final TableRow row;

    public EditOnlyTableData(TableRow r) {
        this(r, "");
    }

    public EditOnlyTableData(TableRow r, String text) {
        super();

        this.row = r;
        this.id = CellDataCounter.getCount();
        getTextBox().setText(text);
        getTextBox().addKeyDownHandler(new KeyDownHandler() {
            public void onKeyDown(KeyDownEvent event) {
                if (event.getSource() == EditOnlyTableData.this.getTextBox()) {
                    handleKeyEvent(event);
                }
            }
        });

        getTextBox().addFocusHandler(new FocusHandler() {
            public void onFocus(FocusEvent event) {
                handleFocus();
            }
        });

        getTextBox().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            }
        });

        getTextBox().addBlurHandler(new BlurHandler() {
            public void onBlur(BlurEvent event) {
                row.focusLost(EditOnlyTableData.this);
            }
        });

    }

    private void handleKeyEvent(KeyDownEvent event) {
        int keyCode = event.getNativeKeyCode();
        if (KeyCodes.KEY_UP == keyCode) {
            row.up(this);
        }
        else if (KeyCodes.KEY_DOWN == keyCode) {
            row.down(this);
        }
        else if (KeyCodes.KEY_RIGHT == keyCode) {
            if (getTextBox().getCursorPos() == getText().length()) {
                row.right(this, event);
            }
        }
        else if (KeyCodes.KEY_LEFT == keyCode) {
            if (getTextBox().getCursorPos() == 0) {
                row.left(this, event);
            }
        }
        else if (KeyCodes.KEY_ENTER == keyCode) {
            row.enter(this);
        }
        else if (KeyCodes.KEY_DELETE == keyCode || KeyCodes.KEY_BACKSPACE == keyCode) {
            if (getText() == null || getText().equals("")) {
                row.backspace(this);
                event.stopPropagation();
                event.preventDefault();
            }
        }

    }

    public void setFocus(boolean focus) {
        getTextBox().setFocus(true);
    }

    public void style(TextLayout tl) {
        StyleIt.add(this, tl);
    }

    protected void handleFocus() {
        row.focusGained(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EditOnlyTableData that = (EditOnlyTableData) o;

        if (id != that.id) {
            return false;
        }

        return true;
    }

    public String getText(){
        return getTextBox().getText();
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public long getId() {
        return id;
    }

    public GuiComponent getComponent() {
        return this;
    }

    public boolean isFocusable() {
        return true;
    }


}
