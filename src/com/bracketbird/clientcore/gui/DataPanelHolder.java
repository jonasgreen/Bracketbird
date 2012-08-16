package com.bracketbird.clientcore.gui;


import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.validation.*;

import java.util.*;

/**
 *
 */
public class DataPanelHolder extends VerticalComponent {

    private SimplePanelComponent problemHolder;
    private ValidationComponent valComponent;
    private HorizontalComponent buttonPanel;
    private ButtonComponent saveButton;
    private ButtonComponent cancelButton;
    private ButtonComponent deleteButton;

    private DataPanel dataPanel;
    private LabelComponent infoLabel;


    public DataPanelHolder(DataPanel dp) {
        super();
        this.dataPanel = dp;
        init();
    }



    public void init() {
        add(getProblemHolder(), new Layout17(null, "300px"));

        add(dataPanel, new TextLayout(4, 0, 0, 0));
        add(getButtonPanel(), new Layout17(20, 10, 4, 0, Horizontal.RIGHT));
        add(getInfoLabel(), new TextLayout().sizeEkstraSmall().colorBaseDark());

    }



    public HorizontalComponent getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new HorizontalComponent();
            TextLayout tl = new TextLayout(0, 0, 0, 10, Vertical.MIDDLE).sizeSmall().colorBaseDark();
            buttonPanel.add(getSaveButton(), tl);
            buttonPanel.add(getCancelButton(), tl);
            if (dataPanel.hasDelete()) {
                buttonPanel.add(getDeleteButton(), tl);
            }
        }
        return buttonPanel;
    }

    public LabelComponent getInfoLabel() {
        if (infoLabel == null) {
            infoLabel = new LabelComponent("");
        }
        return infoLabel;
    }

    public SimplePanelComponent getProblemHolder() {
        if (problemHolder == null) {
            problemHolder = new SimplePanelComponent();
        }
        return problemHolder;
    }

    public ButtonComponent getDeleteButton() {
        if (deleteButton == null) {
            deleteButton = new ButtonComponent("Delete");
            deleteButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    dataPanel.delete();
                }
            });
        }
        return deleteButton;
    }

    public ButtonComponent getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new ButtonComponent("Cancel");
            cancelButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    cancel();
                }
            });
        }
        return cancelButton;
    }

    private void cancel() {
        dataPanel.cancel();

    }

    public ButtonComponent getSaveButton() {
        if (saveButton == null) {
            saveButton = new ButtonComponent("Save");
            saveButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    save();
                }
            });
        }
        return saveButton;
    }

    private void save() {
        ValidateManager vm = new ValidateManager();
        Collection<DataContainer> dcs = dataPanel.getDataContainerChildren();
        boolean succes = vm.validate(dcs);

        if (succes) {
            removeErrors();
            dataPanel.save();
        }
        else {
            showErrors(vm);
        }
    }

    public void removeErrors() {
        if (valComponent != null) {
            valComponent.removeFromParent();
            for (DataContainer dc : dataPanel.getDataContainerChildren()) {
                dc.setDataIsIllegal(false);
            }
        }
    }

    public void showErrors(ValidateManager vm) {
        if (valComponent != null) {
            valComponent.removeFromParent();
        }
        InfoManager.hideInfo();
        valComponent = new ValidationComponent(vm);
        getProblemHolder().add(valComponent, new Layout17(0, 0, 15, 0, null, "100%"));
        vm.getErrorDCs().get(0).setFocus(true);
    }

    public void clear() {
        for (DataContainer dc : dataPanel.getDataContainerChildren()) {
            dc.clear();
        }
    }

    public void setFocus(){
        dataPanel.setFocus();
    }

}
