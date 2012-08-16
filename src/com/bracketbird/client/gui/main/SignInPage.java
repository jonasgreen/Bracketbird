package com.bracketbird.client.gui.main;


import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.validation.*;

/**
 *
 */
public class SignInPage extends Page<SignInPageController> {

    private VerticalComponent content;
    private SignInPanel signInPanel;
    private VerticalComponent signInWrapper;
    private HyperlinkLabelComponent createAnAccount;
    private SimplePanelComponent problemHolder;
    private ValidationComponent valComponent;


    public SignInPage() {
        super();
        this.content = new VerticalComponent();
        initWidget(content);
    }

    public void init() {

        Layout17 ll = new TextLayout(0, 0, 12, 0).sizeH1().bold().colorBaseDark();

        content.add(new LabelComponent("Login"), ll);
        content.add(getProblemHolder(), new Layout17(0, 0, 0, 0, null, "310px"));
        content.add(getSignInWrapper());

        HorizontalComponent hc = new HorizontalComponent();
        hc.add(getButtonPanel(), LayoutFac.button().margin(0, 10, 0, 0).horizontalAlignLeft().verticalAlignMiddel());
        hc.add(getCreateAnAccount(), new TextLayout(Vertical.MIDDLE));
        content.add(hc, new TextLayout(20, 0, 0, 4, Vertical.BOTTOM));
    }

    private ButtonPanel getButtonPanel() {


        ButtonComponent b = new ButtonComponent("Login");
        b.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                getController().signIn();
            }
        });

        ButtonComponent fP = new ButtonComponent("Forgot password");
        fP.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                getController().forgotPassword();
            }
        });
        ButtonPanel panel = new ButtonPanel(Horizontal.LEFT, b, fP);

        return panel;
    }

    public HyperlinkLabelComponent getCreateAnAccount() {
        if (createAnAccount == null) {
            createAnAccount = new HyperlinkLabelComponent("Create free account", new TextLayout().sizeSmall().bold().colorBlue().underline().noWrap());
            createAnAccount.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    InfoManager.hideInfo();
                    PageFlow.popUp(SignUpPageController.getInstance());
                }
            });
        }
        return createAnAccount;
    }


    public VerticalComponent getSignInWrapper() {
        if (signInWrapper == null) {
            signInWrapper = new VerticalComponent();
            signInWrapper.add(getSignInPanel(), new TextLayout(null, "100%"));
        }
        return signInWrapper;
    }


    public SignInPanel getSignInPanel() {
        if (signInPanel == null) {
            signInPanel = new SignInPanel();

            KeyDownHandler keyDHandler = new KeyDownHandler() {
                public void onKeyDown(KeyDownEvent event) {
                    if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                        getController().signIn();
                    }
                }
            };
            signInPanel.PASSWORD.addKeyDownHandler(keyDHandler);
            signInPanel.EMAIL.addKeyDownHandler(keyDHandler);
            signInPanel.REMEMBER_ME.addKeyDownHandler(keyDHandler);
        }
        return signInPanel;
    }

    public void removeErrors() {
        if (valComponent != null) {
            valComponent.removeFromParent();
            for (DataContainer dc : getSignInPanel().getDataContainerChildren()) {
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


    public SimplePanelComponent getProblemHolder() {
        if (problemHolder == null) {
            problemHolder = new SimplePanelComponent();
        }
        return problemHolder;
    }

    public VerticalComponent getContent() {
        return content;
    }

    protected void setSubPageHolder(Page subPage) {
        //ignore
    }
}

