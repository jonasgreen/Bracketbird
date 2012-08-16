package com.bracketbird.clientcore.validation;

import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public abstract class Validator {

    public static String REPLACE_ITEM = "#";



    public static DateValidator DATE_FORMAT = new DateValidator();


    public static MandatoryValidator MANDATORY = new MandatoryValidator();

    public static CvrValidator CVR_VALIDATOR = new CvrValidator();

    public static DoubleValidator DOUBLE_VALIDATOR = new DoubleValidator();
    public static IntegerValidator INTEGER_VALIDATOR = new IntegerValidator();

    public static StringValidator STRING_MAX_5 = new StringValidator(5);
    public static StringMinValidator STRING_MIN_6 = new StringMinValidator(6);

    public static AcceptTermsOfUserValidator ACCEPT_TERMS_OF_USE = new AcceptTermsOfUserValidator();
    public static SportsTypeValidator SPORTS_TYPE_VALIDATOR = new SportsTypeValidator();

    public static TodayOrAfterValidator TODAY_OR_AFTER = new TodayOrAfterValidator();

    public abstract String validate(ValueContainer vc);

    public String format(String msg, Object ... values){
        String retMsg = msg;
        for (Object value : values) {
            
            retMsg = retMsg.replaceFirst(REPLACE_ITEM, String.valueOf(value));
        }
        return retMsg;
    }

}
