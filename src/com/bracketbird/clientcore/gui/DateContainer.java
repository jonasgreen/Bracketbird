package com.bracketbird.clientcore.gui;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.dom.client.FocusHandler;
import com.bracketbird.clientcore.validation.Validator;

import java.util.Date;

/**
 *
 */
public class DateContainer extends DataContainer{
    private final DatePanel datePanel;

    public DateContainer(String name, boolean mandatory) {
        this(name, mandatory, DateTimeFormat.getFormat("dd/MM/yyyy"));
    }

    public Widget getDataWidget() {
        return datePanel.getTextBox().getTextBox();
    }

    public DateContainer(String name, boolean mandatory, DateTimeFormat dateFormat) {
        super(name, mandatory);
        add(Validator.DATE_FORMAT);
        datePanel = new DatePanel(dateFormat);
    }

    public GuiComponent getGui() {
        return datePanel;
    }

    public void clear() {
        datePanel.clear();
        setDataIsIllegal(false);
    }

    public boolean isEmpty() {
       return datePanel.isEmpty();
    }

    public void setFocus(boolean focus) {
       datePanel.getTextBox().setFocus(focus);
    }

    public void enableWidget(boolean editable) {
        
    }

    public void addFocusHandler(FocusHandler fh) {
        datePanel.getTextBox().getTextBox().addFocusHandler(fh);
    }

    public void setValue(Object obj) {
        datePanel.setDate((Date)obj);
    }

    public Date getValue() {
        return isEmpty() ? null : datePanel.getDate();
    }

    public String getStringValue(){
        return datePanel.getTextBox().getText();
    }

    public DateTimeFormat getDateFormat(){
        return datePanel.getDateFormat();
    }

    public void setVisible(boolean visible) {
        datePanel.setVisible(visible);
        getLabel().getLabel().setVisible(visible);
    }


}
