package com.bracketbird.client;

public class ErrorConsole extends Console{

    private static ErrorConsole instance;

    private ErrorConsole() {
    }

    public static ErrorConsole get() {
        if (instance == null) {
            instance = new ErrorConsole();
        }
        return instance;
    }


    public void write(String msg){
        writeTo(msg);
    }


    private static native void writeTo(String text)/*-{
        console.error(text);
    }-*/;

}
