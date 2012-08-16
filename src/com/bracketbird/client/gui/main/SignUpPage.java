package com.bracketbird.client.gui.main;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.validation.*;

/**
 *
 */
public class SignUpPage extends Page<SignUpPageController> {

    private VerticalComponent content;
    private SignUpPanel signupPanel;
    private HyperlinkLabelComponent alreadyAMember;
    private VerticalComponent signUpWrapper;
    private SimplePanelComponent problemHolder;
    private ValidationComponent valComponent;
    private HorizontalComponent termsOfUse;
    private AcceptTermsOfUseContainer acceptTermsBox;
    private LabelComponent header;
    private HorizontalComponent termsOfUseLeft;

    public SignUpPage() {
        super();
        content = new VerticalComponent();
        initWidget(content);
    }

    public void init() {
        getAcceptTermsBox().add(Validator.ACCEPT_TERMS_OF_USE);

        Layout17 ll = new TextLayout(0, 0, 12, 0).sizeH1().bold().noWrap().colorLink();

        Layout17 lsiguPanel = new TextLayout().border(1).borderColor(Color.textBase());

        content.add(getHeader(), ll);
        content.add(getProblemHolder(), new Layout17(0, 0, 10, 0, null, "300px"));

         //driver link


        content.add(getSignUpWrapper(), lsiguPanel);

        content.add(getTermsOfUseAndButton(), new Layout17(20,0,20,0, null, "100%"));
        content.add(getAlreadyAMember());

    }


    public LabelComponent getHeader() {
        if (header == null) {
            header = new LabelComponent("Create account");
        }
        return header;
    }

    public AcceptTermsOfUseContainer getAcceptTermsBox() {
        if (acceptTermsBox == null) {
            acceptTermsBox = new AcceptTermsOfUseContainer("Yes, I agree to Bracketbirds ", true);
            StyleIt.add(acceptTermsBox.getGui(), new TextLayout().noWrap());
        }
        return acceptTermsBox;
    }

    public HorizontalComponent getTermsOfUseAndButton() {
        if (termsOfUse == null) {
            termsOfUse = new HorizontalComponent();

            termsOfUseLeft = new HorizontalComponent();
            TextLayout l = new TextLayout(0,3,0,0, null, Vertical.MIDDLE).sizeSmall().colorBaseDark();
            termsOfUseLeft.add(getAcceptTermsBox().getGui(), l);

            HyperlinkLabelComponent ref = new HyperlinkLabelComponent("terms of service");
            ref.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    Window.open("http://www.bracketbird.com/terms_eng.html", "_blank", null);
                }
            });
            termsOfUseLeft.add(ref, l.underline().colorBlue().margin(0,0,2,0));
            termsOfUse.add(termsOfUseLeft, new Layout17(0,0,5,0, Horizontal.LEFT, Vertical.BOTTOM));

            HorizontalComponent hcRight = new HorizontalComponent();
            hcRight.add(getSaveButton(), LayoutFac.button().margin(0,10,0,0).horizontalAlignRight().verticalAlignMiddel());
            termsOfUse.add(hcRight, new Layout17(Horizontal.RIGHT, Vertical.MIDDLE));
        }
        return termsOfUse;
    }

    public SimplePanelComponent getProblemHolder() {
        if (problemHolder == null) {
            problemHolder = new SimplePanelComponent();
        }
        return problemHolder;
    }

    private ButtonComponent getSaveButton() {
        ButtonComponent b = new ButtonComponent("Create account");
        //b.setText("Opret");
        b.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                getController().save();
            }
        });
        return b;
    }

    public HyperlinkLabelComponent getAlreadyAMember() {
        if (alreadyAMember == null) {
            alreadyAMember = new HyperlinkLabelComponent("I already have an account", new TextLayout().sizeSmall().bold().colorBlue().underline().noWrap());
            alreadyAMember.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    InfoManager.hideInfo();
                    PageFlow.popUp(SignInPageController.getInstance());
                }
            });
        }
        return alreadyAMember;
    }


    public SignUpPanel getSignupPanel() {
        if (signupPanel == null) {
            signupPanel = new SignUpPanel();
        }
        return signupPanel;
    }

    public VerticalComponent getSignUpWrapper() {
        if (signUpWrapper == null) {
            signUpWrapper = new VerticalComponent();
            signUpWrapper.add(getSignupPanel(), new Layout17(null, "100%").add(P.BACKGROUND_E1));
        }
        return signUpWrapper;
    }

    public void removeErrors() {
        if (valComponent != null) {
            valComponent.removeFromParent();
            for (DataContainer dc : getSignupPanel().getTable().getDataContainerChildren()) {
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

    protected void setSubPageHolder(Page subPage) {
        //ignore
    }
    

}
