package com.bracketbird.client.util;

/**
 *
 */
public class KeyUtil {



    public static int TAB = 9;
    public static int RIGHT_ARROW = 39;
    public static int LEFT_ARROW = 37;
    public static int DOWN_ARROW = 40;
    public static int UP_ARROW = 38;
    public static int BACKSPACE = 8;
    public static int DELETE = 46;
    public static int LINE_SEP = 109;


    public static int ENTER = 13;
    public static int SPACE = 32;

    public static boolean isTab(int keyCode) {
        return keyCode == TAB;
    }

    public static boolean isEnter(int keyCode) {
        return keyCode == ENTER;
    }

    public static boolean isLeftArrow(int keyCode) {
        return keyCode == LEFT_ARROW;
    }

    public static boolean isRightArrow(int keyCode) {
        return keyCode == RIGHT_ARROW;
    }

    public static boolean isDownArrow(int keyCode) {
        return keyCode == DOWN_ARROW;
    }

    public static boolean isUpArrow(int keyCode) {
        return keyCode == UP_ARROW;
    }

    public static boolean isComma(int keyCode){
        return keyCode == 188;
    }

    public static boolean isSepLine(int keyCode){
        return keyCode == 109;
    }


    public static boolean isSpace(int keyCode){
        return keyCode == SPACE;
    }

    public static boolean isDigit(int keyCode){
        return 48 <= keyCode && keyCode <= 57;
    }


    public static boolean isDelete(int keyCode){
        return keyCode == DELETE;
    }

    public static boolean isBackSpace(int keyCode){
        return keyCode == BACKSPACE;
    }

    public static boolean isOneOf(int keyCode, int ... keys){
        for (int key : keys) {
            if(key == keyCode){
                return true;
            }
        }
        return false;
    }

    public static boolean isArrow(int key) {
        return key == UP_ARROW || key == DOWN_ARROW || key == LEFT_ARROW || key == RIGHT_ARROW;
    }
}
