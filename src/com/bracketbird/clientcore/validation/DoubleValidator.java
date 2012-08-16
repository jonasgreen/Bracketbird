package com.bracketbird.clientcore.validation;

import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class DoubleValidator extends Validator{

    private static String ERROR_MSG = "har et ugyldigt format. Skal være et tal som fx 3 eller 3,4";


    public String validate(ValueContainer vc) {
        if (vc.isEmpty()) {
            return null;
        }
        try {
            ((DoubleContainer)vc).getValue();            
            return null;
        }
        catch (Throwable t) {
            return ERROR_MSG;
        }

    }
}