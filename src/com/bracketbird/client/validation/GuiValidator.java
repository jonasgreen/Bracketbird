package com.bracketbird.client.validation;

import com.bracketbird.client.pages.settings.SettingsValidationException;
import com.bracketbird.client.rules.IntegerRule;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class GuiValidator {

    private Label labelUsed;
    private Widget valueWidgetsUsed;

    public Integer toInteger(TextBox hasValue, Label label, boolean mandatory, IntegerRule rule) throws SettingsValidationException {
        String valueStr = hasValue.getValue();
        if (isEmpty(valueStr)) {
            if (mandatory) {
                styleError(label, hasValue);
                throw new SettingsValidationException(label, hasValue, "Value is missing");
            }
            else {
                return null;
            }
        }
        else {
            try {
                Integer value = Integer.valueOf(valueStr);
                if (!rule.isSatisfiedBy(value)) {
                    styleError(label, hasValue);
                    throw new SettingsValidationException(label, hasValue, rule.whyNotSatisfied(value));
                }
                return value;
            }
            catch (NumberFormatException e) {
                styleError(label, hasValue);
                throw new SettingsValidationException(label, hasValue, "Value has to be a number");
            }
        }
    }

    public void removeErrorStyles(){
        if(labelUsed != null){
            labelUsed.removeStyleName("error_label");
        }
        if(valueWidgetsUsed != null){
            valueWidgetsUsed.removeStyleName("error_value");
        }
    }

    private void styleError(Label label, TextBox hasValue) {
        this.labelUsed = label;
        this.valueWidgetsUsed = hasValue;

        label.addStyleName("error_label");
        hasValue.addStyleName("error_value");
    }


    private boolean isEmpty(String value) {
        return value == null || value.equals("");
    }


}
