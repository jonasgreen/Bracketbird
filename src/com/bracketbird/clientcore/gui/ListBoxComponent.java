package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.ListBox;

/**
 *
 */
public class ListBoxComponent extends GuiComponent{

    private ListBox listBox = new ListBox(false);

    public ListBoxComponent() {
        super();
        initWidget(listBox);
    }

    public ListBox getListBox() {
        return listBox;
    }

    public void addItem(String s) {
        listBox.addItem(s);
    }

    public void setVisibleItemCount(int i) {
        listBox.setVisibleItemCount(i);
    }

    public void setSelectedIndex(int i) {
        listBox.setSelectedIndex(i);
    }

    public String getItemText(int selectedIndex) {
        return listBox.getItemText(selectedIndex);
    }

    public int getSelectedIndex() {
        return listBox.getSelectedIndex();
    }

    public void addKeyDownHandler(KeyDownHandler keyDownHandler) {
        listBox.addKeyDownHandler(keyDownHandler);
    }


}
