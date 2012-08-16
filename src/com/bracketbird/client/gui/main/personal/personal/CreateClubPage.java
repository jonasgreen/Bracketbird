package com.bracketbird.client.gui.main.personal.personal;

import com.bracketbird.client.model.*;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.validation.*;

import java.util.*;

/**
 *
 */
public class CreateClubPage extends Page<CreateClubPageController> {


    public StringContainer NAME = new StringContainer("Name of the club", true);

    private TextLayout labelLayout = new TextLayout(0, 0, 6, 0).sizeSmall().colorBase().bold();
    private TextLayout contentLayout = new TextLayout(0, 0, 0, 0, "46px", "480px", null, Vertical.MIDDLE).sizeH1().verticalAlignMiddel().colorBaseDark();


    private SimplePanelComponent problemHolder;
    private ValidationComponent valComponent;

    private LabelComponent helpText;
    private LabelComponent helpTextAdmin;
    private ButtonComponent button;
    private SimplePanelComponent succesMessageHolder;
    private HorizontalComponent succesText;
    private VerticalComponent content;

    public CreateClubPage() {
        super();
        content = new VerticalComponent();
        initWidget(content);
    }


    public void init() {
        content.add(getProblemHolder(), new TextLayout(0,0,10,0, null, "480px", Horizontal.LEFT));



            content.add(NAME.getLabel(), labelLayout);
            content.add(NAME.getGui(), contentLayout);

            

        TextLayout tlHelp1 = new TextLayout().sizeSmall().colorLink().paddingTop(20);
        TextLayout tlHelp2 = new TextLayout().sizeSmall().colorLink().paddingTop(10).paddingBottom(20);
        content.add(getHelpTextAdmin(), tlHelp1);
        content.add(getHelpText(), tlHelp2);

        content.add(getSuccesMessageHolder());

        content.add(getButton(), LayoutFac.button().horizontalAlignLeft());
        content.setWidth("500px");
        content.getElement().getStyle().setMargin(10, Style.Unit.PX);


    }


    public Collection<DataContainer> getDataContainerChildren() {
        List<DataContainer> children = new ArrayList<DataContainer>();
        children.add(NAME);
        return children;
    }

    public String getHeader() {
        return "Create A New Club";
    }


    public LabelComponent getHelpTextAdmin() {
        if (helpTextAdmin == null) {
            helpTextAdmin = new LabelComponent("You are automatically added as a member with 'admin' rights of this club.");
        }
        return helpTextAdmin;
    }

    public LabelComponent getHelpText() {
        if (helpText == null) {
            helpText = new LabelComponent("You can add further details later, by entering the club's setting page.");
        }
        return helpText;
    }


    public ButtonComponent getButton() {
        if (button == null) {
            button = new ButtonComponent("Create club");
            button.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    getController().createClub();
                }
            });
        }
        return button;
    }


    public void removeErrors() {
        if (valComponent != null) {
            valComponent.removeFromParent();

            for (DataContainer dc : getDataContainerChildren()) {
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
        getProblemHolder().add(valComponent, new TextLayout(null, "100%"));

        vm.getErrorDCs().get(0).setFocus(true);
    }

    public void removeSuccesText() {
        if (succesText != null) {
            succesText.removeFromParent();
        }
    }

    public void showSucces(final Club club) {
        if (succesText != null) {
            succesText.removeFromParent();
        }
        succesText = new HorizontalComponent();
        succesText.add(new LabelComponent("Your club was succesfully created - you can enter it"), new TextLayout().sizeH3().colorBaseDark());
        HyperlinkLabelComponent hl = new HyperlinkLabelComponent("here");
        hl.underline();
        hl.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                //UserManager.getInstance().loggedIntoClub(club);
            }
        });
        succesText.add(hl, new TextLayout().paddingLeft(4).colorLink().sizeH2());
        getSuccesMessageHolder().add(succesText, new TextLayout().paddingBottom(8));
    }

    public SimplePanelComponent getProblemHolder() {
        if (problemHolder == null) {
            problemHolder = new SimplePanelComponent();
        }
        return problemHolder;
    }

    public SimplePanelComponent getSuccesMessageHolder() {
        if (succesMessageHolder == null) {
            succesMessageHolder = new SimplePanelComponent();
        }
        return succesMessageHolder;
    }

    protected void setSubPageHolder(Page subPage) {
        //ignore
    }

}