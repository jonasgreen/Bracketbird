package com.bracketbird.client.pages.front;

import com.google.gwt.user.client.ui.HTML;

/**
 *
 */
public class Logo extends HTML {

    public Logo() {
        super();
        setStyleName("logo");
        //addStyleName("flex_alignItems_center");
        String top = "<div class='logoTop'><span class='logoTopLeft'>BRACKET</span><span class='logoTopRight'>BIRD</span></div>";
        String bottom = "<div class='logoBottom'><span class='logoBottomLeft'>MORE TIME</span><span class='logoBottomRight'> TO PLAY</span></div>";

        setHTML(top);
    }
}
