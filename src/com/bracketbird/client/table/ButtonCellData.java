package com.bracketbird.client.table;


import com.bracketbird.clientcore.gui.ButtonComponent;
import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.gui.GuiComponent;
import com.bracketbird.clientcore.gui.TextBoxComponent;
import com.bracketbird.clientcore.style.P;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Timer;

/**
 *
 */
public class ButtonCellData extends FlowComponent implements CellData {

    private final long id;
    private TableRow row;
    private ButtonComponent button;


    public ButtonCellData(TableRow r) {
        super();
        this.row = r;
        this.id = CellDataCounter.getCount();
        getButton().setText("Enter result");
        
        add(getButton(), new TextLayout("100%", "70%"));

        getButton().getButton().addKeyDownHandler(new KeyDownHandler() {
            public void onKeyDown(KeyDownEvent event) {

                if (event.getSource() == ButtonCellData.this.getButton().getButton()) {
                    handleKeyEvent(event);
                    event.preventDefault();
                    event.stopPropagation();
                }
            }
        });



        getButton().getButton().addFocusHandler(new FocusHandler() {
            public void onFocus(FocusEvent event) {
                handleFocus();
            }
        });

        getButton().getButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                getButton().getButton().setFocus(true);
            }
        });

        getButton().getButton().addBlurHandler(new BlurHandler() {
            public void onBlur(BlurEvent event) {
                row.focusLost(ButtonCellData.this);
            }
        });

    }

    public ButtonComponent getButton() {
        if (button == null) {
            button = new ButtonComponent();
            button.getButton().setStyleName("buttoncell");
        }
        return button;
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
            row.right(this, event);
        }
        else if (KeyCodes.KEY_LEFT == keyCode) {
            row.left(this, event);

        }
        else if (KeyCodes.KEY_ENTER == keyCode) {
            row.enter(this);
        }
    }


    public void setFocus(boolean focus) {
        getButton().getButton().setFocus(true);
    }

    public void handleFocus() {
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

        ButtonCellData that = (ButtonCellData) o;

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
        getButton().setText(text);
    }


    public void style(TextLayout tl) {
        StyleIt.add(this, tl);
    }

    public String getText() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
