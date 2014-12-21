package com.bracketbird.client.service;

import java.io.Serializable;
import java.util.Map;


/**
 *
 */
public class AndFinder extends Finder implements Serializable {
    private static final long serialVersionUID = -2246744522887333659L;

    private Finder f1;
    private Finder f2;

    public AndFinder() {
        super();
    }

    public AndFinder(Finder f1, Finder f2) {
        super();
        this.f1 = f1;
        this.f2 = f2;
    }

    public String getFilter(){
        return f1.getFilter() + " && "+ f2.getFilter();
    }

    public void buildValueMap(Map<String, Object> map) {
        f1.buildValueMap(map);
        f2.buildValueMap(map);
    }

    public void buildParameterList(StringBuffer sb) {
        f1.buildParameterList(sb);
        f2.buildParameterList(sb);
    }

    public Finder getF1() {
        return f1;
    }

    public void setF1(Finder f1) {
        this.f1 = f1;
    }

    public Finder getF2() {
        return f2;
    }

    public void setF2(Finder f2) {
        this.f2 = f2;
    }
}