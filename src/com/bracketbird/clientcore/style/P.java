package com.bracketbird.clientcore.style;

import com.google.gwt.user.client.ui.Label;

import java.io.Serializable;

/**
 *
 */
public class P implements Serializable {
    private static final long serialVersionUID = 1289863916204902239L;

    public static P BACKGROUND_WHITE = new P(Name.BACKGROUND, "white");
    public static P BACKGROUND_NONE = new P(Name.BACKGROUND, "none");

    public static P BACKGROUND_SUBPAGE = new P(Name.BACKGROUND, "#F3F3E7");
    public static P BACKGROUND_SUBPAGE_LIGTH = new P(Name.BACKGROUND, "#FCFCF8");
    public static P BACKGROUND_BLUE_GOOGLE_MAP = new P(Name.BACKGROUND, "#99B3CC");


    public static P BACKGROUND_FB = new P(Name.BACKGROUND, "#FBFBFB");

    public static P BACKGROUND_C1 = new P(Name.BACKGROUND, "#C1C1C1");
    public static P BACKGROUND_D1 = new P(Name.BACKGROUND, "#D1D2D4");
    public static P BACKGROUND_E1 = new P(Name.BACKGROUND, "#E1E2E3");
    public static P BACKGROUND_F0 = new P(Name.BACKGROUND, "#EFF0F0");


    public static P BACKGROUND_MENU_LIGHT = new P(Name.BACKGROUND, "#638e52");


    public static P BACKGROUND_BLUE = new P(Name.BACKGROUND, "#29abe2");
    public static P BACKGROUND_DARK_BLUE = new P(Name.BACKGROUND, "#1D9CD3");

    public static P BACKGROUND_LIGHT_GREY = new P(Name.BACKGROUND, "#FAFAFA");
    public static P BACKGROUND_BLUE_TIPS = new P(Name.BACKGROUND, "#A8CFEB");


    public static P BACKGROUND_BLACK = new P(Name.BACKGROUND, "black");


    public static P BACKGROUND_DARK_GREY = new P(Name.BACKGROUND, "#9A9999");

    public static P BACKGROUND_GREY = new P(Name.BACKGROUND, "#E7E7DE");
    public static P BACKGROUND_GREY_YELLOW = new P(Name.BACKGROUND, "#F1F3E8");


    public static P BACKGROUND_ORANGE = new P(Name.BACKGROUND, "#FFC233");


    public static P BACKGROUND_PURPLE = new P(Name.BACKGROUND, "#A00A95");

    public static P BACKGROUND_RED = new P(Name.BACKGROUND, "#FF4100");

    public static P BACKGROUND_DARK_RED = new P(Name.BACKGROUND, "#CC0000");


    public static P COLOR_C1 = new P(Name.COLOR, "#C1C1C1");
    public static P COLOR_4D = new P(Name.COLOR, "#4D4D4D");

    public static P COLOR_BLACK = new P(Name.COLOR, "#000000");
    public static P COLOR_BLUE = new P(Name.COLOR, "#29abe2");
    public static P COLOR_DARK_BLUE = new P(Name.COLOR, "#1A8CBC");

    public static P COLOR_GREY_LIGHT = new P(Name.COLOR, "#E7E7DE");

    public static P COLOR_DARK_GREY = new P(Name.COLOR, "#9A9999");


    public static P COLOR_GREY = new P(Name.COLOR, "#999999");
    public static P COLOR_GREY_CA = new P(Name.COLOR, "#CACACA");


    public static P COLOR_WHITE = new P(Name.COLOR, "white");
    public static P COLOR_WHITE_ALMOST = new P(Name.COLOR, "rgb(201,201,201)");

    public static P COLOR_PURPLE = new P(Name.COLOR, "#740160");


    public static P COLOR_DARK_RED = new P(Name.COLOR, "#CC0000");


    public static P BORDER_BOTTOM_SOLID = new P(Name.BORDER_BOTTOM, "solid");
    public static P BORDER_LEFT_SOLID = new P(Name.BORDER_LEFT, "solid");
    public static P BORDER_RIGHT_SOLID = new P(Name.BORDER_RIGHT, "solid");
    public static P BORDER_TOP_SOLID = new P(Name.BORDER_TOP, "solid");


