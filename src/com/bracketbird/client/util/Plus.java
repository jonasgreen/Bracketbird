package com.bracketbird.client.util;

public class Plus extends MathOperation{

    private static Plus instance;

    private Plus() {
    }

    public static Plus get() {
        if (instance == null) {
            instance = new Plus();
        }
        return instance;
    }


    public int operate(int val1, int val2){
        return val1 + val2;
    }
}
