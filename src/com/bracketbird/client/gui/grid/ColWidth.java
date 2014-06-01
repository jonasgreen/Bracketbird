package com.bracketbird.client.gui.grid;

/**
*
*/
public class ColWidth extends Style {

    public static final ColWidth col1 = new ColWidth("pure-u-1-1");
    public static final ColWidth col1_2 = new ColWidth("pure-u-1-2");
    public static final ColWidth col1_3 = new ColWidth("pure-u-1-3");
    public static final ColWidth col2_3 = new ColWidth("pure-u-2-3");
    public static final ColWidth col1_4 = new ColWidth("pure-u-1-4");
    public static final ColWidth col3_4 = new ColWidth("pure-u-3-4");
    public static final ColWidth col1_5 = new ColWidth("pure-u-1-5");
    public static final ColWidth col2_5 = new ColWidth("pure-u-2-5");
    public static final ColWidth col3_5 = new ColWidth("pure-u-3-5");
    public static final ColWidth col4_5 = new ColWidth("pure-u-4-5");
    public static final ColWidth col1_6 = new ColWidth("pure-u-1-6");
    public static final ColWidth col5_6 = new ColWidth("pure-u-5-6");
    public static final ColWidth col1_8 = new ColWidth("pure-u-1-8");
    public static final ColWidth col3_8 = new ColWidth("pure-u-3-8");
    public static final ColWidth col5_8 = new ColWidth("pure-u-5-8");
    public static final ColWidth col7_8 = new ColWidth("pure-u-7-8");
    public static final ColWidth col1_12 = new ColWidth("pure-u-1-12");
    public static final ColWidth col5_12 = new ColWidth("pure-u-5-12");
    public static final ColWidth col7_12 = new ColWidth("pure-u-7-12");
    public static final ColWidth col11_12 = new ColWidth("pure-u-11-12");
    public static final ColWidth col1_24 = new ColWidth("pure-u-1-24");
    public static final ColWidth col5_24 = new ColWidth("pure-u-5-24");
    public static final ColWidth col7_24 = new ColWidth("pure-u-7-24");
    public static final ColWidth col11_24 = new ColWidth("pure-u-11-24");
    public static final ColWidth col13_24 = new ColWidth("pure-u-13-24");
    public static final ColWidth col17_24 = new ColWidth("pure-u-17-24");
    public static final ColWidth col19_24 = new ColWidth("pure-u-19-24");
    public static final ColWidth col23_24 = new ColWidth("pure-u-23-24");
    public static final ColWidth col1_13 = new ColWidth("pure-u-1-13");
    
    
    public ColWidth(String style) {
        super(style);
    }
    
    public static final ColWidth getEvenColWidth(int count){
    	if (count < 2)
    		return col1;
    	if (count == 2)
    		return col1_2;
    	if (count == 3)
    		return col1_3;
    	if (count == 4)
    		return col1_4;
    	if (count == 5)
    		return col1_5;
    	if (count == 6)
    		return col1_6;
    	if (count < 9)
    		return col1_8;
    	if (count < 13)
    		return col1_12;
    	return col1_24;
    }
}
