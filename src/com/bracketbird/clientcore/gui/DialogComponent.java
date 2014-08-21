package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class DialogComponent extends DialogBox {


    public static enum Response {
        OK,
        CANCEL
    }


    private java.util.Map<Response, ButtonComponent> buttons = new HashMap<Response, ButtonComponent>();
    private VerticalComponent content = new VerticalComponent();
    protected DialogCallBack callBack;
    private Response[] responses;

    public interface DialogCallBack {
        public void onClose(Response r);
    }


    public DialogComponent(boolean autoHide, boolean modal, Response... responses) {
        super(autoHide, modal);
        this.responses = responses;
        //fails if initWidget is called
        //    add(dialogBox);
    }

    public void show(DialogCallBack callBack) {
        center();
        this.callBack = callBack;
        show();
    }


    public void add(GuiComponent gc, Layout17 l) {
        content.add(gc);
        gc.layout(l);
        content.add(getButtons(), new Layout17(12, 4, 4, 4, Horizontal.RIGHT, null));
        add(content);
        content.setWidth("100%");
    }


    protected void buttonClicked(Response r) {
        hide();
        if (callBack != null) {
            callBack.onClose(r);
        }
    }

    private HorizontalComponent getButtons() {
        HorizontalComponent hc = new HorizontalComponent();
        for (Response r : responses) {
            hc.add(createButton(r), new Layout17(0, 4, 0, 0, Horizontal.RIGHT, null));
        }
        return hc;
    }

    private ButtonComponent createButton(Response r) {
        ButtonComponent b = new ButtonComponent();
        if (r == Response.OK) {
            b.setText("Ok");
            b.getButton().setFocus(true);
            b.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    buttonClicked(Response.OK);
                }
            });
        }
        else {
            b.setText("Annullér");
            b.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    buttonClicked(Response.CANCEL);
                }
            });
        }
        buttons.put(r, b);
        return b;
    }

    public ButtonComponent getButton(Response r) {
        return buttons.get(r);
    }


}
