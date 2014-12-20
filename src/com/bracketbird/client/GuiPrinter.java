package com.bracketbird.client;

public class GuiPrinter extends Printer {


    @Override
    protected void executePrintln(Object obj) {
        //do not print in production
        if (Environment.get().isLocal()) {
            console(String.valueOf(obj));
        }
    }

    @Override
    protected void executePrintln(Object... objs) {
        //do not print in production
        if (Environment.get().isLocal()) {
            StringBuilder sb = new StringBuilder();
            for (Object obj : objs) {
                sb.append(obj);
            }
            console(sb.toString());
        }
    }


    public static native void console(String text)/*-{
        console.log(text);
    }-*/;

}
