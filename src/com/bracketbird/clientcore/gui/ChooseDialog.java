package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Timer;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class ChooseDialog extends DialogComponent {

    private long lastClick = 0;
    private int chosenItem = 0;
    private int lastChoosenItem = 0;
    private ListBoxComponent listBox = null;
    private Collection<String> items;
    private GuiComponent guiComp = null;
    private Layout17 guiCompLayout = null;

    public ChooseDialog(List<String> items, String msg, Response... responses) {
        super(false, true, responses);
        setText(msg);
        this.items = items;
        getListBox().setSelectedIndex(0);
    }


    public void add(GuiComponent gc, Layout17 l) {
        guiComp = gc;
        guiCompLayout = l;
    }

    protected void buttonClicked(Response r) {
        hide();
        if (callBack != null) {
            chosenItem = listBox.getSelectedIndex();
            callBack.onClose(r);
        }
    }

    
    public void show(DialogCallBack callBack) {
        buildGui();
        setWidth("300px");
        super.show(callBack);
        Timer t = new Timer() {
            @Override
            public void run() {
                getListBox().getListBox().setFocus(true);
            }
        };

        t.schedule(200);
    }

    private void buildGui() {
        VerticalComponent vc = new VerticalComponent();
        vc.add(getListBox(), new Layout17(0, 0, 0, 0, null, "100%"));
        if(guiComp != null){
        vc.add(guiComp, guiCompLayout);
        }
        super.add(vc, new Layout17(0,0,0,0, null, "100%"));
        ButtonComponent b = getButton(Response.OK);
        b.setText("Vælg");
    }


    public ListBoxComponent getListBox() {
        if (listBox == null) {
            listBox = new ListBoxComponent();
           
            for (String item : items) {
                listBox.addItem(item);
            }
            listBox.setVisibleItemCount(6);
            listBox.getListBox().addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    //simulating dobbel click
                    if (lastChoosenItem == listBox.getSelectedIndex() && System.currentTimeMillis() - lastClick < 1000) {
                        buttonClicked(DialogComponent.Response.OK);
                    }
                    lastClick = System.currentTimeMillis();
                    lastChoosenItem = listBox.getSelectedIndex();
                }
            });


            listBox.getListBox().addKeyDownHandler(new KeyDownHandler() {
                public void onKeyDown(KeyDownEvent event) {
                    if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER && event.getSource() == listBox.getListBox()) {
                        buttonClicked(DialogComponent.Response.OK);
                    }
                    else if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE && event.getSource() == listBox.getListBox()) {
                        buttonClicked(DialogComponent.Response.CANCEL);
                    }

                }
            });
        }
        return listBox;
    }

    public int getChosenItem() {
        return chosenItem;
    }


}
