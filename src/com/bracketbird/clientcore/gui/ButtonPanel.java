package com.bracketbird.clientcore.gui;

import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class ButtonPanel extends HorizontalComponent {
    private ButtonComponent[] buttons;
    private Horizontal align;

    public ButtonPanel(ButtonComponent... buttons) {
        this(Horizontal.RIGHT, buttons);
    }

    public ButtonPanel(Horizontal align, ButtonComponent... buttons) {
        super();
        this.align = align;
        this.buttons = buttons;

        init();
    }

    private void init() {
        HorizontalComponent hc = new HorizontalComponent();
        TextLayout first = LayoutFac.button();
        TextLayout rest = LayoutFac.button().margin(0, 0, 0, 10);

        boolean isFirst = true;
        for (ButtonComponent b : buttons) {
            if (isFirst) {
                hc.add(b, first);
                isFirst = false;
            }
            else {
                hc.add(b, rest);
            }
        }

        add(hc, new TextLayout(align));
    }
}
