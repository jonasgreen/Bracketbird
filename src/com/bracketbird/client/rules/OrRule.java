package com.bracketbird.client.rules;


public class OrRule<T> extends Rule<T> {

    private Rule<T> ruleOne;
    private Rule<T> ruleTwo;

    public OrRule(Rule<T> ruleOne, Rule<T> ruleTwo) {
        this.ruleOne = ruleOne;
        this.ruleTwo = ruleTwo;
    }


    @Override
    public boolean isSatisfiedBy(T t) {
        return ruleOne.isSatisfiedBy(t) || ruleTwo.isSatisfiedBy(t);
    }

    @Override
    public String whyNotSatisfied(T t) {
        return ruleOne.whyNotSatisfied(t) + " or "+ ruleTwo.whyNotSatisfied(t);
    }

    public Rule<T> getRuleOne() {
        return ruleOne;
    }

    public Rule<T> getRuleTwo() {
        return ruleTwo;
    }
}