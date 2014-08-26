package com.bracketbird.client.gui.main;


import com.bracketbird.clientcore.appcontrol.FlowPanelPage;
import com.bracketbird.clientcore.appcontrol.Page;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.KeyUtil;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 *
 */
public class CreateTournamentPage extends FlowPanelPage<CreateTournamentPageController> {

    private VerticalComponent content;
    private TextBoxComponent textBox;
    private LabelComponent label;
    private ButtonPanel buttonPanel;

    public CreateTournamentPage() {
        super();
        this.content = new VerticalComponent();
        add(content);
    }

    public void init() {
        content.add(getLabel(), new TextLayout(0, 0, 12, 0).sizeH1().colorBaseDark().noWrap());
        content.add(getTextBox(), new TextLayout(0, 0, 0, 0, "30px","360px", Vertical.MIDDLE).sizeH2().add(P.VERTICAL_ALIGN_MIDDLE).colorBaseDark());
        content.add(getButtonPanel(), LayoutFac.button().margin(20, 0, 0, 0).horizontalAlignRight().verticalAlignMiddel());

        //TextLayout tl = new TextLayout().border(1).borderColor(P.COLOR_DARK_GREY);
        //StyleIt.add(content, tl);
    }

    public TextBoxComponent getTextBox() {
        if (textBox == null) {
            textBox = new TextBoxComponent();
            textBox.getTextBox().addKeyDownHandler(new KeyDownHandler() {
                public void onKeyDown(KeyDownEvent e) {
                    if (e.getNativeKeyCode() == KeyUtil.ENTER) {                        
                        getController().createTournament();
                    }
                }
            });
        }
        return textBox;
    }

    public LabelComponent getLabel() {
        if (label == null) {
            label = new LabelComponent("Name your new tournament");
        }
        return label;
    }

    public ButtonPanel getButtonPanel() {
        if (buttonPanel == null) {

            ButtonComponent ok = new ButtonComponent("Create");
            ok.getElement().setId("createTournamentOK");
            ok.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    getController().createTournament();
                }
            });
            buttonPanel = new ButtonPanel(Horizontal.RIGHT, ok);
        }

        return buttonPanel;
    }

    public VerticalComponent getContent() {
        return content;
    }

    protected void setSubPageHolder(Page subPage) {
        //ignore
    }
}

