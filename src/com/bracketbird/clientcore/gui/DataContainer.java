package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.validation.*;

import java.util.*;

public abstract class DataContainer extends GuiComponent implements ValueContainer{

    private String name;
    private LabelComponent label;
    private List<Validator> validators = new ArrayList<Validator>();

    public boolean isMandatory() {
        return mandatory;
    }

    private boolean mandatory;


    public DataContainer(String name, boolean mandatory){
        super();
        if(mandatory){
            validators.add(Validator.MANDATORY);
        }
        this.mandatory = mandatory;
        this.name = name;
        this.label = new LabelComponent("");
        setName(name);
        this.label.getLabel().setWordWrap(false);
        StyleIt.add(getLabel().getLabel(), Color.textBaseDark());
    }

    public void setName(String name){
        this.name = name;
        label.setText(mandatory ? (name + " *") : name);
    }

    public String getName() {
        return name;
    }

    public LabelComponent getLabel() {
        return label;
    }

    public abstract Widget getDataWidget();

    public abstract GuiComponent getGui();

    public abstract void clear();

    public abstract boolean isEmpty();

    public abstract void setFocus(boolean focus);


    public void setEnable(boolean enabled){
        if(!enabled){
            StyleIt.add(getLabel(), Name.OPACITY, "0.30");
            StyleIt.add(getLabel(), Name.FILTER, "alpha(opacity=30)");
        }
        else{
            StyleIt.add(getLabel(), Name.OPACITY, "1.00");
            StyleIt.add(getLabel(), Name.FILTER, "alpha(opacity=100)");
        }
        enableWidget(enabled);
    }

    protected abstract void enableWidget(boolean editable);

    public void setDataIsIllegal(boolean dataIsIllegal){
        if(dataIsIllegal){
            StyleIt.add(getLabel().getLabel(),P.COLOR_DARK_RED);
        }
        else{
            StyleIt.add(getLabel().getLabel(),Color.textBaseDark());
        }
    }

    public DataContainer add(Validator v){
        getValidators().add(v);
        return this;
    }

    public List<Validator> getValidators() {
        return validators;
    }

    public void setTitle(String title){
        getGui().setTitle(title);
    }

    public abstract void addFocusHandler(FocusHandler fh);
    public abstract void setVisible(boolean visible);


}
