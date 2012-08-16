package com.bracketbird.client.gui.main;


import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class SignInPanel extends FlexTableComponent {

    public StringContainer EMAIL = new StringContainer("Email", true);
    public PasswordContainer PASSWORD = new PasswordContainer("Password", true);
    public CheckBoxComponent REMEMBER_ME = new CheckBoxComponent("Remember me");

    public SignInPanel() {
        super();
        init();
    }

    private void init() {



        Layout17 ll = new TextLayout(8,0,0,0).sizeSmall().colorBaseDark();

        Layout17 tbl = new TextLayout(0,0,0,0,"30px", "310px", Vertical.MIDDLE).sizeH2().add(P.VERTICAL_ALIGN_MIDDLE).colorBaseDark();

        setLabel(0, 0, EMAIL, ll);
        setWidget(1, 0, EMAIL, tbl);

        setLabel(2, 0, PASSWORD, ll);
        setWidget(3, 0, PASSWORD, tbl);

        setWidget(4, 0, REMEMBER_ME, new TextLayout(10,0,0,0).sizeSmall().colorBaseDark());



        getPanel().setCellSpacing(2);

    }


    public SignInData getValue() {
        SignInData s = new SignInData();
        s.setEmail(EMAIL.getValue());
        s.setPassword(PASSWORD.getValue());
        return s;
    }

    public void setValue(SignInData s) {
        EMAIL.setValue(s.getEmail());
        PASSWORD.setValue(s.getPassword());
    }

    public void clear() {
        clearDataContainerChildren();
    }
}
