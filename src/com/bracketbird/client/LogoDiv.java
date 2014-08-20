package com.bracketbird.client;

import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.gui.LabelComponent;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.dom.client.Style;

/**
 *
 */
public class LogoDiv extends FlowComponent{

    public LogoDiv() {
        int padding = 8;
        FlowComponent content = new FlowComponent();
        add(content);

        FlowComponent fl = new FlowComponent();
        fl.add(new LabelComponent("BRACKET"));
        fl.getElement().getStyle().setFloat(Style.Float.LEFT);
        content.add(fl, new TextLayout().colorLightBlue().sizeH2().padding(padding).paddingRight(0).paddingBottom(2).paddingLeft(12));

        fl = new FlowComponent();
        fl.add(new LabelComponent("BIRD"));
        fl.getElement().getStyle().setFloat(Style.Float.LEFT);
        content.add(fl, new TextLayout().sizeH2().colorGreyLight().padding(padding).paddingLeft(0).paddingBottom(2).paddingRight(12));

    }
}
