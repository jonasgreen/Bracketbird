package com.bracketbird.clientcore.validation;

import com.google.gwt.i18n.client.*;
import com.bracketbird.clientcore.gui.*;

import java.util.*;

/**
 *
 */
public class DateLargerOrEqualsOtherDateValidator extends Validator{

    private static String ERROR_MSG = "må ikke være mindre end "+REPLACE_ITEM;
    private DateContainer dc;

    public DateLargerOrEqualsOtherDateValidator(DateContainer dc) {
        this.dc = dc;
    }

    public String validate(ValueContainer vc) {
        if (vc.isEmpty()) {
            return null;
        }
        String textDate = ((DateContainer)vc).getStringValue();
        DateTimeFormat dateFormat = ((DateContainer)vc).getDateFormat();
        Date d = dateFormat.parse(textDate);

        textDate = dc.getStringValue();
        Date otherDate = dateFormat.parse(textDate);


        if(d.getTime() < otherDate.getTime()){
            return format(ERROR_MSG, dc.getName());
        }
        return null;
    }
}