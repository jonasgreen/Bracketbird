package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.*;
import com.bracketbird.clientcore.validation.*;

import java.util.*;

/**
 *
 */
public abstract class EnterSettingsComponent extends VerticalComponent {

    private HorizontalComponent header;

    private SimplePanelComponent plusMinus;
    private ImageComponent plus = new ImageComponent("plus.png");
    private ImageComponent minus = new ImageComponent("minus.png");
    private TextLayout pmLayout = new TextLayout(Vertical.MIDDLE).paddingRight(5);

    private LabelComponent headerLabel;
    private ButtonComponent saveButton;
    private HorizontalComponent saveButtonPanel;
    private SimplePanelComponent errors = new SimplePanelComponent();


    public EnterSettingsComponent() {
        super();
        init();
    }

    protected void init() {
        add(getHeader(), new TextLayout(10, 0, 10, 0, "20px", "100%"));
        add(getContent(), new TextLayout(0, 10, 10, 30, null, "100%"));
        add(errors, new TextLayout(0, 16, 0, 30));
        add(getSaveButtonPanel(), new TextLayout(null, "100%"));
        showContent(false);
    }


    public HorizontalComponent getSaveButtonPanel() {
        if (saveButtonPanel == null) {
            saveButtonPanel = new HorizontalComponent();
            saveButtonPanel.add(getSaveButton(), new TextLayout(10, 16, 10, 10, Horizontal.RIGHT).sizeSmall().colorBaseDark());
        }
        return saveButtonPanel;
    }

    public ButtonComponent getSaveButton() {
        if (saveButton == null) {
            saveButton = new ButtonComponent("Save");
            saveButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    if(validate()){
                        save();                        
                    }
                }
            });
        }
        return saveButton;
    }


    public boolean validate() {
        ValidateManager vm = new ValidateManager();
        Collection<DataContainer> dcs = getDataContainerChildren();
        boolean succes = vm.validate(dcs);
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

    public HorizontalComponent getHeader() {
        if (header == null) {
            header = new HorizontalComponent();
            header.add(getPlusMinus(), new TextLayout(null, "20px", Horizontal.LEFT, Vertical.MIDDLE));
            header.add(getHeaderLabel(), new TextLayout(0, 0, 0, 10, Horizontal.LEFT, Vertical.MIDDLE).alignLeft().sizeH3().colorBaseDark().italic());

            ClickHandler ch = new ClickHandler() {
                public void onClick(ClickEvent event) {
                    showContent(!getContent().isVisible());
                }
            };
            getPlusMinus().addClickHandler(ch);
            getHeaderLabel().addClickHandler(ch);

            getPlusMinus().addMouseOverHandler(MouseOver.POINTER);
            getHeaderLabel().addMouseOver(MouseOver.POINTER);

        }
        return header;
    }

    public LabelComponent getHeaderLabel() {
        if (headerLabel == null) {
            headerLabel = new LabelComponent(getHeaderText());
        }
        return headerLabel;
    }

    public abstract void save();

    public abstract GuiComponent getContent();

    public abstract String getHeaderText();

    public abstract Collection<DataContainer> getDataContainerChildren();


    public SimplePanelComponent getPlusMinus() {
        if (plusMinus == null) {
            plusMinus = new SimplePanelComponent();
            plusMinus.add(plus, pmLayout);
        }
        return plusMinus;
    }

    public void showContent(boolean show) {
        getContent().setVisible(show);
        getSaveButton().setVisible(show);
        errors.setVisible(show);
        if (show) {
            setBackgroundColor(P.BACKGROUND_SUBPAGE);
            getPlusMinus().add(minus, pmLayout);
        }
        else {
            setBackgroundColor(P.BACKGROUND_WHITE);
            getPlusMinus().add(plus, pmLayout);
        }
    }

}