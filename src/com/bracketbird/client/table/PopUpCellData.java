package com.bracketbird.client.table;


import com.bracketbird.client.gui.rtc.Warning;
import com.bracketbird.client.gui.rtc.matches.MatchEditor;
import com.bracketbird.client.model.tournament.LevelStateAllMatchesPlayed;
import com.bracketbird.client.model.tournament.LevelStateInFinished;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.P;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Timer;

/**
 *
 */
public class PopUpCellData extends TextBoxComponent implements CellData {

    private final long id;
    private TableRow row;

    private Timer focusTimer = new Timer() {
        @Override
        public void run() {

            if (getTextBox().getCursorPos() != 0) {
                getTextBox().setCursorPos(0);
            }
        }
    };

    public PopUpCellData(TableRow r) {
        super();
        this.row = r;
        this.id = CellDataCounter.getCount();
        //getTextBox().setReadOnly(true);
        getTextBox().getElement().getStyle().setProperty("textIndent", "-2px");


        getTextBox().addKeyDownHandler(new KeyDownHandler() {
            public void onKeyDown(KeyDownEvent event) {
                if (event.getSource() == PopUpCellData.this.getTextBox()) {
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
                handleClick();
            }
        });

        getTextBox().addBlurHandler(new BlurHandler() {
            public void onBlur(BlurEvent event) {
                row.focusLost(PopUpCellData.this);
            }
        });

        getTextBox().addMouseOverHandler(MouseOver.POINTER);


    }

    protected void handleKeyEvent(KeyDownEvent event) {
        int keyCode = event.getNativeKeyCode();
        if (KeyCodes.KEY_UP == keyCode) {
            row.up(this);
        }
        else if (KeyCodes.KEY_DOWN == keyCode) {
            row.down(this);
        }
        else if (KeyCodes.KEY_RIGHT == keyCode) {
            row.right(this, event);
        }
        else if (KeyCodes.KEY_LEFT == keyCode) {
            row.left(this, event);

        }
        else if (KeyCodes.KEY_ENTER == keyCode) {
            handleEnter();
        }
        else{
        	event.preventDefault();
            event.stopPropagation();
        }
    }

    protected void handleEnter() {
        
    }


    
    
    public void setFocus(boolean focus) {
        getTextBox().setFocus(true);
    }

    public void handleFocus() {
        focusTimer.schedule(10);
        row.focusGained(this);
    }

    public void handleClick(){

    }


    public void handleClick2(){

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PopUpCellData that = (PopUpCellData) o;

        if (id != that.id) {
            return false;
        }

        return true;
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

    public void setText(String text) {
        super.setText(" " + text);
    }


    public void style(TextLayout tl) {
        StyleIt.add(this, tl);
    }

}
