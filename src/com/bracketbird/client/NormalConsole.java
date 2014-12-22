package com.bracketbird.client;

public class NormalConsole extends Console{

    private static NormalConsole instance;

    private NormalConsole() {
    }

    public static NormalConsole get() {
        if (instance == null) {
            instance = new NormalConsole();
        }
        return instance;
    }


    public void write(String msg){
        writeTo(msg);
    }


    private static native void writeTo(String text)/*-{
        console.log(text);
    }-*/;

}
