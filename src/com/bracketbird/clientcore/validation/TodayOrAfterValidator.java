package com.bracketbird.clientcore.validation;

import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;

/**
 *
 */
public class TodayOrAfterValidator extends Validator{

    private static String ERROR_MSG = "has to be today or later.";


    public String validate(ValueContainer vc) {
        if (vc.isEmpty()) {
            return null;
        }
        Date d = ((DateContainer)vc).getValue();
        if(DateUtil.isDayAfter(DateUtil.today(), d)){
            return format(ERROR_MSG);

        }
        return null;

    }
}
