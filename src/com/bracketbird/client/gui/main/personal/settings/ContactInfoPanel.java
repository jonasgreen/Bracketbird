package com.bracketbird.client.gui.main.personal.settings;


import com.bracketbird.client.gui.main.SignInPageController;
import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class ContactInfoPanel extends FlexTableComponent {

    public LabelComponent name = new LabelComponent("Navn:");
    public LabelComponent phone = new LabelComponent("Tlf.nr:");
    public LabelComponent email = new LabelComponent("Email:");

    public LabelComponent nameValue = new LabelComponent("");
    public LabelComponent phoneValue = new LabelComponent("");
    public HtmlComponent emailValue = new HtmlComponent();
    public HorizontalComponent infoValue;


    private User contactInfoUser = null;
    private LabelComponent infoValueText;
    private HyperlinkComponent signInLink;

    public ContactInfoPanel() {
        super();
        init();
    }

    private void init() {
        Layout17 ll = new TextLayout(null, "45px", Horizontal.LEFT).sizeSmall();

        Layout17 lv = new TextLayout(null, "200px", Horizontal.LEFT).sizeSmall();


        setWidget(0, 0, name, ll);

        setWidget(0, 1, nameValue, lv);

        setWidget(1, 0, phone, ll);
        setWidget(1, 1, phoneValue, lv);


        setWidget(2, 0, email, ll);
        setWidget(2, 1, emailValue, lv);

        setWidget(3, 0, getInfoValue(), new Layout17(Horizontal.LEFT));
        getFlexCellFormatter().setColSpan(3, 0, 2);
    }

    public HorizontalComponent getInfoValue() {
        if (infoValue == null) {
            infoValue = new HorizontalComponent();
            Layout17 l = new TextLayout(0, 8, 0, 0, null, Vertical.MIDDLE).sizeSmall();
            infoValue.add(getInfoValueText(), l);
            infoValue.add(getSignInLink(), l);
        }
        return infoValue;
    }

    public LabelComponent getInfoValueText() {
        if (infoValueText == null) {
            infoValueText = new LabelComponent("");
            infoValueText.setBackgroundColor(P.BACKGROUND_RED);

            StyleIt.add(infoValueText, Name.PADDING, "4px");
            StyleIt.add(infoValueText, P.COLOR_WHITE);

        }
        return infoValueText;
    }


    public HyperlinkComponent getSignInLink() {
        if (signInLink == null) {
            signInLink = new HyperlinkComponent();
            signInLink.getHyperlink().setText("log på");
            signInLink.getHyperlink().setTargetHistoryToken(SignInPageController.HISTORY_NAME);
        }
        return signInLink;
    }



}
