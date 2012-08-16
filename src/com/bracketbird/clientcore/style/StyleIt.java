package com.bracketbird.clientcore.style;

import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;


/**
 *
 */
public class StyleIt {


    public static void add(Widget w, Layout17 l) {
        for (P p : l.getProperties()) {
            add(w, p);
        }
    }

    public static void add(Widget w, Css css) {
        for (P p : css.getProperties()) {
            add(w, p);
        }
    }

    public static void add(Widget w, P p) {
        add(w, p.getName(), p.getValue());
    }

    public static void add(Widget w, Name name, String value) {
        w.getElement().getStyle().setProperty(name.getName(), correctFontSize(name, value));

    }


    //ELEMENT

    public static void add(Element e, Layout17 l) {
        for (P p : l.getProperties()) {
            add(e, p);
        }
    }

    public static void add(Element w, Css css) {
        for (P p : css.getProperties()) {
            add(w, p);
        }
    }

    public static void add(Element w, P p) {
        add(w, p.getName(), p.getValue());
    }

    public static void add(Element w, Name name, String value) {
        w.getStyle().setProperty(name.getName(), correctFontSize(name, value));
    }


    //DOM ELEMENTS
    public static void add(com.google.gwt.dom.client.Element w, Layout17 l) {
        for (P p : l.getProperties()) {
            add(w, p);
        }
    }

    public static void add(com.google.gwt.dom.client.Element w, Css css) {
        for (P p : css.getProperties()) {
            add(w, p);
        }
    }

    public static void add(com.google.gwt.dom.client.Element w, P p) {
        add(w, p.getName(), p.getValue());
    }

    public static void add(com.google.gwt.dom.client.Element w, Name name, String value) {
        w.getStyle().setProperty(name.getName(), correctFontSize(name, value));
    }


    public static P correctFontSize(P p) {
        String value = correctFontSize(p.getName(), p.getValue());
        return new P(p.getName(), value);
    }

    public static String correctFontSize(Name name, String value) {
            return value;

    }


    public static void setTransitionsTime(Element elm, double time){
        String value =  "all "+time+"s ease";

        elm.getStyle().setProperty("transition", value);
        elm.getStyle().setProperty("MozTransition", value);
        elm.getStyle().setProperty("WebkitTransition", value);
        elm.getStyle().setProperty("OTransition", value);

    }
}
