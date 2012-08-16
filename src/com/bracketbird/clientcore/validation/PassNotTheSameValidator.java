package com.bracketbird.clientcore.validation;


import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class PassNotTheSameValidator extends Validator {
    private PasswordContainer pwContainer;

    public PassNotTheSameValidator(PasswordContainer container) {
        this.pwContainer = container;
    }

    public String validate(ValueContainer vc) {
        String value = (String) vc.getValue();
        if (value != null && !value.equals(pwContainer.getValue())) {
            return "does not match "+pwContainer.getName();
        }
        return null;
    }


}