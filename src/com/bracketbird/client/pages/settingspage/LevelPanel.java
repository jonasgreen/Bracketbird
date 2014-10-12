package com.bracketbird.client.pages.settingspage;

import com.bracketbird.client.Flex;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LevelPanel extends FlowPanel {


    private List<LevelComponent> levelComponents = new ArrayList<LevelComponent>();
    private AddStageDropDown popup;


    private Button button;


    public LevelPanel() {
        setStyleName(Flex.FLEX);
        addStyleName(Flex.ALIGN_ITEMS_CENTER);

        add(new BeginLevelComponent());

        add(new LevelComponent("Group"));
        add(new LevelComponent("Knockout"));

        add(new EndLevelComponent());
        //add(new AllEmptyLevelComponent());
        //add(new EndLevelComponent());
        add(getButton());

    }


    public Button getButton() {
        if (button == null) {
            button = new Button("Add Stage/Level");
            button.setStyleName("primaryButton");
            button.getElement().getStyle().setMarginLeft(40, Style.Unit.PX);
            button.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    if (popup == null) {
                        popup = new AddStageDropDown();
                        popup.setWidth(button.getOffsetWidth() + "px");
                        popup.show();
                        popup.getElement().getStyle().setTop(button.getAbsoluteTop() + button.getOffsetHeight(), Style.Unit.PX);
                        popup.getElement().getStyle().setLeft(button.getAbsoluteLeft(), Style.Unit.PX);
                        popup.addCloseHandler(new CloseHandler<PopupPanel>() {
                            @Override
                            public void onClose(CloseEvent<PopupPanel> event) {
                                popup = null;
                            }
                        });

                        popup.addAutoHidePartner(button.getElement());
                    }
                    else{
                        popup.hide();
                    }
                }
            });



        }
        return button;
    }

    private boolean isEmpty() {
        return levelComponents.size() == 1 && levelComponents.get(0) instanceof AllEmptyLevelComponent;
    }


}
