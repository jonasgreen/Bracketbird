package com.bracketbird.clientcore.style;

import com.bracketbird.clientcore.gui.Color;

/**
 *
 */
public class TextLayout extends Layout17 {


    public static Css FONT_BASIS = new Css(
            P.X_SYSTEM_FONT_NONE,
            P.FONT_FAMILY_ARIAL_HELV_SANS,
            P.FONT_STRETCH_NORMAL,
            P.FONT_STYLE_NORNAL,
            P.FONT_VARIANT_NORMAL,
            P.FONT_WEIGHT_NORMAL,
            P.LINE_HEIGHT_1_25);

    public TextLayout() {
        super();
    }

    public TextLayout(Css css) {
        super(css);
    }

    public TextLayout(P p) {
        super(p);
    }

    public TextLayout(Horizontal alignH) {
        super(alignH);
    }

    public TextLayout(Vertical alignV) {
        super(alignV);
    }

    public TextLayout(String height, String width) {
        super(height, width);
    }

    public TextLayout(Horizontal alignH, Vertical alignV) {
        super(alignH, alignV);
    }

    public TextLayout(String height, String width, Horizontal alignH, Vertical alignV) {
        super(height, width, alignH, alignV);
    }

    public TextLayout(String height, String width, Horizontal alignH) {
        super(height, width, alignH);
    }

    public TextLayout(String height, String width, Vertical alignV) {
        super(height, width, alignV);
    }

    public TextLayout(int topMargin, int rightMargin, int bottomMargin, int leftMargin) {
        super(topMargin, rightMargin, bottomMargin, leftMargin);
    }

    public TextLayout(int topMargin, int rightMargin, int bottomMargin, int leftMargin, String height, String width) {
        super(topMargin, rightMargin, bottomMargin, leftMargin, height, width);
    }

    public TextLayout(int topMargin, int rightMargin, int bottomMargin, int leftMargin, Horizontal alignH) {
        super(topMargin, rightMargin, bottomMargin, leftMargin, alignH);
    }

    public TextLayout(int topMargin, int rightMargin, int bottomMargin, int leftMargin, Vertical alignV) {
        super(topMargin, rightMargin, bottomMargin, leftMargin, alignV);
    }


    public TextLayout(int topMargin, int rightMargin, int bottomMargin, int leftMargin, String height, String width, Horizontal alignH) {
        super(topMargin, rightMargin, bottomMargin, leftMargin, height, width, alignH);
    }


    public TextLayout(int topMargin, int rightMargin, int bottomMargin, int leftMargin, String height, String width, Vertical alignV) {
        super(topMargin, rightMargin, bottomMargin, leftMargin, height, width, alignV);
    }

    public TextLayout(int topMargin, int rightMargin, int bottomMargin, int leftMargin, Horizontal alignH, Vertical alignV) {
        super(topMargin, rightMargin, bottomMargin, leftMargin, alignH, alignV);
    }

    public TextLayout(int topMargin, int rightMargin, int bottomMargin, int leftMargin, String height, String width, Horizontal alignH, Vertical alignV) {
        super(topMargin, rightMargin, bottomMargin, leftMargin, height, width, alignH, alignV);
    }

    private void addCss(Css css) {
        for (P property : css.getProperties()) {
            getProperties().add(property);
        }
    }

    public TextLayout margin(int top, int right, int bottom, int left) {
        setTopMargin(top);
        setRightMargin(right);
        setBottomMargin(bottom);
        setLeftMargin(left);
        return this;
    }

    public TextLayout sizeTitle() {
        addCss(FONT_BASIS.and(P.FONT_SIZE_38PX));
        return this;
    }


    public TextLayout sizeEkstraGiga() {
        addCss(FONT_BASIS.and(P.FONT_SIZE_64PX));
        return this;
    }

    public TextLayout sizeGiga() {
        addCss(FONT_BASIS.and(P.FONT_SIZE_46PX));
        return this;
    }

    public TextLayout sizeH1() {
        addCss(FONT_BASIS.and(P.FONT_SIZE_28PX));
        return this;
    }


    public TextLayout sizeH2() {
        addCss(FONT_BASIS.and(P.FONT_SIZE_22PX));
        return this;
    }

    public TextLayout sizeH3() {
        addCss(FONT_BASIS.and(P.FONT_SIZE_18PX));
        return this;
    }


    //used with normal text - like help site.

    public TextLayout sizeNormal() {
        addCss(FONT_BASIS.and(P.FONT_SIZE_16PX));
        return this;
    }

