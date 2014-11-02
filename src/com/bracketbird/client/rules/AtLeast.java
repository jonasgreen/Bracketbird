package com.bracketbird.client.rules;


public class AtLeast extends IntegerRule {

    private int checkValue;

    public AtLeast(int value) {
        this.checkValue = value;
    }


    @Override
    public boolean isSatisfiedBy(Integer value) {
        return value >= checkValue;
    }

    @Override
    public String whyNotSatisfied(Integer value) {
        return value+" is to small - has to be at least "+checkValue;
    }

}
