package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public abstract class GuiComponent extends Composite implements RequiresResize, ProvidesResize {

    private java.util.Map<Integer, DataContainer> dataContainerChildren = new TreeMap<Integer, DataContainer>();

    private P backGroundColor = P.BACKGROUND_WHITE;
    private Focusable nextFocus = null;
    private Layout17 layout = null;


    public GuiComponent() {
        super();

    }

    


    public void onResize() {
        if (getWidget() instanceof RequiresResize) {
            ((RequiresResize) getWidget()).onResize();
        }
    }

    public void setMargin(String s) {
        StyleIt.add(this, Name.MARGIN, s);
    }

    public void setNextFocusable(Focusable nextFocus) {
        this.nextFocus = nextFocus;
    }

    public void tabFocus() {
        if (nextFocus != null) {
            nextFocus.setFocus(true);
        }
    }


    public void setMargin(int top, int right, int bottom, int left) {
        StringBuffer sb = new StringBuffer();
        sb.append(top).append("px ");
        sb.append(right).append("px ");
        sb.append(bottom).append("px ");
        sb.append(left).append("px");
        setMargin(sb.toString());
    }


    public void setStyle(Css... css) {
        for (Css cs : css) {
            StyleIt.add(this, cs);
        }
    }

    public void setStyle(P... ps) {
        for (P p : ps) {
            StyleIt.add(this, p);
        }
    }


    public void setStyle(Name name, String value) {
        StyleIt.add(this, name, value);
    }

    public void setBackgroundColor(P color) {
        this.backGroundColor = color;
        StyleIt.add(this, Name.BACKGROUND, color.getValue());
    }

    public void setWidth(String width) {
        Widget parent = getParent();
        if (parent == null) {
            System.out.println("Error not able to set Width");
            //ApplicationController.getInstance().error(getClass().getName() + " Not able to setWidth on component before it is added to parent.");
        }
        else if ((parent instanceof CellPanel)) {
            ((CellPanel) parent).setCellWidth(this, width);
        }

        super.setWidth(width);

    }


    public void setHeight(String height) {

        Widget parent = getParent();
        if (parent == null) {
            System.out.println("Not able to set heigth");
            //ApplicationController.getInstance().error(getClass().getName() + " Not able to setHeight on component before it is added to parent.");
        }

        else if ((parent instanceof CellPanel)) {
            ((CellPanel) parent).setCellHeight(this, height);
        }

        super.setHeight(height);

    }


    public void layout(Layout17 l) {
        layout = l;
        if (l == null) {
            return;
        }

        align(l.getAlignH(), l.getAlignV(), l.getTopMargin(), l.getRightMargin(), l.getBottomMargin(), l.getLeftMargin());
        if (l.getHeight() != null) {
            setHeight(l.getHeight());
        }
        if (l.getWidth() != null) {
            setWidth(l.getWidth());
        }
        for (P p : l.getProperties()) {
            setStyle(p);
        }
    }


    public void align(Horizontal alignH, Vertical alignV, int top, int right, int bottom, int left) {
        Widget parent = getParent();

        if (parent == null) {
            return;
        }

        if ((parent instanceof CellPanel)) {
            if (alignH != null) {
                ((CellPanel) parent).setCellHorizontalAlignment(this, alignH.getAlignment());
            }
            if (alignV != null) {
                ((CellPanel) parent).setCellVerticalAlignment(this, alignV.getAlignment());
            }
            setMargin(top, right, bottom, left);
        }
        else {
            String mTop = String.valueOf(top) + "px";
            String mRight = (alignH == Horizontal.LEFT || alignH == Horizontal.CENTER) ? "auto" : String.valueOf(right) + "px";
            String mBottom = String.valueOf(bottom) + "px";
            String mLeft = (alignH == Horizontal.RIGHT || alignH == Horizontal.CENTER) ? "auto" : String.valueOf(left) + "px";

            setStyle(Name.MARGIN_TOP, mTop);
            setStyle(Name.MARGIN_RIGHT, mRight);
            setStyle(Name.MARGIN_Bottom, mBottom);
            setStyle(Name.MARGIN_LEFT, mLeft);
        }
        if (alignV != null) {
            setStyle(Name.VERTICAL_ALIGN, alignV.getAlignment().getVerticalAlignString());
        }

    }

    public P getBackGroundColor() {
        return backGroundColor;
    }


    //ELEMENT LAYOUT

    public void layout(Element e, Layout17 l) {
        if (e == null || l == null) {
            return;
        }
        align(e, l.getAlignH(), l.getAlignV(), l.getTopMargin(), l.getRightMargin(), l.getBottomMargin(), l.getLeftMargin());
        if (l.getHeight() != null) {
            StyleIt.add(e, Name.HEIGHT, l.getHeight());
        }
        if (l.getWidth() != null) {
            StyleIt.add(e, Name.WIDTH, l.getWidth());
        }
        for (P p : l.getProperties()) {
            StyleIt.add(e, p);
        }
    }

    public void align(Element e, Horizontal alignH, Vertical alignV, int top, int right, int bottom, int left) {
        String mTop = String.valueOf(top) + "px";
        String mRight = (alignH == Horizontal.LEFT || alignH == Horizontal.CENTER) ? "auto" : String.valueOf(right) + "px";
        String mBottom = String.valueOf(bottom) + "px";
        String mLeft = (alignH == Horizontal.RIGHT || alignH == Horizontal.CENTER) ? "auto" : String.valueOf(left) + "px";

        StyleIt.add(e, Name.MARGIN_TOP, mTop);
        StyleIt.add(e, Name.MARGIN_RIGHT, mRight);
        StyleIt.add(e, Name.MARGIN_Bottom, mBottom);
        StyleIt.add(e, Name.MARGIN_LEFT, mLeft);

        if (alignV != null) {
            StyleIt.add(e, Name.VERTICAL_ALIGN, alignV.getAlignment().getVerticalAlignString());
        }

    }

    public GuiComponent paddingTop(int size) {
        StyleIt.add(this, Name.PADDING_TOP, size + "px");
        return this;
    }

    public GuiComponent paddingLeft(int size) {
        StyleIt.add(this, Name.PADDING_LEFT, size + "px");
        return this;
    }


    public GuiComponent paddingright(int size) {
        StyleIt.add(this, Name.PADDING_RIGHT, size + "px");
        return this;
    }

    public GuiComponent paddingBottom(int size) {
        StyleIt.add(this, Name.PADDING_Bottom, size + "px");
        return this;
    }

    public GuiComponent padding(int top, int right, int bottom, int left) {
        StyleIt.add(this, Name.PADDING, top + "px " + right + "px " + bottom + "px " + left + "px");
        return this;

    }

    public GuiComponent padding(int size) {
        StyleIt.add(this, Name.PADDING, size + "px");
        return this;
    }


    public void add(DataContainer m) {
        dataContainerChildren.put(dataContainerChildren.size() + 1, m);
    }

    public Collection<DataContainer> getDataContainerChildren() {
        return dataContainerChildren.values();
    }

    public void clearDataContainerChildren() {
        for (DataContainer dc : dataContainerChildren.values()) {
            dc.clear();
        }
    }

    public Layout17 getLayout() {
        return layout;
    }

    public void setLayout(Layout17 layout) {
        layout(layout);
    }

    protected <H extends EventHandler> HandlerRegistration addADomHandler(H handler, DomEvent.Type<H> type) {
        return addDomHandler(handler, type);
    }

    public void setFocus(boolean focus){

    }

}
