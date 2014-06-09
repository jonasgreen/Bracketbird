package com.bracketbird.clientcore.gui;


import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.i18n.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class DatePanel extends HorizontalComponent implements ClickHandler {
    private TextBoxComponent textBox;
    private String dummyDate;
    private DatePicker datePicker;
    private DatePopUp popUp;
    private Image image;
    private DateTimeFormat dateFormat;

    public DatePanel(DateTimeFormat dateFormat) {
        super();
        this.dateFormat = dateFormat;
        this.dummyDate = dateFormat.getPattern().toLowerCase();
        init();
        addDomHandler(this, ClickEvent.getType());
    }

    public void init() {
        add(getTextBox(), new TextLayout(0, 4, 0, 0, "18px", "90px", Horizontal.LEFT, null).colorBaseDark().sizeSmall());
        add(getImage());
        setCellHorizontalAlignment(getImage(), HasHorizontalAlignment.ALIGN_RIGHT);
        setCellVerticalAlignment(getImage(), HasVerticalAlignment.ALIGN_MIDDLE);
    }


    public Image getImage() {
        if (image == null) {
            image = new Image("img/calendar_25.png");
            //image.setPixelSize(25, 25);
            StyleIt.add(image, Name.MARGIN, "0 0 0 4");
        }
        return image;
    }

    public DatePopUp getPopUp() {
        if (popUp == null) {
            popUp = new DatePopUp();
            StyleIt.add(popUp, Name.Z_INDEX, ZIndex.DATE_POPUP);
            popUp.add(getDatePicker());
            popUp.addCloseHandler(new CloseHandler<PopupPanel>() {
                public void onClose(CloseEvent<PopupPanel> popupPanelCloseEvent) {
                    getTextBox().setFocus(true);
                }
            });


        }
        return popUp;
    }

    public void clear(){
        getTextBox().setText(dummyDate);
    }

    public DatePicker getDatePicker() {
        if (datePicker == null) {
            datePicker = new DatePicker();

            datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
                public void onValueChange(ValueChangeEvent<Date> dateValueChangeEvent) {
                    setDate(dateValueChangeEvent.getValue());
                    getPopUp().hide();
                    textBox.setBackgroundColor(P.BACKGROUND_WHITE);
                }
            });

        }
        return datePicker;
    }

    public TextBoxComponent getTextBox() {
        if (textBox == null) {
            textBox = new TextBoxComponent();
            textBox.setText(dummyDate);
            textBox.addKeyDownHandler(new KeyDownHandler() {
                public void onKeyDown(KeyDownEvent event) {
                    if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER || event.getNativeKeyCode() == KeyCodes.KEY_TAB) {
                        getPopUp().hide();
                    }
                }
            });
            textBox.getTextBox().addValueChangeHandler(new ValueChangeHandler<String>() {
                public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
                    //ignore
                }
            });
        }
        return textBox;
    }

    public void onClick(ClickEvent event) {
        //if (validate()) {
            try {
                Date d = dateFormat.parse(getTextBox().getText());
                getDatePicker().setValue(d);
                getDatePicker().setCurrentMonth(d);
            }
            catch (Throwable t) {
                //ignore
            }
        //}
        getPopUp().showRelativeTo(getTextBox());
    }

    public void setDate(Date date) {
        getTextBox().setText(dateFormat.format(date));
    }

    public boolean isEmpty(){
        String textDate = getTextBox().getText();
        return textDate == null || textDate.equals("") || textDate.equals(dummyDate);
    }


    public Date getDate(){
        return dateFormat.parse(getTextBox().getText());
    }



    private boolean validate() {
        String date = "empty";
        String textDate = getTextBox().getText();
        if (textDate == null || textDate.equals("") || textDate.equals(dummyDate)) {
            return true;
        }
        try {
            if (textDate.length() != 10) {
                return false;
            }
            Date d = dateFormat.parse(textDate);
            date = dateFormat.format(d);
        }
        catch (Throwable t) {
            //ignore
        }
        return textDate.equals(date);
    }


    private void showDateError(String date) {
        DialogComponent d = new DialogComponent(false, true, DialogComponent.Response.OK);
        d.setText("Ugyldig dato");
        VerticalComponent vc = new VerticalComponent();
        vc.add(new LabelComponent(date + "  er en ugyldig dato."));
        vc.add(new LabelComponent("Gyldigt format: dd/mm/yyyy"));
        d.add(vc, null);
        d.show(new DialogComponent.DialogCallBack() {
            public void onClose(DialogComponent.Response r) {
                getTextBox().setFocus(true);
                getTextBox().setBackgroundColor(P.BACKGROUND_RED);
            }
        });
    }

    public String getDummyDate() {
        return dummyDate;
    }

    public DateTimeFormat getDateFormat() {
        return dateFormat;
    }
}
