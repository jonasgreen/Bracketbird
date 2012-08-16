package com.bracketbird.clientcore.style;

import java.util.*;

/**
 *
 */
public class Css {

    private Map<Name, P> properties = new HashMap<Name, P>();

    //properties should overwrite properties in extended styles.
    Css(Css... styles) {
        for (Css style : styles) {
            addProperties(style);
        }
    }

    public Css(P ... ps){
        for (P p : ps) {
            properties.put(p.getName(), p);
        }
    }

    public Css(Name name, String value) {
        properties.put(name, new P(name, value == null ? "" : value));
    }

    public Collection<P> getProperties() {
        return properties.values();
    }

    private void addProperty(P p){
        properties.put(p.getName(), p);
    }

    private void addProperties(Css style) {
        for (P p : style.getProperties()) {
            properties.put(p.getName(), p);
        }
    }
    public Css and(Name n, String value){
        Css newStyle = new Css(this);
        newStyle.addProperty(new P(n, value));
        return newStyle;
    }

    public Css and(P p){
        Css newStyle = new Css(this);
        newStyle.addProperty(p);
        return newStyle;
    }

    public Css and(Css style) {
        Css newStyle = new Css(this);
        newStyle.addProperties(style);
        return newStyle;

    }

    public String getValue(Name name) {
        return properties.get(name).getValue();
    }


    @Override
    public String toString() {
        return "CssStyle{" +
                "properties=" + (properties == null ? null : Arrays.asList(properties)) +
                '}';
    }
}

