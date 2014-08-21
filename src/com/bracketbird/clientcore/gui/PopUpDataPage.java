package com.bracketbird.clientcore.gui;

import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class PopUpDataPage extends Page<PopUpDataPageController> {

    private VerticalComponent content;
    private SimplePanelComponent contentHolder = new SimplePanelComponent();
    private LabelComponent header;

    private DataPanelHolder dataPanelHolder;


    public PopUpDataPage() {
        super();
        content = new VerticalComponent();
        add(content);
    }

    public void init() {
        content.add(getHeader(), new TextLayout(0, 0, 12, 0).sizeH1().bold().colorBaseDark());
        content.add(contentHolder, new TextLayout(4, 0, 0, 0));
        //setBackgroundColor(P.BACKGROUND_SUBPAGE);
    }

    protected void setSubPageHolder(Page subPage) {
        //ignore
    }


    public void setFocus(){
        if(dataPanelHolder != null){
            dataPanelHolder.setFocus();
        }
    }


    public LabelComponent getHeader() {
        if (header == null) {
            header = new LabelComponent("");
        }
        return header;
    }


    public DataPanelHolder getDataPanelHolder() {
        return dataPanelHolder;
    }

    public void setDataPanel(DataPanel dataPanel, String title) {
        this.dataPanelHolder = new DataPanelHolder(dataPanel);
        getHeader().setText(title);
        if(dataPanel.getInfo() != null){
            dataPanelHolder.getInfoLabel().setText(dataPanel.getInfo());
        }
        contentHolder.add(dataPanelHolder);
    }


}