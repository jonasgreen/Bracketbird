package com.bracketbird.client.table;


import com.bracketbird.clientcore.gui.GuiComponent;
import com.bracketbird.clientcore.gui.TextBoxComponent;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.event.dom.client.*;

/**
 *
 */
public class LabelCellData extends TextBoxComponent implements CellData{

    private final long id;
    private TableRow row;
    private ClickHandler clickHandler;

    public LabelCellData(TableRow r, String text) {
        this(r, text, null);
    }

    public LabelCellData(TableRow r, String text, ClickHandler clickHandler) {
        super();
        this.clickHandler = clickHandler;
        this.row = r;
        setText(text);
        this.id = CellDataCounter.getCount();
        getTextBox().setReadOnly(true);
        getTextBox().getElement().getStyle().setProperty("textIndent", "-2px");
        getTextBox().addFocusHandler(new FocusHandler() {
            public void onFocus(FocusEvent event) {
                row.focusLost(LabelCellData.this);
            }
        });
        if(clickHandler != null){
            getTextBox().addMouseOverHandler(MouseOver.POINTER);
        }
        getTextBox().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                row.focusLost(LabelCellData.this);
                row.focusNext(LabelCellData.this);
                
                if(LabelCellData.this.clickHandler != null){                    
                    LabelCellData.this.clickHandler.onClick(clickEvent);
                }
            }
        });
         getTextBox().addBlurHandler(new BlurHandler() {
            public void onBlur(BlurEvent event) {
                row.focusLost(LabelCellData.this);
            }
        });

    }

    public void setFocus(boolean focus) {
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

        LabelCellData that = (LabelCellData) o;

        if (id != that.getId()) {
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
        return false;
    }

    public void setText(String text){
        super.setText(" "+text);
    }


    public void style(TextLayout tl) {
        StyleIt.add(this, tl);
    }

}
