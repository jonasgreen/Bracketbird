package com.bracketbird.client.pages;

import com.bracketbird.clientcore.appcontrol.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class MenuItem extends FlowPanel implements PageChangedListener {

    private static String STYLE_ACTIVE_MODE = "menuItem_active";
    private static String STYLE_PASSIVE_MODE = "menuItem_passive";

    private PageController pageController;
    private String mode;

    public MenuItem(String labelText, PageController pc) {
        Label label = new Label();
        label.setText(labelText);
        this.pageController = pc;
        this.setStyleName("menuItem");

        this.addStyleName(STYLE_PASSIVE_MODE);
        this.mode = STYLE_PASSIVE_MODE;

        TournamentContext.get().addPageChangeListener(this);

        label.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (isPassive()) {
                    Application.show(pageController);
                }
            }
        });
        add(label);
    }

    private boolean isPassive() {
        return STYLE_PASSIVE_MODE.equals(mode);
    }

    @Override
    public void onPageChange(PageChangedEvent event) {
        setActive(pageController.equals(event.getCurrentPage()));
    }

    private void setActive(boolean activate) {
        String oldStyle = mode;
        mode = activate ? STYLE_ACTIVE_MODE : STYLE_PASSIVE_MODE;
        if (!oldStyle.equals(mode)) {
            removeStyleName(oldStyle);
            addStyleName(mode);
        }
    }
}
