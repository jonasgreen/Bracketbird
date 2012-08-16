package com.bracketbird.clientcore.service;

import java.io.Serializable;
import java.util.Map;


/**
 *
 */
public abstract class Finder implements Serializable {

    private String ordering;

    public Finder() {
    }

    public String getOrdering() {
        return ordering;
    }

    public void setOrdering(String ordering) {
        this.ordering = ordering;
    }

    public Finder and(Finder f){
        return new AndFinder(this, f);
    }

    public abstract String getFilter();

    public abstract void buildValueMap(Map<String, Object> map);

    public abstract void buildParameterList(StringBuffer sb);


}