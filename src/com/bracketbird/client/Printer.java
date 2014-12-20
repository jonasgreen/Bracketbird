package com.bracketbird.client;

import com.google.gwt.core.client.GWT;

public abstract class Printer {

    private static Printer printer;

    public static Printer get() {
        if (printer == null) {
            try {
                //should fail on client
                printer = GWT.isScript() ? new GuiPrinter() : new ServerPrinter();
            }
            catch (Exception e) {
                printer = new GuiPrinter();
            }
        }

        return printer;
    }

    protected abstract void executePrintln(Object obj);

    protected abstract void executePrintln(Object ... objs);


    public static void println(Object obj) {
        get().executePrintln(obj);
    }

    public static void println(Object ... objs) {
        get().executePrintln(objs);
    }


}
