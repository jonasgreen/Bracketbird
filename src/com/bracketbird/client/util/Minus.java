package com.bracketbird.client.util;

public class Minus extends MathOperation{

    private static Minus instance;

    private Minus() {
    }

    public static Minus get() {
        if (instance == null) {
            instance = new Minus();
        }
        return instance;
    }


    public int operate(int val1, int val2){
        return val1 - val2;
    }
}
