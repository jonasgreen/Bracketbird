package com.bracketbird.clientcore.gui;

import java.util.Collection;

/**
 *
 */
public abstract class DataPanel extends VerticalComponent{

    private boolean hasDelete = false;

    protected DataPanel() {
        super();
    }

    public abstract Collection<DataContainer> getDataContainerChildren();

    public abstract void save();
    public abstract void delete();

    public void cancel(){
        PopupManager.hide();
    }


    public String getInfo(){
        return null;
    }


    public abstract void setFocus();


    public boolean hasSave(){
        return true;
    }

    public boolean hasCancel(){
        return true;
    }

    public boolean hasDelete(){
        return hasDelete;
    }

    public void setHasDelete(boolean hasDelete) {
        this.hasDelete = hasDelete;
    }
}
