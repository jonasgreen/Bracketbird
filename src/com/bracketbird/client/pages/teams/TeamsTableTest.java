package com.bracketbird.client.pages.teams;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 *
 */
public class TeamsTableTest extends FlowPanel{



    public TeamsTableTest() {
        setStyleName("teamsTableTest");

        int i = 0;
        while (i < 10){
            addRow(i++);
        }

        Button b = new Button("test");
        b.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                getWidget(getWidgetCount()-1).getElement().getStyle().setProperty("order", "3");
            }
        });
        add(b);
        b.getElement().getStyle().setProperty("order", i+"");
    }


    public void addRow(int order){
        TextBox box = new TextBox();
        box.setStyleName("teamsTableItem");
        add(box);
        box.setText(order+"");
        box.getElement().getStyle().setProperty("order", order+"");
        box.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
               getWidget(getWidgetCount()-1).getElement().getStyle().setProperty("order", "2");
            }
        });
    }
}
