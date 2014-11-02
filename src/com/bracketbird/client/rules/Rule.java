package com.bracketbird.client.rules;


public abstract class Rule<T> {
    public abstract boolean isSatisfiedBy(T t);

    protected Rule() {
    }

    public AndRule<T> and(Rule<T> rule){
        return new AndRule<T>(this, rule);
    }

    public OrRule<T> or(Rule<T> rule){
        return new OrRule<T>(this, rule);
    }

    public NotRule<T> not(){
        return new NotRule<T>(this);
    }

    public abstract String whyNotSatisfied(T t);
}
