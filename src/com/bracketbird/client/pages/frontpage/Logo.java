package com.bracketbird.client.pages.frontpage;

import com.google.gwt.user.client.ui.HTML;

/**
 *
 */
public class Logo extends HTML {

    public Logo() {
        super();
        addStyleName("logo");
        setHTML("<span class='logoLeft'>BRACKET</span><span class='logoRight'>BIRD</span>");
    }
}
