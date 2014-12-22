package com.bracketbird.client;

public class GuiPrinter extends Printer {

    private Console console = NormalConsole.get();

    @Override
    void executePrintException(Throwable t) {
        console = ErrorConsole.get();
        executePrintln(makeSimpleStackTrace(t));
        console = NormalConsole.get();
    }

    @Override
    void executePrintException(Throwable t, Object... objs) {
        console = ErrorConsole.get();
        executePrintln(objs);
        executePrintln(makeSimpleStackTrace(t));
        console = NormalConsole.get();
    }

    @Override
    void executePrintln(Object obj) {
        //do not print in production
        if (Environment.get().isLocal()) {
            console.write(String.valueOf(obj));
        }
    }

    @Override
    void executePrintln(Object... objs) {
        //do not print in production
        if (Environment.get().isLocal()) {
            StringBuilder sb = new StringBuilder();
            for (Object obj : objs) {
                sb.append(obj);
            }
            console.write(sb.toString());
        }
    }


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


}
