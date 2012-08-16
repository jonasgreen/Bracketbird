package com.bracketbird.client.gui.main;


import com.bracketbird.client.service.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.validation.*;

/**
 *
 */
public class SignUpPanel extends VerticalComponent {

    public boolean addMember = false;

    public StringContainer FIRST_NAME = new StringContainer("First name", true);
    public StringContainer LAST_NAME = new StringContainer("Last name", true);


    public StringContainer EMAIL = new StringContainer("Email", true);
    public PasswordContainer PASSWORD = new PasswordContainer("Password", true);
    public PasswordContainer REPEAT_PASSWORD = new PasswordContainer("Repeat password", true);
    public CheckBoxComponent REMEMBER_ME = new CheckBoxComponent("Remember me");


    private FlexTableComponent table;
    private CheckBoxComponent newsMail;


    public SignUpPanel() {
        super();
        init();
    }


    private void init() {
        PASSWORD.add(Validator.STRING_MIN_6);
        REPEAT_PASSWORD.add(new PassNotTheSameValidator(PASSWORD));

        this.add(getTable(), new Layout17(0, 0, 10, 0, null, "100%"));
    }


    public CheckBoxComponent getNewsMail() {
        if (newsMail == null) {
            newsMail = new CheckBoxComponent("Yes, let me recieve news.");
            newsMail.getCheckBox().setValue(true);
        }
        return newsMail;
    }

    public FlexTableComponent getTable() {
        if (table == null) {
            table = new FlexTableComponent();


            Layout17 ll = new TextLayout(8,0,0,0,Horizontal.RIGHT).sizeSmall().noWrap().colorBaseDark();

            Layout17 tbl = new TextLayout("30px", "230px", Vertical.MIDDLE).sizeH2().add(P.VERTICAL_ALIGN_MIDDLE).colorBaseDark();
            Layout17 tblarger = new TextLayout("30px", "475px", Vertical.MIDDLE).sizeH2().add(P.VERTICAL_ALIGN_MIDDLE).colorBaseDark();


            table.setLabel(0, 0, FIRST_NAME, ll);
            table.setLabel(0, 1, LAST_NAME, ll);

            table.setWidget(1, 0, FIRST_NAME, tbl);
            table.setWidget(1, 1, LAST_NAME, tbl);

            table.setLabel(2, 0, EMAIL, ll);

            table.setWidget(3, 0, EMAIL, tblarger);
            table.getFlexCellFormatter().setColSpan(3, 0, 2);


            table.setLabel(4, 0, PASSWORD, ll);
            table.setWidget(5, 0, PASSWORD, tbl);

            table.setLabel(6, 0, REPEAT_PASSWORD, ll);
            tbl.setBottomMargin(8);
            table.setWidget(7, 0, REPEAT_PASSWORD, tbl);

            table.setWidget(8, 0, REMEMBER_ME, new TextLayout().sizeSmall().colorBaseDark());
            
            table.getPanel().setCellSpacing(2);

        }
        return table;
    }


    public CreateUser getValue() {
        CreateUser s = new CreateUser();

        s.setFirstName(FIRST_NAME.getValue());
        s.setLastName(LAST_NAME.getValue());
        s.setEmail(EMAIL.getValue());
        s.setPassword(PASSWORD.getValue());

        return s;
    }

    public void setValue(CreateUser s) {
        EMAIL.setValue(s.getEmail());
        PASSWORD.setValue(s.getPassword());
    }

    public void clear() {
        clearDataContainerChildren();
    }

    public boolean isAddMember() {
        return addMember;
    }

    public void setAsAddMember(boolean addMember) {
        this.addMember = addMember;
        REPEAT_PASSWORD.setVisible(!addMember);
        PASSWORD.setVisible(!addMember);
        REMEMBER_ME.setVisible(!addMember);
        getNewsMail().setVisible(!addMember);

    }
}
