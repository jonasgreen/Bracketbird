package com.bracketbird.client.pages.front;

import com.google.gwt.user.client.ui.HTML;

/**
 *
 */
public class Logo extends HTML {

    public Logo() {
        super();
        setStyleName("frontPage_logo");
        //addStyleName("flex_alignItems_center");
        String top = "<div class='frontPage_logo_top'><span class='frontPage_logo_topLeft'>BRACKET</span><span class='frontPage_logo_topRight'>BIRD</span></div>";
        String bottom = "<div class='frontPage_logo_bottom'><span class='frontPage_logo_bottomLeft'>MORE TIME</span><span class='frontPage_logo_bottomRight'> TO PLAY</span></div>";

        setHTML(top);
    }
}
