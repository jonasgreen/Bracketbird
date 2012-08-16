package com.bracketbird.clientcore.validation;

import com.google.gwt.i18n.client.*;
import com.bracketbird.clientcore.gui.*;

import java.util.*;

/**
 *
 */
public class DateLargerOrEqualsTodayValidator extends Validator{

    private static String ERROR_MSG = "må ikke være mindre end dags dato";


    public String validate(ValueContainer vc) {
        if (vc.isEmpty()) {
            return null;
        }
        String textDate = ((DateContainer)vc).getStringValue();
        DateTimeFormat dateFormat = ((DateContainer)vc).getDateFormat();
        Date d = dateFormat.parse(textDate);
        Date today = dateFormat.parse(dateFormat.format(new Date()));

        if(d.getTime() < today.getTime()){
            return ERROR_MSG;
        }
        return null;
    }
}