    //used in Menu, labels, small buttons, shorter text messages and lines.

    public TextLayout sizeSmall() {
        addCss(FONT_BASIS.and(P.FONT_SIZE_14PX));
        return this;
    }

    //used for buttons in tables

    public TextLayout sizeEkstraEkstraSmall() {
        addCss(FONT_BASIS.and(P.FONT_SIZE_12PX));
        return this;
    }

    //used at top and bottom pages and popuphelp

    public TextLayout sizeEkstraSmall() {
        addCss(FONT_BASIS.and(P.FONT_SIZE_14PX));
        return this;
    }


    public TextLayout borderColor(P color) {
        getProperties().add(new P(Name.BORDER_COLOR, color.getValue()));
        return this;
    }

    public TextLayout border(int top, int right, int bottom, int left) {
        if (top != 0) {
            borderTop(top);
        }
        if (right != 0) {
            borderRight(right);
        }
        if (bottom != 0) {
            borderBottom(bottom);
        }
        if (left != 0) {
            borderLeft(left);
        }
        return this;
    }

    public TextLayout border(int size) {
        getProperties().add(P.BORDER_STYLE_SOLID);
        getProperties().add(new P(Name.BORDER_WIDTH, size + "px"));
        return this;
    }

    public TextLayout borderTop(int size) {
        getProperties().add(P.BORDER_TOP_SOLID);
        getProperties().add(new P(Name.BORDER_WIDTH, size + "px"));
        return this;
    }

    public TextLayout borderBottom(int size) {
        getProperties().add(P.BORDER_BOTTOM_SOLID);
        getProperties().add(new P(Name.BORDER_WIDTH, size + "px"));
        return this;
    }

    public TextLayout borderRight(int size) {
        getProperties().add(P.BORDER_RIGHT_SOLID);
        getProperties().add(new P(Name.BORDER_WIDTH, size + "px"));
        return this;
    }

    public TextLayout borderLeft(int size) {
        getProperties().add(P.BORDER_LEFT_SOLID);
        getProperties().add(new P(Name.BORDER_WIDTH, size + "px"));
        return this;
    }


    public TextLayout bold() {
        getProperties().add(P.FONT_WEIGHT_BOLD);
        return this;
    }


    public TextLayout normal() {
        getProperties().add(P.FONT_WEIGHT_NORMAL);
        return this;
    }

    public TextLayout italic() {
        getProperties().add(P.FONT_STYLE_ITALIC);
        return this;
    }

    public TextLayout alignRight() {
        getProperties().add(new P(Name.TEXT_ALIGN, "right"));
        return this;
    }

    public TextLayout alignLeft() {
        getProperties().add(new P(Name.TEXT_ALIGN, "left"));
        return this;
    }

    public TextLayout alignCenter() {
        getProperties().add(P.TEXT_ALIGN_CENTER);
        return this;
    }

    public TextLayout alignJystify() {
        getProperties().add(P.TEXT_ALIGN_JUSTIFY);
        return this;
    }

    public TextLayout paddingLeft(int size) {
        getProperties().add(new P(Name.PADDING_LEFT, size + "px"));
        return this;
    }

    public TextLayout paddingRight(int size) {
        getProperties().add(new P(Name.PADDING_RIGHT, size + "px"));
        return this;
    }

    public TextLayout paddingTop(int size) {
        getProperties().add(new P(Name.PADDING_TOP, size + "px"));
        return this;
    }

    public TextLayout horizontalCenter() {
        getProperties().add(new P(Name.MARGIN_LEFT, "auto"));
        getProperties().add(new P(Name.MARGIN_RIGHT, "auto"));
        return this;
    }


    public TextLayout paddingBottom(int size) {
        getProperties().add(new P(Name.PADDING_Bottom, size + "px"));
        return this;
    }

    public TextLayout padding(int top, int right, int bottom, int left) {
        getProperties().add(new P(Name.PADDING, top + "px " + right + "px " + bottom + "px " + left + "px"));
        return this;

    }

    public TextLayout padding(int size) {
        getProperties().add(new P(Name.PADDING, size + "px"));
        return this;
    }


    public TextLayout floatLeft() {
        getProperties().add(new P(Name.FLOAT, "left"));
        return this;
    }

    public TextLayout floatRight() {
        getProperties().add(new P(Name.FLOAT, "right"));
        return this;
    }

    public TextLayout noWrap() {
        getProperties().add(P.NO_WRAP);
        return this;
    }


