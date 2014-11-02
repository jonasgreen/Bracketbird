package com.bracketbird.client.validation;

import com.bracketbird.client.rules.IntegerRule;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;

public class Validator {


    public final IntegerValidator integers = new IntegerValidator(this);


    public Integer toInteger(Label label, HasValue<String> value, boolean mandatory, IntegerRule rule){
        return null;
    }




}
