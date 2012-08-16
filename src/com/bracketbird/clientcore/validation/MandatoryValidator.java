package com.bracketbird.clientcore.validation;

import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class MandatoryValidator extends Validator {

    private String ERROR_MSG = "is missing";

    public String validate(ValueContainer vc) {
        return vc.isEmpty() ? ERROR_MSG : null;
    }
}
