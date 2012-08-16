package com.bracketbird.client.gui.main.personal.settings;

import com.bracketbird.client.*;
import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.service.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.validation.*;

import java.util.*;

/**
 *
 */
public class PasswordSettings extends EnterSettingsComponent {
    private VerticalComponent content;

    public PasswordContainer OLD_PASSWORD = new PasswordContainer("Your password", true);
    public PasswordContainer NEW_PASSWORD = new PasswordContainer("New password", true);
    public PasswordContainer REPEAT_NEW_PASSWORD = new PasswordContainer("Repeat new password", true);

    private FlexTableComponent table;



    public PasswordSettings() {
        super();
        doInit();
    }

    private void doInit() {
        User user = UserManager.getInstance().getUser();
        OLD_PASSWORD.add(new PassDoesNotMatchExistingValidator(user.getPassword()));
        REPEAT_NEW_PASSWORD.add(new PassNotTheSameValidator(NEW_PASSWORD));
        NEW_PASSWORD.add(Validator.STRING_MIN_6);


        Layout17 ll = new TextLayout(8, 0, 0, 0, Horizontal.RIGHT).sizeSmall().colorBase();

        Layout17 tbl = new TextLayout("26px", "196px", Vertical.MIDDLE).sizeH3().add(P.VERTICAL_ALIGN_MIDDLE).colorBaseDark();
        getTable();

        table.setLabel(0, 0, OLD_PASSWORD, ll);
        table.setWidget(1, 0, OLD_PASSWORD, tbl);

        table.setLabel(2, 0, NEW_PASSWORD, ll);
        table.setWidget(3, 0, NEW_PASSWORD, tbl);

        table.setLabel(4, 0, REPEAT_NEW_PASSWORD, ll);
        table.setWidget(5, 0, REPEAT_NEW_PASSWORD, tbl);


        table.getPanel().setCellSpacing(2);



    }

    public void save() {
        User user = UserManager.getInstance().getUser();
        user.setPassword(NEW_PASSWORD.getValue());
        BBService.updateUser(user, new CallBack<VoidResult>() {
            @Override
            public void success(VoidResult result) {
                InfoManager.showSucces("Your password was changed with succes.");
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

    public FlexTableComponent getTable() {
        if (table == null) {
            table = new FlexTableComponent();

        }
        return table;
    }

    public String getHeaderText() {
        return "Change password";
    }

    public Collection<DataContainer> getDataContainerChildren() {
        return table.getDataContainerChildren();
    }

}
