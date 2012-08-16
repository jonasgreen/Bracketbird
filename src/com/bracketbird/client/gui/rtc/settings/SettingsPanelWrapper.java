package com.bracketbird.client.gui.rtc.settings;


import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

import java.util.*;

/**
 *
 */
public class SettingsPanelWrapper extends VerticalComponent {

    private ErrorPanel errorPanel;
    private Collection<DataContainer> dataContainers;
    private SettingsPanel settingsPanel;

    public SettingsPanelWrapper(SettingsPanel sc, boolean showWarn, ButtonComponent... buttons) {
        super();
        this.settingsPanel = sc;
        this.dataContainers = sc.getDataContainerChildren();
        this.errorPanel = new ErrorPanel(dataContainers);

        HorizontalComponent header = new HorizontalComponent();
        header.add(new LabelComponent(sc.getName()), new TextLayout(Vertical.MIDDLE).sizeH1().colorBaseDark().bold());
        add(header, new TextLayout().padding(20));
        add(errorPanel, new TextLayout(0, 20, 10, 20));
        add(sc, new TextLayout(0, 20, 0, 20));

        if (showWarn) {
            TextLayout tl = new TextLayout().paddingLeft(20).paddingRight(20).paddingTop(2).paddingBottom(2).sizeSmall().colorBaseDark();
            VerticalComponent vc = new VerticalComponent();
            vc.add(new LabelComponent("ATTENTION!"), tl.clone().paddingTop(6));
            vc.add(new LabelComponent("Any changes made will reset all matches in this "), tl);
            vc.add(new LabelComponent("and the following stages of the tournament."), tl.clone().paddingBottom(6));
            add(vc, new TextLayout(0, 0, 0, 0, null, "100%").backgroundCompl().borderTop(1).borderBottom(1).borderColor(Color.backgroundBaseDark()));
        }
        add(new ButtonPanel(buttons), new TextLayout(null, "100%").padding(20));

    }

    public boolean validate() {
        return errorPanel.validate();
    }

    public void clear() {
        errorPanel.clear();
        settingsPanel.clear();
    }

    public void setFocus(boolean focus) {
        if (dataContainers.isEmpty()) {
            return;
        }
        dataContainers.iterator().next().setFocus(focus);
    }

}
