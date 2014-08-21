package com.bracketbird.clientcore.gui;

import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.style.*;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;


/**
 *
 */
public class PopupManager {

    private static PopupPanel panel = new PopupPanel(false, true);
    private static SimplePanelComponent contentHolder = new SimplePanelComponent();
    private static RootPanel rootPanel = RootPanel.get("popup");
    private static OnClose onClose;


    static {
        panel.addCloseHandler(new CloseHandler<PopupPanel>() {
            public void onClose(CloseEvent<PopupPanel> popupPanelCloseEvent) {
                ensureHistory();
                if(onClose != null){
                    onClose.onClose();
                }
            }
        });


        rootPanel.setHeight((Window.getClientHeight() + 1000) + "px");
        rootPanel.setWidth((Window.getClientWidth() + 1000) + "px");
        rootPanel.setStyleName("overlay");

        StyleIt.add(rootPanel, P.BACKGROUND_DARK_GREY);
        StyleIt.add(rootPanel, Name.OPACITY, "0.5");
        StyleIt.add(rootPanel, Name.FILTER, "alpha(opacity=50)");
        StyleIt.add(rootPanel, Name.Z_INDEX, ZIndex.POPUP_OVERLAY);

        StyleIt.add(panel, Name.Z_INDEX, ZIndex.POPUP_BOX);
        StyleIt.add(panel, Name.BORDER_WIDTH, "0px");
        panel.add(contentHolder);
        rootPanel.setVisible(false);
        StyleIt.add(panel, Name.PADDING, "0px");
        contentHolder.setStyleName("lineShadow");

    }


    public static void show(PageController pc) {
        show(pc, null);
    }


    public static void show(PageController pc, OnClose afterClose) {
        showPage(pc, afterClose);
        pc.afterLoad();

    }

    private static void showPage(PageController pc, OnClose afterClose) {
        VerticalComponent content = new VerticalComponent();
        //content.add(pc.getPage(), new TextLayout(0, 0, 0, 0).padding(10, 10, 10, 10));
        show(content, onClose);
    }

    public static void show(GuiComponent gc, OnClose afterClose){
        onClose = afterClose;
        rootPanel.setVisible(true);
        contentHolder.add(gc);


        panel.center();
        panel.show();

        gc.setFocus(true);
    }

    public static boolean isShowing() {
        return panel.isShowing();
    }

    public static int getClientOffsetHeight() {
        return panel.getElement().getAbsoluteTop();
    }

    public static void setBorder(PopupInfo.InfoLevel level) {
        StyleIt.add(panel, Name.BORDER_WIDTH, "1px");
        StyleIt.add(panel, Name.BORDER_COLOR, "#A6A6A6");
    }

    private static void ensureHistory() {
        //TODO - PageLoaderManager.getInstance().addActiveControllerToHistory();
    }


    public static void hide() {
        /*if (panel != null && panel.isShowing()) {
            panel.hide();
        }
        rootPanel.setVisible(false);
        */
    }

    public static void setLightRootPanel() {
        StyleIt.add(rootPanel, P.BACKGROUND_LIGHT_GREY);
    }

    public static void setDarkRootPanel() {
        StyleIt.add(rootPanel, P.BACKGROUND_DARK_GREY);
    }
}
