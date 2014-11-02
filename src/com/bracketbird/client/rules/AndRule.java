package com.bracketbird.client.rules;


public class AndRule<T> extends Rule<T> {

    private Rule<T> ruleOne;
    private Rule<T> ruleTwo;


    public AndRule(Rule<T> ruleOne, Rule<T> ruleTwo) {
        this.ruleOne = ruleOne;
        this.ruleTwo = ruleTwo;
    }


    @Override
    public boolean isSatisfiedBy(T t) {
        return ruleOne.isSatisfiedBy(t) && ruleTwo.isSatisfiedBy(t);
    }

    @Override
    public String whyNotSatisfied(T t) {
        if(!ruleOne.isSatisfiedBy(t)){
            return ruleOne.whyNotSatisfied(t);
        }
        return ruleTwo.whyNotSatisfied(t);
    }

    public Rule<T> getRuleOne() {
        return ruleOne;
    }

    public Rule<T> getRuleTwo() {
        return ruleTwo;
    }
}
