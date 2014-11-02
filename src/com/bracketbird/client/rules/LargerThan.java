package com.bracketbird.client.rules;


public class LargerThan extends IntegerRule {

    private int checkValue;

    public LargerThan(int value) {
        this.checkValue = value;
    }


    @Override
    public boolean isSatisfiedBy(Integer value) {
        return value > checkValue;
    }

    @Override
    public String whyNotSatisfied(Integer value) {
        return value+" is to small - has to be larger than "+checkValue;
    }

}
