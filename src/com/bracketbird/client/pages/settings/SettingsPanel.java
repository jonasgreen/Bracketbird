package com.bracketbird.client.pages.settings;


import com.bracketbird.client.ErrorPanel;
import com.bracketbird.client.PopupBracketBird;
import com.bracketbird.client.model.tournament.TournamentLevel;
import com.bracketbird.client.validation.GuiValidator;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class SettingsPanel extends PopupBracketBird {

    protected GuiValidator validator = new GuiValidator();

    private TournamentLevel level;
    private ErrorPanel errorPanel;

    protected SettingsPanel(TournamentLevel level) {
        super(false, true);
        this.level = level;
        getContentPanel().add(getErrorPanel());
        addStyleName("settingsPanel");
    }

    public ErrorPanel getErrorPanel() {
        if (errorPanel == null) {
            errorPanel = new ErrorPanel();
        }
        return errorPanel;
    }


    public TournamentLevel getLevel() {
        return level;
    }

    protected void addEmptyLine(){
        FlowPanel fl = new FlowPanel();
        fl.setStyleName("emptyLine");
        fl.add(new Label(""));
        getContentPanel().add(fl);
    }

    protected void addRow(Label label, Widget widget){
        FlowPanel r = new FlowPanel();
        r.setStyleName("labelValueRow");
        r.addStyleName("flex_alignItems_center");
        r.addStyleName("flex_justifyContent_spaceBetween");

        r.add(label);
        label.addStyleName("settingsLabel");

        widget.addStyleName("settingsValue");
        r.add(widget);
        getContentPanel().add(r);
    }
}
