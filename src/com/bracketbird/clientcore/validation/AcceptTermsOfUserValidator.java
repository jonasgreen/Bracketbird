package com.bracketbird.clientcore.validation;


import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class AcceptTermsOfUserValidator extends Validator {


    private static String ERROR_MSG = "is not accepted";

    public String validate(ValueContainer vc) {
        Boolean value = (Boolean) vc.getValue();
        if (value) {
            return null;
        }
        return ERROR_MSG;
    }


}