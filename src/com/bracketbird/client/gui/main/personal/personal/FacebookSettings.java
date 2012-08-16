package com.bracketbird.client.gui.main.personal.personal;

import com.bracketbird.clientcore.gui.*;

import java.util.*;

/**
 *
 */
public class FacebookSettings extends EnterSettingsComponent {
    private VerticalComponent content;


    public FacebookSettings() {
        super();
    }

    public void save() {

    }

    public VerticalComponent getContent() {
        if (content == null) {
            content = new VerticalComponent();
        }
        return content;
    }

    public String getHeaderText() {
        return "Facebook integration settings";
    }

    public Collection<DataContainer> getDataContainerChildren() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}