package com.bracketbird.client.pages.settings;


import com.bracketbird.client.Css;
import com.bracketbird.client.ErrorPanel;
import com.bracketbird.client.PopupBracketBird;
import com.bracketbird.client.model.tournament.TournamentLevel;
import com.bracketbird.client.validation.GuiValidator;
import com.bracketbird.clientcore.gui.OnClose;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class SettingsPanel extends PopupBracketBird {

    protected GuiValidator validator = new GuiValidator();

    private Button saveButton;
    private Label cancelButton;
    private TournamentLevel level;
    private ErrorPanel errorPanel;

    protected SettingsPanel(TournamentLevel level, OnClose onClose) {
        super(false, true, onClose);
        this.level = level;
        content.add(getErrorPanel());

    }

    protected void addButtons() {
        FlowPanel panel = Css.style(new FlowPanel(), "buttonsPanel", "flex_center_end");

        panel.add(getCancelButton());
        panel.add(getSaveButton());

        content.add(panel);
    }

    public ErrorPanel getErrorPanel() {
        if (errorPanel == null) {
            errorPanel = new ErrorPanel();
        }
        return errorPanel;
    }

    public Label getCancelButton() {
        if (cancelButton == null) {
            cancelButton = Css.style(new Label("Cancel"), "secondaryButton", "settingsPanelButton");
            cancelButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    cancel();
                }
            });
        }
        return cancelButton;
    }

    public Button getSaveButton() {
        if (saveButton == null) {
            saveButton = Css.style(new Button("Save"), "primaryButton", "settingsPanelButton");
            saveButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    save();
                }
            });
        }
        return saveButton;
    }

    public TournamentLevel getLevel() {
        return level;
    }

    protected void addErrorStyle(Label l, Widget valueWidget){
        if(l != null){
            l.addStyleName("settingsPanel_error_label");
        }
        if(valueWidget != null){
            valueWidget.addStyleName("settingsPanel_error_value");
        }
    }

    protected void addEmptyLine(){
        FlowPanel fl = new FlowPanel();
        fl.setStyleName("emptyLine");
        fl.add(new Label(""));
        content.add(fl);
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
        content.add(r);
    }
}
