package com.bracketbird.clientcore.style;

/**
 *
 */
public enum Name {


//OBS - ingen bindestreger - erstattes af camelCase

    ALIGN("align"),

    BACKGROUND("background"),

    BORDER("border"),
    BORDER_COLOR("borderColor", "border-color"),
    BORDER_STYLE("borderStyle", "border-style"),
    BORDER_BOTTOM("borderBottom", "border-bottom"),
    BORDER_LEFT("borderLeft", "border-left"),
    BORDER_RIGHT("borderRight", "border-right"),
    BORDER_TOP("borderTop", "border-top"),
    BORDER_WIDTH("borderWidth", "border-width"),

    COLOR("color"),
    COLSPAN("colspan", "col-span"),

    DISPLAY("display"),

    FILTER("filter"),

    FLOAT("float"),
    FONT_FAMILY("fontFamily","font-family"),
    FONT_SIZE("fontSize", "font-size"),
    FONT_SIZE_ADJUST("fontSizeAdjust", "font-size-adjust"),
    FONT_STRETCH("fontStretch", "font-stretch"),
    FONT_STYLE("fontStyle", "font-style"),
    FONT_VARIANT("fontVariant", "font-variant"),
    FONT_WEIGHT("fontWeight", "font-weight"),


    FOREGROUND("foreground"),

    HEIGHT("height"),
    HORIZONTAL_ALIGN("horizontalAlign", "horizontal-align"),

    LEFT("left"),
    LINE_HEIGHT("lineHeight", "line-height"),

    MARGIN("margin"),
    MARGIN_LEFT("marginLeft", "margin-left"),
    MARGIN_RIGHT("marginRight", "margin-right"),
    MARGIN_Bottom("marginBottom", "margin-bottom"),
    MARGIN_TOP("marginTop", "margin-top"),

    OPACITY("opacity"),
    OVERFLOW("overflow"),

    PADDING("padding"),
    PADDING_LEFT("paddingLeft", "padding-left"),
    PADDING_RIGHT("paddingRight", "padding-right"),

    PADDING_TOP("paddingTop", "padding-top"),
    PADDING_Bottom("paddingBottom", "padding-bottom"),

    POSITION("position"),


    RIGHT("right"),

    TEXT_ALIGN("textAlign", "text-align"),
    TEXT_DECORATION("textDecoration", "text-decoration"),

    TOP("top"),
    BOTTOM("bottom"),

    VERTICAL_ALIGN("verticalAlign", "vertical-align"),
    WHITE_SPACE("whiteSpace", "white-space"),
    WIDTH("width"),

    X_SYSTEM_FONT("xSystemFont", "x-system-font"),
    Z_INDEX("zIndex", "z-index");

    private final String name;
    private final String htmlName;


    Name(String n) {
        this(n, n);
    }


    Name(String n, String htmlName) {
        this.htmlName = htmlName;
        this.name = n;
    }

    public String getName() {
        return name;
    }

    public String getHtmlName() {
        return htmlName;
    }

    public boolean isFontSize() {
        return name.equals(Name.FONT_SIZE.getName());
    }


    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                '}';
    }
}
