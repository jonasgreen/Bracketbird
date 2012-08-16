package com.bracketbird.clientcore.gui;

import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.validation.*;

import java.util.*;

/**
 *
 */
public class ErrorPanel extends VerticalComponent {

    private SimplePanelComponent errors = new SimplePanelComponent();
    private Collection<DataContainer> dataContainers = new ArrayList<DataContainer>();

    public ErrorPanel(Collection<DataContainer> dataContainers) {
        super();
        this.dataContainers = dataContainers;
        add(errors);
    }


    public void setDataContainers(Collection<DataContainer> dataContainers) {
        this.dataContainers = dataContainers;
    }

    public boolean validate() {
        ValidateManager vm = new ValidateManager();
        boolean succes = vm.validate(dataContainers);
        if (succes) {
            errors.setVisible(false);
        }
        else {
            errors.setVisible(true);
            errors.add(new ValidationComponent(vm), new TextLayout(null, "100%"));
            vm.getErrorDCs().get(0).setFocus(true);
        }
        return succes;
    }

    public Collection<DataContainer> getDataContainerChildren(){
        return dataContainers;
    }

    public void clear(){
        errors.setVisible(false);
        for (DataContainer dc : dataContainers) {
            dc.setDataIsIllegal(false);
        }
    }
}