package com.bracketbird.client.applicationstarter;

import com.bracketbird.client.browser.Browser;
import com.bracketbird.client.gui.main.AppPageController;
import com.bracketbird.client.gui.main.FrontPageController;
import com.bracketbird.clientcore.appcontrol.PageController;
import com.bracketbird.clientcore.appcontrol.PageFlow;
import com.bracketbird.clientcore.gui.LabelComponent;
import com.bracketbird.clientcore.gui.VerticalComponent;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.util.GlobalKeyboardHandler;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 *
 */
public class ApplicationStarter {

    protected GlobalKeyboardHandler gh = new GlobalKeyboardHandler();


    public void start() {
        Window.enableScrolling(true);
        PageFlow.activeController = AppPageController.getInstance();
        PageFlow.show(getPageController());

        if (Browser.isIEBrowser()) {
            showIENotSupportedMessage();
        }
        else {
            //UserManager.getInstance().silentLogIn();
            gh.setupSingleAppKeyboardShortcuts();
        }


    }


    public PageController getPageController() {
        return FrontPageController.getInstance();
    }

    protected void showIENotSupportedMessage() {
        PopupPanel popupPanel = new PopupPanel(false);
        VerticalComponent vc = new VerticalComponent();

        LabelComponent header = new LabelComponent("Internet Explorer is not supported at the moment");
        LabelComponent t1a = new LabelComponent("Please run this site in Mozilla Firefox or Google Chrome.");
        LabelComponent t2 = new LabelComponent("Sorry for the inconvenience.");
        vc.add(header, new TextLayout().sizeH1().colorBlack().padding(20).paddingBottom(30));

        TextLayout tl = new TextLayout().sizeH3().colorBlack().padding(5).paddingLeft(20).paddingRight(20);
        vc.add(t1a, tl);
        tl.paddingBottom(20);
        vc.add(t2, tl);

        popupPanel.add(vc);
        vc.getElement().getStyle().setBorderColor("black");
        vc.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        vc.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
        vc.getElement().getStyle().setBorderWidth(1, Style.Unit.PX);
        vc.getElement().getStyle().setBackgroundColor("Orange");
        popupPanel.show();
        popupPanel.center();
    }
}
