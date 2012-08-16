package com.bracketbird.clientcore.validation;


import com.google.gwt.i18n.client.*;
import com.bracketbird.clientcore.gui.*;

import java.util.*;

/**
 *
 */
public class DateValidator extends Validator{

    private static String ERROR_MSG = "has an illegal format. Valid format is: " + REPLACE_ITEM;


    public String validate(ValueContainer vc) {
        if (vc.isEmpty()) {
            return null;
        }
        String textDate = ((DateContainer)vc).getStringValue();
        String checkDate = "Empty_xxx";
        DateTimeFormat dateFormat = ((DateContainer)vc).getDateFormat();
        try {
            Date d = dateFormat.parse(textDate);
            checkDate = dateFormat.format(d);
        }
        catch (Throwable t) {
            //ignore
        }
        if(textDate.equals(checkDate)){
            return null;
        }
        return format(ERROR_MSG, dateFormat.getPattern().toLowerCase());
    }
}
