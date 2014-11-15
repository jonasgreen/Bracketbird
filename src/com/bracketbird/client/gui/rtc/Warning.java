package com.bracketbird.client.gui.rtc;


import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.util.KeyUtil;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class Warning extends FlowPanel implements KeyDownHandler {
    protected Label header;
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
        add(getHeader());
        SimplePanelComponent sp = new SimplePanelComponent();
        sp.add(getContent());
        add(sp);
        add(getButtonPanel());
        addDomHandler(this, KeyDownEvent.getType());
    }


    public GuiComponent getContent() {
        if (guiComponent == null) {
            guiComponent = new LabelComponent(text);
        }
        return guiComponent;
    }

    public Label getHeader() {
        if (header == null) {
            header = new Label("WARNING!");
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
