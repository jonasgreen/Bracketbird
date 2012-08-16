package com.bracketbird.clientcore.appcontrol;

/**
 *
 */
public class ApplicationException extends Exception{
    private static final long serialVersionUID = -899240546609515918L;

    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }
}