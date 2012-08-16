package com.bracketbird.clientcore.validation;


import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.util.*;

/**
 *
 */
public class CvrValidator extends Validator {


    private static String ERROR_FORMAT_MSG = "skal bestå af 8 cifre";
    private static String ERROR_CVR_MSG = "er ikke gyldigt";


    public CvrValidator() {
    }

    public String validate(ValueContainer vc) {
        String value = (String) vc.getValue();
        Long cvrNr = null;
        if (value == null || value.length() != 8) {
            return ERROR_FORMAT_MSG;
        }
        try {
            Long.valueOf(value);
        }
        catch (Throwable e) {
            return ERROR_FORMAT_MSG;
        }

        if (!CvrNr.validate(value)) {
            return ERROR_CVR_MSG;
        }

        return null;
    }


}