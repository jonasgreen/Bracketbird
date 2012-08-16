package com.bracketbird.client.gui.main;

import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.gui.LabelComponent;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.dom.client.Style;

/**
 *
 */
public class FrontSlogan extends FlowComponent{

    private LabelComponent header;
    private LabelComponent text;

    public FrontSlogan(String header, String text) {
        super();

        getHeader().setText(header);
        getText().setText(text);

        add(new FlowComponent(getHeader(), new TextLayout().colorBaseDark().sizeH1().paddingBottom(0)));
        add(new FlowComponent(getText(), new TextLayout().colorBase().sizeH3().alignJystify()).paddingBottom(30));
    }

    public LabelComponent getHeader() {
        if (header == null) {
            header = new LabelComponent("");
        }
        return header;
    }

    public LabelComponent getText() {
        if (text == null) {
            text = new LabelComponent("");
        }
        return text;
    }
}


