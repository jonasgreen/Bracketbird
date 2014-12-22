package com.bracketbird.client;

public class ServerPrinter extends Printer {
    @Override
    void executePrintException(Throwable t) {
        t.printStackTrace();
    }

    @Override
    void executePrintException(Throwable t, Object... objs) {
        executePrintln(objs);
        executePrintException(t);
    }

    @Override
    void executePrintln(Object obj) {
        System.out.println(obj);
    }

    @Override
    void executePrintln(Object... objs) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : objs) {
            sb.append(obj);
        }
        executePrintln(sb);
    }
}