    public TextLayout underline() {
        getProperties().add(P.TEXT_DECORATION_UNDERLINE);
        return this;
    }

    public TextLayout verticalAlignMiddel() {
        setAlignV(Vertical.MIDDLE);
        return this;
    }

    public TextLayout verticalAlignBottom() {
        setAlignV(Vertical.BOTTOM);
        return this;
    }

    public TextLayout verticalAlignTop() {
        setAlignV(Vertical.TOP);
        return this;
    }


    public TextLayout overflowHidden() {
        getProperties().add(new P(Name.OVERFLOW, "hidden"));
        return this;
    }

    public TextLayout opacityMiddle() {
        getProperties().add(new P(Name.OPACITY, "0.25"));
        getProperties().add(new P(Name.FILTER, "alpha(opacity=25)"));
        return this;
    }

    public TextLayout opacityNormal() {
        getProperties().add(new P(Name.OPACITY, "1.0"));
        getProperties().add(new P(Name.FILTER, "alpha(opacity=100)"));
        return this;
    }

    public TextLayout horizontalAlignLeft() {
        setAlignH(Horizontal.LEFT);
        return this;
    }

    public TextLayout horizontalAlignRight() {
        setAlignH(Horizontal.RIGHT);
        return this;
    }

    public TextLayout zIndex(String zindex) {
        getProperties().add(new P(Name.Z_INDEX, zindex));
        return this;
    }

    public void asHtmlStyle(StringBuffer sb) {
        sb.append("style=\"");
        for (P p : getProperties()) {
            sb.append(p.getName().getHtmlName()).append(":").append(p.getValue()).append(";");
        }


        sb.append("\"");
    }


    ///***********************
    // COLORS
    ///***********************


    public TextLayout colorLink() {
        return colorBaseDark();
    }

    //BASE

    public TextLayout backgroundBase() {
        getProperties().add(Color.backgroundBase());
        return this;
    }

    public TextLayout backgroundBlue() {
        getProperties().add(P.BACKGROUND_BLUE);
        return this;
    }

    public TextLayout backgroundBaseDark() {
        getProperties().add(Color.backgroundBaseDark());
        return this;
    }

    public TextLayout colorBlue() {
        getProperties().add(P.COLOR_BLUE);
        return this;
    }

    public TextLayout colorLightBlue() {
        getProperties().add(P.COLOR_BLUE_LIGHT);
        return this;
    }

    public TextLayout colorDarkBlue() {
        getProperties().add(P.COLOR_DARK_BLUE);
        return this;
    }

    public TextLayout colorBase() {
        getProperties().add(Color.textBase());
        return this;
    }

    public TextLayout colorBaseDark() {
        getProperties().add(Color.textBaseDark());
        return this;
    }


    //COMPLEMENTARY

    public TextLayout backgroundCompl() {
        getProperties().add(Color.backgroundCompl());
        return this;
    }

    public TextLayout backgroundComplLight() {
        getProperties().add(Color.backgroundComplLight());
        return this;
    }

    public TextLayout colorCompl() {
        getProperties().add(Color.textCompl());
        return this;
    }

    public TextLayout colorComplDark() {
        getProperties().add(Color.textComplDark());
        return this;
    }


    //ALLROUND BACKGROUND COLORS

    public TextLayout backgroundWhite() {
        getProperties().add(P.BACKGROUND_WHITE);
        return this;
    }

    public TextLayout backgroundBlack() {
        getProperties().add(P.BACKGROUND_BLACK);
        return this;
    }

    public TextLayout backgroundGrey() {
        getProperties().add(P.BACKGROUND_E1);
        return this;
    }

    public TextLayout backgroundGreyLight() {
        getProperties().add(P.BACKGROUND_F0);
        return this;
    }

    //ALLROUND TEXT COLORS

    public TextLayout colorWhite() {
        getProperties().add(P.COLOR_WHITE);
        return this;
    }

    public TextLayout colorBlack() {
        getProperties().add(P.COLOR_BLACK);
        return this;
    }


    public TextLayout colorC1() {
        getProperties().add(P.COLOR_C1);
        return this;
    }


    public TextLayout colorGrey() {
        getProperties().add(P.COLOR_GREY);
        return this;
    }

    public TextLayout colorGreyLight() {
        getProperties().add(P.COLOR_GREY_LIGHT2);
        return this;
    }


    public TextLayout colorRed() {
        getProperties().add(P.COLOR_DARK_RED);
        return this;
    }
}
