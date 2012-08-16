package com.bracketbird.clientcore.validation;

import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class PassDoesNotMatchExistingValidator extends Validator {
    private String existPass;

    public PassDoesNotMatchExistingValidator(String existingPass) {
        this.existPass = existingPass;
    }

    public String validate(ValueContainer vc) {
        String value = (String) vc.getValue();
        if (value != null && !value.equals(existPass)) {
            return "is not your password";
        }
        return null;
    }


}