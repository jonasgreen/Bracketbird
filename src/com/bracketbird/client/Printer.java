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


    abstract void executePrintException(Throwable t);
    abstract void executePrintException(Throwable t, Object ... objs);
    abstract void executePrintln(Object obj);
    abstract void executePrintln(Object ... objs);



    public static void println(Object obj) {
        get().executePrintln(obj);
    }

    public static void println(Object ... objs) {
        get().executePrintln(objs);
    }

    public static void printException(Throwable t) {
        get().executePrintException(t);
    }

    public static void printException(Throwable t, Object ... objs) {
        get().executePrintException(t, objs);
    }

}
