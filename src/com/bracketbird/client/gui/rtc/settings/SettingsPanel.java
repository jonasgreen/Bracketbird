package com.bracketbird.client.gui.rtc.settings;


//import com.bracketbird.client.model.tournament.*;

import com.bracketbird.client.model.tournament.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public abstract class SettingsPanel extends VerticalComponent {


    protected TextLayout labelLayout = new TextLayout(null, "240px", Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().bold().colorBaseDark().padding(6, 0, 2, 0);

    protected TextLayout contentStringLayout = new TextLayout(0, 0, 0, 10, "18px", "240px", Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().padding(2).colorBaseDark();
    protected TextLayout contentLayout = new TextLayout(0, 0, 0, 10, Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().padding(2).colorBaseDark();
    public TextLayout contentListLayout = new TextLayout(0, 0, 0, 10, Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().padding(2).colorBaseDark();
    protected TextLayout contentAddRemoveListLayout = new TextLayout(0, 0, 0, 10, Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().padding(2).colorBaseDark();

    protected TextLayout contentIntegerLayout = new TextLayout(0, 0, 0, 10, "18px", "50px", Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().padding(2).alignRight().colorBaseDark();
    protected TournamentLevel level;


    private String name;
    private VerticalComponent settingsPanel;
    private VerticalComponent advSettingsPanel;
    private HyperlinkLabelComponent showAdvLabel;
    private VerticalComponent advancedSettingsContent;

    protected SettingsPanel(String name, TournamentLevel level) {
        this.name = name;
        this.level = level;
        int paddingLeft = 0;

        VerticalComponent content = new VerticalComponent();
        add(content, new TextLayout(null, "100%").padding(0, 0, 30, 0).paddingLeft(paddingLeft));
        content.add(getSettingsPanel());
        //content.add(getAdvSettingsPanel());
        getAdvancedSettingsContent().setVisible(false);
    }


    public VerticalComponent getAdvSettingsPanel() {
        if (advSettingsPanel == null) {
            advSettingsPanel = new VerticalComponent();
            TextLayout tl = new TextLayout().sizeEkstraSmall().colorBaseDark().padding(20, 0, 10, 0).italic().underline();
            HorizontalComponent hc = new HorizontalComponent();
            hc.add(getShowAdvLabel(), tl);

            hc.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    getAdvancedSettingsContent().setVisible(!getAdvancedSettingsContent().isVisible());
                    getShowAdvLabel().getLabel().setText(getAdvancedSettingsContent().isVisible() ? "Hide advanced settings" : "Show advanced settings");
                }
            });

            advSettingsPanel.add(hc);
            advSettingsPanel.add(getAdvancedSettingsContent());

        }
        return advSettingsPanel;
    }

    public VerticalComponent getAdvancedSettingsContent() {
        if (advancedSettingsContent == null) {
            advancedSettingsContent = new VerticalComponent();
        }
        return advancedSettingsContent;
    }


    public VerticalComponent getSettingsPanel() {
        if (settingsPanel == null) {
            settingsPanel = new VerticalComponent();
        }
        return settingsPanel;
    }

    public HyperlinkLabelComponent getShowAdvLabel() {
        if (showAdvLabel == null) {
            showAdvLabel = new HyperlinkLabelComponent("Show advanced settings");
        }
        return showAdvLabel;
    }

    public abstract Integer getType();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addFlexTable(DataContainer... dcs) {
        addFlexTable(getSettingsPanel(), dcs);
    }

    public void addAdvFlexTable(DataContainer... dcs) {
        addFlexTable(getAdvancedSettingsContent(), dcs);
    }

    private void addFlexTable(CellComponent cc, DataContainer... dcs) {
        FlexTableComponent ft = new FlexTableComponent();
        int i = 0;

        for (DataContainer dc : dcs) {
            ft.setLabel(i, 0, dc, labelLayout);
            if(dc instanceof AddRemoveListContainer){
                ft.getFlexCellFormatter().setAlignment(i, 0, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_TOP);
            }

            ft.setWidget(i++, 1, dc, getContentLayout(dc));
            add(dc);
        }
        cc.add(ft);

    }

    private TextLayout getContentLayout(DataContainer dc) {
        if (dc instanceof StringContainer) {
            return contentStringLayout;
        }
        else if (dc instanceof ListContainer) {
            return contentListLayout;
        }
        else if (dc instanceof AddRemoveListContainer) {
            return contentAddRemoveListLayout;
        }
        else if (dc instanceof IntegerContainer) {
            return contentIntegerLayout;
        }
        return contentLayout;
    }

    @Override
    public String toString() {
        return "SettingsComponent{" +
                "name='" + name + '\'' +
                '}';
    }

    public abstract void setValue(LevelSettings ss);

    public abstract LevelSettings getValue();

    public void clear(){
        setValue(level.getStageSettings());
    }

    public TournamentLevel getLevel() {
        return level;
    }

    public void setLevel(TournamentLevel level) {
        this.level = level;
    }
}
