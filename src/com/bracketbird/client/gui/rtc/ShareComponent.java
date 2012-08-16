package com.bracketbird.client.gui.rtc;

import com.bracketbird.client.url.UrlUtil;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.Horizontal;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class ShareComponent extends FlowComponent {


    private ButtonComponent closeButton;

    public ShareComponent(String readOnlyUrl, String editUrl) {
        init(readOnlyUrl, editUrl);
    }

    private void init(String readOnlyUrl, String editUrl) {
        StyleIt.add(this, new TextLayout().padding(10));

        TextLayout title = new TextLayout(null, "100%").sizeH1().colorBaseDark();
        TextLayout text = new TextLayout(null, "100%").sizeH3().colorBaseDark().paddingTop(20).paddingBottom(4);
        TextLayout divL = new TextLayout().backgroundGreyLight().paddingLeft(0);

        add(new LabelComponent("Share tournament"), title);

        add(new LabelComponent("View only:"), text);
        add(getUrlCompoenent(readOnlyUrl), divL);

        add(new LabelComponent("Full access:"), text);
        add(getUrlCompoenent(editUrl), divL);

        getCloseButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                PopupManager.hide();
            }
        });
        ButtonPanel buttonPanel = new ButtonPanel(Horizontal.RIGHT, getCloseButton());
        add(buttonPanel, new TextLayout(null, "100%").margin(20,0,0,0));

    }

    public ButtonComponent getCloseButton() {
        if (closeButton == null) {
            closeButton = new ButtonComponent("Close");
        }
        return closeButton;
    }

    private FlowComponent getUrlCompoenent(String url){
        FlowComponent div = new FlowComponent();
        TextBoxComponent tb = new TextBoxComponent();
        tb.setText(UrlUtil.getBaseUrl() + "#"+url);
        tb.getTextBox().setReadOnly(true);
        TextLayout layout = new TextLayout("30px", "500px").sizeNormal().italic().colorBaseDark().backgroundGreyLight();

        div.add(tb, layout);
        //tb.setStyleName("share_url");
        return div;
    }
}
