package com.bracketbird.server;

import javax.servlet.ServletContext;

/**
 *
 */
public class Logger {
    private static ServletContext context;

    public static void init(ServletContext sc) {
        context = sc;
    }

    public static void log(String text) {
        context.log(text);
    }


    public static void log(String text, Throwable t) {
        context.log(text, t);
    }
}
