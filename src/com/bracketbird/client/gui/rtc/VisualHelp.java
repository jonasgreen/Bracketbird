package com.bracketbird.client.gui.rtc;


import com.bracketbird.client.gui.rtc.teams.TeamsPageController;
import com.bracketbird.clientcore.appcontrol.PageFlow;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;

/**
 *
 */
public class VisualHelp extends SimplePanelComponent {


    private ImageComponent hideVisualHelp;
    private ImageComponent showVisualHelp;

    private List<VisualHelpItem> items = new ArrayList<VisualHelpItem>();
    private List<PopupPanel> popups = new ArrayList<PopupPanel>();

    public VisualHelp() {
        super();
        init();
    }

    private void init() {
        add(getShowHelp());
    }

    public void add(VisualHelpItem item) {
        items.add(item);
    }


    public FlowComponent getShowHelp() {
        FlowComponent showHelp = new FlowComponent();

        LabelComponent p = new LabelComponent("<< Back");
        p.setStyleName("colorbutton4");
        p.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                PageFlow.show(TeamsPageController.getInstance());
            }
        });

        LabelComponent help = new LabelComponent("?");
        help.setStyleName("colorbutton4");
        help.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                show();
            }
        });

        showHelp.addLeft(p, null);
        showHelp.addLeft(help, null);

        return showHelp;
    }

    public FlowComponent getHideHelp() {
        FlowComponent showHelp = new FlowComponent();

        LabelComponent p = new LabelComponent("<< Back");
        p.setStyleName("colorbutton4");
        p.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                PageFlow.show(TeamsPageController.getInstance());
            }
        });


        showHelp.addLeft(p, null);
        showHelp.addLeft(getHideVisualHelp(), null);

        return showHelp;
    }



    public void show() {
        add(getHideHelp());
        for (VisualHelpItem item : items) {
            final PopupPanel p = new PopupPanel(true);
            p.addCloseHandler(new CloseHandler<PopupPanel>() {
                public void onClose(CloseEvent<PopupPanel> popupPanelCloseEvent) {
                    popups.remove(p);
                    if (popups.isEmpty()) {
                        hide();
                    }
                }
            });
            p.add(new ImageComponent(item.getImageUrl()));
            StyleIt.add(p, new TextLayout().border(0));
            int left;
            int top;

            if (item.getTarget() != null) {
                left = item.getTarget().getAbsoluteLeft() + item.getExtraLeft();
                top = item.getTarget().getAbsoluteTop() + item.getExtraTop();

            }
            else {
                left = item.getExtraLeft();
                top = item.getExtraTop();
            }
            p.setPopupPosition(left, top);
            p.show();
            popups.add(p);
        }

    }

    public ImageComponent getHideVisualHelp() {
        if (hideVisualHelp == null) {
            hideVisualHelp = new ImageComponent("hideVisualHelp.png");
            hideVisualHelp.getImage().addMouseOverHandler(MouseOver.POINTER);
            hideVisualHelp.getImage().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    hide();

                }
            });
            hideVisualHelp.getImage().setTitle("Hide visual help");
        }
        return hideVisualHelp;
    }

    public void hide() {
        add(getShowHelp());
        for (PopupPanel popup : popups) {
            popup.hide();
        }
    }

}
