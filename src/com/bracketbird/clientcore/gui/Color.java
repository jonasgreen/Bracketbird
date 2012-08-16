package com.bracketbird.clientcore.gui;


import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class Color {

    private static ColorScheme scheme = new DefaultColorScheme();

    private Color() {
    }


    public static void setScheme(ColorScheme sc) {
        scheme = sc;
    }

    public static P backgroundBase() {
        return scheme.backgroundBase();
    }

    public static P backgroundBaseLight() {
        return scheme.backgroundBaseLight();
    }

    public static P backgroundBaseDark() {
        return scheme.backgroundBaseDark();
    }

    public static P textBase() {
        return scheme.textBase();
    }

    public static P textBaseLight() {
        return scheme.textBaseLight();
    }

    public static P textBaseDark() {
        return scheme.textBaseDark();
    }

    public static P backgroundCompl() {
        return scheme.backgroundCompl();
    }

    public static P backgroundComplLight() {
        return scheme.backgroundComplLight();
    }

    public static P backgroundComplDark() {
        return scheme.backgroundComplDark();
    }

    public static P textCompl() {
        return scheme.textCompl();
    }

    public static P textComplLight() {
        return scheme.textComplLight();
    }

    public static P textComplDark() {
        return scheme.textComplDark();
    }
}
