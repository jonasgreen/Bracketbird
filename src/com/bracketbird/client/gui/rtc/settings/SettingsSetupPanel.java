package com.bracketbird.client.gui.rtc.settings;


import com.bracketbird.client.gui.rtc.RTCLayoutFac2;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class SettingsSetupPanel extends VerticalComponent {


    public SettingsSetupPanel() {
        super();
        init();
    }

    public void init() {
        add(new LabelComponent("Use a template."), RTCLayoutFac2.h2());

        HorizontalComponent hc = new HorizontalComponent();
        ListBoxComponent lb = new ListBoxComponent();
        lb.getListBox().addItem("Your templates");
        lb.getListBox().addItem("Tirsdagsturnering");
        lb.getListBox().addItem("Jays");
        hc.add(lb, new TextLayout(0, 10, 0, 0).sizeNormal().colorBaseDark().verticalAlignMiddel());

        ButtonComponent bc = new ButtonComponent("Use template");
        hc.add(bc, LayoutFac.button().verticalAlignMiddel());

        add(hc, new TextLayout(10, 0, 0, 0, Vertical.MIDDLE));

        HorizontalComponent one = new HorizontalComponent();
        one.add(new LabelComponent("Or define settings"), RTCLayoutFac2.h2().noWrap().paddingRight(8));


        HyperlinkLabelComponent link = new HyperlinkLabelComponent("from scratch.", RTCLayoutFac2.h2().underline());
        link.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                SettingsPageController.getInstance().showSettingsFromScratch();

            }
        });
        one.add(link, new TextLayout(Vertical.MIDDLE));

        add(one, new TextLayout(30, 0, 0, 0));
    }

}