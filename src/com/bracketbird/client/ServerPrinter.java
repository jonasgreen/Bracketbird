package com.bracketbird.client;

public class ServerPrinter extends Printer {
    @Override
    protected void executePrintln(Object obj) {
        System.out.println(obj);
    }

    @Override
    protected void executePrintln(Object... objs) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : objs) {
            sb.append(obj);
        }
        executePrintln(sb);
    }
}
