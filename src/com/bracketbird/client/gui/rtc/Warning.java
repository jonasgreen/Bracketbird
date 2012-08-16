package com.bracketbird.client.gui.rtc;


import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.*;

/**
 *
 */
public class Warning extends VerticalComponent implements KeyDownHandler {
    protected LabelComponent header;
    private String text;
    private boolean proceed = false;
    protected ButtonPanel buttonPanel;
    protected GuiComponent guiComponent;

    public Warning(String text) {
        super();
        this.text = text;
        init();
    }

    private void init() {
        add(getHeader(), getHeaderLayout());
        SimplePanelComponent sp = new SimplePanelComponent();
        sp.add(getContent(), getTextLayout());
        add(sp, getBackgroundLayout());
        add(getButtonPanel(), new TextLayout(null, "100%").padding(20));
        addDomHandler(this, KeyDownEvent.getType());
    }

    protected TextLayout getTextLayout() {
        return new TextLayout(null, "500px").sizeNormal().colorBaseDark().padding(20);
    }

    protected TextLayout getHeaderLayout() {
        return new TextLayout().sizeH2().colorBaseDark().padding(20);
    }

    protected TextLayout getBackgroundLayout() {
        return new TextLayout(null, "100%").backgroundCompl();
    }


    public GuiComponent getContent() {
        if (guiComponent == null) {
            guiComponent = new LabelComponent(text);
        }
        return guiComponent;
    }

    public LabelComponent getHeader() {
        if (header == null) {
            header = new LabelComponent("WARNING!");
        }
        return header;
    }

    public ButtonPanel getButtonPanel() {
        if (buttonPanel == null) {
            ButtonComponent proceed = new ButtonComponent("Proceed");
            proceed.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    Warning.this.proceed = true;
                    PopupManager.hide();
                }
            });
            proceed.setFocus(true);

            ButtonComponent cancel = new ButtonComponent("Cancel");
            cancel.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    Warning.this.proceed = false;
                    PopupManager.hide();
                }
            });

            buttonPanel = new ButtonPanel(cancel, proceed);
        }
        return buttonPanel;
    }

    public boolean isProceed() {
        return proceed;
    }


    public void onKeyDown(KeyDownEvent event) {
        if (KeyUtil.isEnter(event.getNativeKeyCode())) {
            Warning.this.proceed = true;
            PopupManager.hide();
        }

    }
}
