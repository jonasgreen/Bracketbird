package com.bracketbird.client;

import com.bracketbird.clientcore.gui.OnClose;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;

public abstract class PopupBracketBird extends PopupPanel {


    protected FlowPanel content = new FlowPanel();
    private HandlerRegistration handlerRegistration;
    private OnClose onClose;

    public PopupBracketBird() {
        init();
    }


    public PopupBracketBird(boolean autoHide, boolean modal, OnClose onClose) {
        super(autoHide, modal);
        this.onClose = onClose;
        init();
    }

    private void init() {
        add(content);
        setStyleName("popupBracketBird");

        addCloseOnEsc();
        addCloseHandler(new CloseHandler<PopupPanel>() {
            @Override
            public void onClose(CloseEvent<PopupPanel> event) {
                handleClose();
            }
        });
    }

    private void handleClose() {
        if(handlerRegistration != null){
            handlerRegistration.removeHandler();
        }
        if(onClose != null){
            onClose.onClose();
        }
    }

    private void addCloseOnEsc() {
        handlerRegistration = Event.addNativePreviewHandler(new Event.NativePreviewHandler() {
            public void onPreviewNativeEvent(Event.NativePreviewEvent event) {

                int keyCode = event.getNativeEvent().getKeyCode();
                if (keyCode == KeyCodes.KEY_ESCAPE) {
                    cancel();
                    event.getNativeEvent().preventDefault();
                    event.getNativeEvent().stopPropagation();
                }
                else if(keyCode == KeyCodes.KEY_ENTER || keyCode == KeyCodes.KEY_MAC_ENTER){
                    save();
                    event.getNativeEvent().preventDefault();
                    event.getNativeEvent().stopPropagation();
                }
            }
        });
    }

    protected void cancel(){
        hide();
    }

    protected abstract void save();

    public void center() {
        super.center();
        setPopupPosition(getAbsoluteLeft(), 150);
    }
}
