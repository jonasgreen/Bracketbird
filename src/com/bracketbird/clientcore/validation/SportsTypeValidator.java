package com.bracketbird.clientcore.validation;
import com.bracketbird.clientcore.gui.*;
/**
 *
 */
public class SportsTypeValidator extends Validator{

    private static String ERROR_MSG = "is missing";


    public String validate(ValueContainer vc) {
        if (vc.isEmpty()) {
            return null;
        }
        try {
            Integer value = ((ListContainer<Integer>)vc).getValue();
            if(value == -1){
                return ERROR_MSG;
            }
            return null;

        }
        catch (Throwable t) {
            return ERROR_MSG;
        }
    }
}