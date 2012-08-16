package com.bracketbird.clientcore.validation;
import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public class StringValidator extends Validator {


    private static String ERROR_MSG = "må ikke være længere end " + REPLACE_ITEM + " karakterer";

    private int maxLength = 0;

    public StringValidator(int maxLength) {
        this.maxLength = maxLength;
    }

    public String validate(ValueContainer vc) {
        String value = (String) vc.getValue();
        if (value != null) {
            if (value.length() > maxLength) {
                return format(ERROR_MSG, maxLength);
            }
        }
        return null;
    }


    public int getMaxLength() {
        return maxLength;
    }
}
