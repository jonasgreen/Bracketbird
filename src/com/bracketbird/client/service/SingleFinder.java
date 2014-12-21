package com.bracketbird.client.service;

import java.io.Serializable;
import java.util.Map;


/**
 *
 */
public abstract class SingleFinder extends Finder implements Serializable {
    private static final long serialVersionUID = 2569725778558505809L;

    public enum Operator {
        LESS_THAN("<"),
        LESS_THAN_OR_EQUAL_TO("<="),
        EQUAL_TO("=="),
        GREATER_THAN(">"),
        GREATER_THAN_OR_EQUAL(">="),
        NOT_EQUAL_TO("!=");

        private final String operator;

        private Operator(String operator) {
            this.operator = operator;
        }

        public String getOperator() {
            return operator;
        }
    }


    private String filter;
    private String declaredParameter;
    private String parameterName;


    public SingleFinder() {
        super();
    }

    public SingleFinder(String paramClass, String colNam) {
        this(paramClass, colNam, Operator.EQUAL_TO);
    }

    public SingleFinder(String paramClass, String colNam, Operator operator) {
        super();
        this.parameterName = colNam + "Param";
        this.filter = colNam + " " + operator.getOperator() + " " + parameterName;
        this.declaredParameter = paramClass + " " + parameterName;
    }

    public String getFilter() {
        return filter;
    }

    public abstract Object getValue();

    public void buildValueMap(Map<String, Object> map) {
        map.put(parameterName, getValue());
    }

    public void buildParameterList(StringBuffer sb) {
        if (sb.length() != 0) {
            sb.append(", ");
        }
        sb.append(declaredParameter);
    }


}
