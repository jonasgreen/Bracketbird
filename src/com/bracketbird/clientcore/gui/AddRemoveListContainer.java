package com.bracketbird.clientcore.gui;


import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;

/**
 *
 */
public class AddRemoveListContainer<T> extends DataContainer {


    private class RowPanel extends HorizontalComponent {


        private ListContainer<T> listContainer;

        private RowPanel(KeyValueList<T> list) {
            super();
            this.listContainer = new ListContainer<T>("", list, 0, false, true);
            add(listContainer.getGui(), new TextLayout().sizeSmall().padding(2).colorBaseDark());

            ImageComponent minus = new ImageComponent("img/minus.png");
            minus.getImage().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    removeRow(RowPanel.this);
                }
            });
            minus.getImage().addMouseOverHandler(MouseOver.POINTER);
            minus.setTitle("Remove this rule.");
            add(minus, new TextLayout(Vertical.MIDDLE).paddingLeft(4));
            minus.setHeight("10px");
            minus.setWidth("10px");

            ImageComponent plus = new ImageComponent("img/plus.png");
            plus.getImage().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    addRow();
                }
            });
            plus.getImage().addMouseOverHandler(MouseOver.POINTER);
            plus.setTitle("Add another rule.");
            add(plus, new TextLayout(Vertical.MIDDLE).paddingLeft(4));
            plus.setHeight("10px");
            plus.setWidth("10px");

        }

    }

    private List<RowPanel> rows = new ArrayList<RowPanel>();
    private VerticalComponent contentWidget = new VerticalComponent();
    private KeyValueList keyValueList;


    public AddRemoveListContainer(String name, KeyValueList<T> keyValueList, boolean mandatory) {
        super(name, mandatory);
        this.keyValueList = keyValueList;
        init();
    }

    private void init() {
        addRow();
    }

    public void removeRow(RowPanel rp) {
        if (rows.size() == 1) {
            return;
        }
        rp.removeFromParent();
        rows.remove(rp);
    }

    public RowPanel addRow() {
        RowPanel rp = new RowPanel(keyValueList);
        contentWidget.add(rp, new TextLayout().paddingTop(2).paddingBottom(2));
        rows.add(rp);
        return rp;
    }

    public Widget getDataWidget() {
        return contentWidget;
    }


    public GuiComponent getGui() {
        return contentWidget;
    }

    public void clear() {
        for (RowPanel row : rows) {
            row.removeFromParent();
        }
        rows = new ArrayList<RowPanel>();
        addRow();
        setDataIsIllegal(false);
    }

    public void setValue(Object obj) {
        throw new SystemException("Method should not be used");
    }


    public List<T> getValue() {
        List<T> list = new ArrayList<T>();
        for (RowPanel row : rows) {
            list.add(row.listContainer.getValue());
        }
        return list;

    }

    public void setValue(List<T> list) {
        clear();

        boolean b = true;
        for (T t : list) {
            if(b){
                b = false;
            }
            else{
                addRow();
            }
        }

        int count = 0;

        for (T t : list) {
            RowPanel rp = rows.get(count++);
            rp.listContainer.setKeyWithValue(t);
        }


    }

    public boolean isEmpty() {
        return false;
    }

    public void addKeyDownHandler(KeyDownHandler handler) {
    }

    @Override
    public void setFocus(boolean focus) {
        rows.get(rows.size() - 1).listContainer.setFocus(focus);
    }

    public void enableWidget(boolean editable) {

    }

    public void addFocusHandler(FocusHandler fh) {
    }

    public void setVisible(boolean visible) {
        contentWidget.setVisible(visible);
        getLabel().getLabel().setVisible(visible);
    }


}