package com.bracketbird.client.gui.main.personal.settings;


import com.bracketbird.client.*;
import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.service.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class ContactSettings extends EnterSettingsComponent {
    private VerticalComponent content;

    public StringContainer FIRST_NAME = new StringContainer("First name", true);
    public StringContainer LAST_NAME = new StringContainer("Last name", true);
    public StringContainer NICK_NAME = new StringContainer("Nick name", false);


    public StringContainer PHONE = new StringContainer("Phone", false);
    public StringContainer EMAIL = new StringContainer("Email", true);

    private FlexTableComponent table;
    private AddressPanel address;



    public ContactSettings() {
        super();
        doInit();
    }

    private void doInit() {
        User user = UserManager.getInstance().getUser();
        FIRST_NAME.setValue(user.getFirstName());
        LAST_NAME.setValue(user.getLastName());
        NICK_NAME.setValue(user.getNickName());
        EMAIL.setValue(user.getEmail());
        PHONE.setValue(user.getPhone());

        Layout17 ll = new TextLayout(8, 0, 0, 0, Horizontal.RIGHT).sizeSmall().colorBase();

        Layout17 tbl = new TextLayout("26px", "196px", Vertical.MIDDLE).sizeH3().add(P.VERTICAL_ALIGN_MIDDLE).colorBaseDark();
        Layout17 tblarger = new TextLayout("26px", "406px", Vertical.MIDDLE).sizeH3().add(P.VERTICAL_ALIGN_MIDDLE);
        getTable();
        
        table.setLabel(0, 0, FIRST_NAME, ll);
        table.setLabel(0, 1, LAST_NAME, ll);
        table.setLabel(0, 2, NICK_NAME, ll);

        table.setWidget(1, 0, FIRST_NAME, tbl);
        table.setWidget(1, 1, LAST_NAME, tbl);
        table.setWidget(1, 2, NICK_NAME, tbl);

        table.setLabel(2, 0, EMAIL, ll);
        table.setLabel(2, 2, PHONE, ll);

        table.setWidget(3, 0, EMAIL, tblarger);
        table.setWidget(3, 1, PHONE, tbl);
        table.getFlexCellFormatter().setColSpan(3, 0, 2);
        table.getPanel().setCellSpacing(2);
        EMAIL.setEnable(false);

    }

    public void save() {
        User user = UserManager.getInstance().getUser();
        user.setFirstName(FIRST_NAME.getValue());
        user.setLastName(LAST_NAME.getValue());
        user.setNickName(NICK_NAME.getValue());
        user.setPhone(PHONE.getValue());
        BBService.updateUser(user, new CallBack<VoidResult>() {
            @Override
            public void success(VoidResult result) {
                InfoManager.showSucces("Contact settings saved vith succes");
            }

            @Override
            public void fail(Throwable t) {
            }
        });
    }

    public VerticalComponent getContent() {
        if (content == null) {
            content = new VerticalComponent();
            content.add(getTable(), new TextLayout());
        }
        return content;
    }

    public AddressPanel getAddress() {
        if (address == null) {
            address = new AddressPanel();
        }
        return address;
    }

    public FlexTableComponent getTable() {
        if (table == null) {
            table = new FlexTableComponent();

        }
        return table;
    }

    public String getHeaderText() {
        return "Contact settings";
    }

    public Collection<DataContainer> getDataContainerChildren() {
        return table.getDataContainerChildren();
    }
}