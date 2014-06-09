package com.bracketbird.client.gui.rtc.settings;

import com.bracketbird.client.gui.rtc.VisualHelp;
import com.bracketbird.client.gui.rtc.VisualHelpItem;
import com.bracketbird.client.gui.rtc.matches.EnterResultsPageController;
import com.bracketbird.client.model.tournament.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class TournamentSettingsPanel extends VerticalComponent {
    protected TextLayout contentStringLayout = new TextLayout(0, 0, 0, 0, "18px", "240px", Horizontal.LEFT, Vertical.TOP).sizeSmall().colorBaseDark();
    protected TextLayout labelLayout = new TextLayout(null, "160px", Horizontal.LEFT, Vertical.TOP).sizeNormal().colorBaseDark();

    private boolean isTemplate = false;
    private StagePanelHolder stagePanelHolder;
    private LabelComponent clickText;
    private VisualHelp visualHelp;
    private PopupPanel initPopup = new PopupPanel(true);
    private LabelComponent nextButton;

    public TournamentSettingsPanel(boolean template) {
        super();
        isTemplate = template;
        init();
    }

    private void init() {
        FlexTableComponent table = new FlexTableComponent();

        int row = 0;
        
        table.setWidget(row++, 0, new LabelComponent(""), new TextLayout("20px", null));

        table.setWidget(row, 0, new LabelComponent("Stages/Levels"), labelLayout);

        table.setWidget(row++, 1, getStagePanelHolder2(), new TextLayout().sizeSmall().colorC1());
        table.setWidget(row++, 1, getClickText(), new TextLayout("20px", null).sizeH2().colorBaseDark());

        table.setWidget(row++, 0, new LabelComponent(""), new TextLayout("60px", null));


        add(table);
        HorizontalComponent hc = new HorizontalComponent();

        hc.add(getVisualHelp(), new TextLayout(Horizontal.LEFT, Vertical.MIDDLE));
        hc.add(getNextButton(), new TextLayout(Horizontal.RIGHT, Vertical.MIDDLE));
        add(hc, new TextLayout(80, 0, 0, 0, null, "100%"));


    }

    public LabelComponent getNextButton() {
        if (nextButton == null) {
            nextButton = new LabelComponent("Continue >>");
            nextButton.setStyleName("colorbutton4");
            nextButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    PageFlow.show(EnterResultsPageController.getInstance());
                }
            });
        }
        return nextButton;
    }


    public void showInitPopoup() {
        StyleIt.add(initPopup, new TextLayout().border(0));
        initPopup.add(new ImageComponent("img/vhSettingsStages.png"));
        initPopup.setPopupPosition(610, 190);
        initPopup.show();
    }

   
    public LabelComponent getClickText() {
        if (clickText == null) {
            clickText = new LabelComponent("");
        }
        return clickText;
    }

    public StagePanelHolder getStagePanelHolder2() {
        if (stagePanelHolder == null) {
            stagePanelHolder = new StagePanelHolder(this);
        }
        return stagePanelHolder;
    }


    public VisualHelp getVisualHelp() {
        if (visualHelp == null) {
            visualHelp = new VisualHelp();
            visualHelp.add(new VisualHelpItem(getStagePanelHolder2().LEVELS.getGui(), 210, -20, "img/vhSettingsStages.png"));
            visualHelp.add(new VisualHelpItem(getStagePanelHolder2().getStageHolder(), -110, 110, "img/vhSettingsSettings.png"));

        }
        return visualHelp;
    }

    void createStage(TournamentLevel level) {
        getStagePanelHolder2().createStage(level);
    }

    void removeStage() {
        getStagePanelHolder2().removeStage();
    }

    public boolean isEmpty() {
        return getStagePanelHolder2().isEmpty();
    }
}
