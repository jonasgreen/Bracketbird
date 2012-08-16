package com.bracketbird.clientcore.validation;
import com.bracketbird.clientcore.gui.*;
/**
 *
 */
public class StringMinValidator extends Validator {
    private static String ERROR_MSG = "has to be at least " + REPLACE_ITEM + " characters long";

    private int minLength = 0;

    public StringMinValidator(int minLength) {
        this.minLength = minLength;
    }

    public String validate(ValueContainer vc) {
        String value = (String) vc.getValue();
        if (value == null || value.length() < minLength) {
            return format(ERROR_MSG, minLength);
        }
        return null;
    }


}