    public static P BORDER_STYLE_SOLID = new P(Name.BORDER_STYLE, "solid");
    public static P BORDER_WIDTH_1px = new P(Name.BORDER_WIDTH, "1px");

    public static P BORDER_WIDTH_2px = new P(Name.BORDER_WIDTH, "2px");
    public static P BORDER_TOP_SOLID_2px_999 = new P(Name.BORDER_TOP, "2px solid #727272");

    public static P FLOAT_LEFT = new P(Name.FLOAT, "left");

    public static P FONT_FAMILY_ARIAL_HELV_SANS = new P(Name.FONT_FAMILY, "klavika,Arial,Helvetica,sans-serif");

    //ekstra giga
    public static P FONT_SIZE_64PX = new P(Name.FONT_SIZE, "64px");


    //giga
    public static P FONT_SIZE_46PX = new P(Name.FONT_SIZE, "46px");

    //title
    public static P FONT_SIZE_38PX = new P(Name.FONT_SIZE, "38px");

    //h1
    public static P FONT_SIZE_28PX = new P(Name.FONT_SIZE, "28px");

    //H2
    public static P FONT_SIZE_22PX = new P(Name.FONT_SIZE, "22px");

    //H3
    public static P FONT_SIZE_18PX = new P(Name.FONT_SIZE, "18px");

    //Normal
    public static P FONT_SIZE_15PX = new P(Name.FONT_SIZE, "15px");

    //smal
    public static P FONT_SIZE_16PX = new P(Name.FONT_SIZE, "16px");

    //ekstra small
    public static P FONT_SIZE_14PX = new P(Name.FONT_SIZE, "14px");

    //ekstra ekstra small    
    public static P FONT_SIZE_12PX = new P(Name.FONT_SIZE, "12px");


    public static P FONT_SIZE_ADJUST_NONE = new P(Name.FONT_SIZE_ADJUST, "none");
    public static P FONT_STRETCH_NORMAL = new P(Name.FONT_STRETCH, "normal");
    public static P FONT_STYLE_NORNAL = new P(Name.FONT_STYLE, "normal");
    public static P FONT_VARIANT_NORMAL = new P(Name.FONT_VARIANT, "normal");
    public static P FONT_WEIGHT_BOLD = new P(Name.FONT_WEIGHT, "bold");
    public static P FONT_WEIGHT_NORMAL = new P(Name.FONT_WEIGHT, "normal");
    public static P FONT_STYLE_ITALIC = new P(Name.FONT_STYLE, "italic");

    public static P LINE_HEIGHT_1_25 = new P(Name.LINE_HEIGHT, "1.25");

    public static P TEXT_ALIGN_CENTER = new P(Name.TEXT_ALIGN, "center");
    public static P TEXT_DECORATION_UNDERLINE = new P(Name.TEXT_DECORATION, "underline");
    public static P TEXT_DECORATION_NON = new P(Name.TEXT_DECORATION, "none");

    public static P TEXT_ALIGN_JUSTIFY = new P(Name.TEXT_ALIGN, "justify");
    public static P VERTICAL_ALIGN_MIDDLE = new P(Name.VERTICAL_ALIGN, "middle");

    public static P NO_WRAP = new P(Name.WHITE_SPACE, "nowrap");
    public static P WRAP = new P(Name.WHITE_SPACE, "normal");

    public static P X_SYSTEM_FONT_NONE = new P(Name.X_SYSTEM_FONT, "none");


    private Name name;
    private String value;
    public static P BACKGROUND_F5 = new P(Name.BACKGROUND, "#F5F5F5");

    public P(Name name, String value) {
        this.name = name;
        this.value = value;
    }

    public Name getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public boolean isFontSize() {
        return name.isFontSize();
    }


    @Override
    public String toString() {
        return "P{" +
                "name='" + name.getName() + '\'' +
                ", value='" + value + '\'' +
                '}';
    }


}
