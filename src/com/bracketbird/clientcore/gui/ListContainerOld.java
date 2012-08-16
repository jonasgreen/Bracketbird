package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.dom.client.FocusHandler;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class ListContainerOld extends DataContainer {
    ListBoxComponent lb = new ListBoxComponent();

    private List<String> items;
    private int selectedIndex;

    public ListContainerOld(String name, String[] items, int selectedIndex, boolean isMandatory) {
        this(name, Arrays.asList(items),selectedIndex, isMandatory);
    }

    public ListContainerOld(String name, List<String> items, int selectedIndex, boolean isMandatory) {
        super(name, isMandatory);
        this.items = items;
        this.selectedIndex = selectedIndex;
        for (String s : items) {
            lb.addItem(s);
        }
        lb.setVisibleItemCount(1);
        lb.setSelectedIndex(selectedIndex);
        

    }

    public Widget getDataWidget() {
        return lb.getListBox();
    }

    public ListBoxComponent getGui() {
        return lb;
    }

    public void setValue(Object obj) {
        setValue((String) obj);
    }

    public String getValue(){
        return lb.getListBox().getValue(lb.getSelectedIndex());
    }

    public void setValue(String value){
        int index = 0;
        for (String item : items) {
            if(item.equalsIgnoreCase(value)){
                lb.setSelectedIndex(index);
                return;
            }
            index++;
        }
    }

    public void clear(){
        lb.setSelectedIndex(selectedIndex);
        setDataIsIllegal(false);
    }

    public boolean isEmpty() {
        return false;
    }

    @Override
    public void setFocus(boolean focus) {
       lb.getListBox().setFocus(focus);
    }

    public void enableWidget(boolean editable) {
        lb.getListBox().setEnabled(editable);
    }

    public void addFocusHandler(FocusHandler fh) {
        lb.getListBox().addFocusHandler(fh);
    }
    public void setVisible(boolean visible) {
        lb.getListBox().setVisible(visible);
        getLabel().getLabel().setVisible(visible);
    }

}
