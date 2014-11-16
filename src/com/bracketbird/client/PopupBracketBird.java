package com.bracketbird.client;

import com.bracketbird.client.gui.rtc.Handler;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

import java.util.ArrayList;
import java.util.List;

public abstract class PopupBracketBird extends PopupPanel {

    protected FlowPanel allContent = new FlowPanel();

    protected FlowPanel contentPanel;
    protected FlowPanel headerPanel;
    protected FlowPanel buttonsPanel;

    private Label headerLabel;

    private Button okButton;
    private Label cancelButton;

    private List<Handler> okHandlers = new ArrayList<Handler>();

    private HandlerRegistration handlerRegistration;

    public PopupBracketBird() {
        init();
    }


    public PopupBracketBird(boolean autoHide, boolean modal) {
        super(autoHide, modal);
        init();
    }

    private void init() {
        setStyleName("popupBracketBird");
        add(allContent);
        allContent.add(getHeaderPanel());
        allContent.add(getContentPanel());
        allContent.add(getButtonsPanel());

        addCloseOnEsc();
        addCloseHandler(new CloseHandler<PopupPanel>() {
            @Override
            public void onClose(CloseEvent<PopupPanel> event) {
                handleClose();
            }
        });
    }

    public Label getHeaderLabel() {
        if (headerLabel == null) {
            headerLabel = new Label("");
        }
        return headerLabel;
    }

    public FlowPanel getHeaderPanel() {
        if (headerPanel == null) {
            headerPanel = Css.style(new FlowPanel(), "popupBracketBird_headerPanel");
            headerPanel.add(getHeaderLabel());
        }
        return headerPanel;
    }

    public FlowPanel getContentPanel() {
        if (contentPanel == null) {
            contentPanel = Css.style(new FlowPanel(), "popupBracketBird_contentPanel");
        }
        return contentPanel;
    }



    private void handleClose() {
        if(handlerRegistration != null){
            handlerRegistration.removeHandler();
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
            }
        });
    }


    protected FlowPanel getButtonsPanel() {
        if (buttonsPanel == null) {
            buttonsPanel = Css.style(new FlowPanel(), "popupBracketBird_buttonsPanel", "flex_center_end");

            buttonsPanel.add(getCancelButton());
            buttonsPanel.add(getOkButton());

        }
        return buttonsPanel;
    }

    protected Label getCancelButton() {
        if (cancelButton == null) {
            cancelButton = Css.style(new Label("Cancel"), "secondaryButton", "settingsPanelButton");
            cancelButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    cancel();
                }
            });
        }
        return cancelButton;
    }

    public Button getOkButton() {
        if (okButton == null) {
            okButton = Css.style(new Button("Save"), "primaryButton", "settingsPanelButton");
            okButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    ok();
                }
            });
        }
        return okButton;
    }


    protected void cancel(){
        hide();
    }

    protected void ok(){
        for (Handler e : okHandlers) {
            e.handle();
        }
        hide();
    }

    public void addOkHandler(Handler h){
        okHandlers.add(h);
    }


    public void show(){
        super.show();
        setFocusElement();
    }

    private void setFocusElement() {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                setFocus();
            }
        });
    }

    public void center() {
        super.center();
        setPopupPosition(getAbsoluteLeft(), 150);
        setFocusElement();
    }

    protected abstract void setFocus();
}
