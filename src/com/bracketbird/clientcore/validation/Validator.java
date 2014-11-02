package com.bracketbird.clientcore.validation;

import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public abstract class Validator {

    public static String REPLACE_ITEM = "#";

    public static DateValidator DATE_FORMAT = new DateValidator();

    public static MandatoryValidator MANDATORY = new MandatoryValidator();

    public static DoubleValidator DOUBLE_VALIDATOR = new DoubleValidator();
    public static IntegerValidator INTEGER_VALIDATOR = new IntegerValidator();

    
    public abstract String validate(ValueContainer vc);

    public String format(String msg, Object ... values){
        String retMsg = msg;
        for (Object value : values) {
            
            retMsg = retMsg.replaceFirst(REPLACE_ITEM, String.valueOf(value));
        }
        return retMsg;
    }

}
