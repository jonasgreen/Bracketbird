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


    public void executePrintln(Exception e){
        executePrintln(makeSimpleStackTrace(e));
    }

    protected abstract void executePrintln(Object obj);

    protected abstract void executePrintln(Object ... objs);


    /**
     * Builds a simple stack trace (including chained exceptions) with just the method names.
     */
    private String makeSimpleStackTrace(Throwable first) {
        StringBuilder out = new StringBuilder();
        for (Throwable t = first; t != null; t = t.getCause()) {
            if (t == first) {
                out.append(t.toString() + "\n");
            } else {
                out.append("Caused by: " + t.toString() + "\n");
            }
            for (StackTraceElement element : t.getStackTrace()) {
                out.append("  at " + element.getMethodName() + "\n"); // only the method name is meaningful.
            }
        }
        return out.toString();
    }


    public static void println(Object obj) {
        get().executePrintln(obj);
    }

    public static void println(Object ... objs) {
        get().executePrintln(objs);
    }

    public static void println(Throwable t) {
        get().executePrintln(t);
    }

}
