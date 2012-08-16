package com.bracketbird.clientcore.gui;

import com.bracketbird.clientcore.model.KeyValueList;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.dom.client.FocusHandler;

import java.util.List;

/**
 *
 */
public class ListContainer<T> extends DataContainer {
    ListBoxComponent lb = new ListBoxComponent();

    private int selectedIndex;
    private KeyValueList<T> keyValueList;
    private boolean emptyPossible = false;

    public ListContainer(String name, KeyValueList<T> keyValueList, int selectedIndex, boolean emptyPossible, boolean isMandatory) {
        super(name, isMandatory);
        this.emptyPossible = emptyPossible;
        this.keyValueList = keyValueList;
        this.selectedIndex = selectedIndex;
        init();
    }


    private void init() {
        @SuppressWarnings({"unchecked"})
        List<String> keys = keyValueList.getKeys();
        for (String key : keys) {
            lb.addItem(key);
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

    public void replace(KeyValueList<T> list) {
        this.keyValueList = list;
        lb.getListBox().clear();
        List<String> keys = keyValueList.getKeys();
        for (String key : keys) {
            lb.addItem(key);
        }
    }

    public void setValue(Object obj) {
        @SuppressWarnings({"unchecked"})
        T value = (T) obj;
        int index = 0;

        List<T> ts = keyValueList.getValues();
        for (T t : ts) {
            if (t.equals(value)) {
                lb.setSelectedIndex(index);
            }
            index++;
        }
    }

    public T getValue() {
        return (T) keyValueList.getValue(lb.getSelectedIndex());
    }

    public String getKey() {
        return keyValueList.getKey(lb.getSelectedIndex());
    }


    public void clear() {
        lb.setSelectedIndex(selectedIndex);
        setDataIsIllegal(false);
    }


    public void setKeyWithValue(T value){
        int i = 0;
        for (T t : keyValueList.getValues()) {
            if(value.equals(t)){
                lb.setSelectedIndex(i);
            }
            i++;
        }
    }

    public boolean isEmpty() {
        return emptyPossible && lb.getSelectedIndex() == 0;
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

    public KeyValueList<T> getKeyValueList() {
        return keyValueList;
    }
}
