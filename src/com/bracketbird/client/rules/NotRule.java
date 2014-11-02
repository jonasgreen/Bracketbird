package com.bracketbird.client.rules;


public class NotRule<T> extends Rule<T> {

    private Rule<T> rule;

    public NotRule(Rule<T> rule) {
        this.rule = rule;
    }

    @Override
    public boolean isSatisfiedBy(T t) {
        return !rule.isSatisfiedBy(t);
    }

    @Override
    public String whyNotSatisfied(T t) {
        return "[NOT] " + rule.whyNotSatisfied(t);
    }


}