package com.bracketbird.clientcore.validation;

import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class IntegerValidator extends Validator{

    private static String ERROR_MSG = "have an illegal format. Has to be an Integer like 1,2,3 etc.";


    public String validate(ValueContainer vc) {
        if (vc.isEmpty()) {
            return null;
        }
        try {
            ((IntegerContainer)vc).getValue();
            return null;
        }
        catch (Throwable t) {
            return ERROR_MSG;
        }

    }
}