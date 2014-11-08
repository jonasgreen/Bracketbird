package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.KeyUtil;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;

/**
 *
 */
public class MatchEditor extends VerticalComponent implements KeyDownHandler {

    private Match match;
    private static MatchEditor instance;

    private ButtonComponent saveButton;
    private ButtonComponent cancel;

    private HorizontalComponent buttons;
    private HorizontalComponent controlPanel;

    private LabelComponent teamNameHome;
    private LabelComponent teamNameOut;
    private HorizontalComponent teamNames;

    private SetEditor setEditor;
    private boolean drawAllowed = false;

    private TextLayout illegalLayout = new TextLayout();

    private TextLayout playersNormalLayout = new TextLayout().opacityNormal();
    private TextLayout playersLooseLayout = new TextLayout().opacityMiddle();

    private String stateErrorMessage;
    private LabelComponent errorMessage;


    private MatchEditor() {
        super();
        setEditor = new SetEditor();

        add(getTeamNames(), new TextLayout(null, "100%"));
        add(new LabelComponent("Result:"), new TextLayout(5, 0, 4, 10).sizeSmall().colorBaseDark().italic());

        add(setEditor, new TextLayout(null, "100%").padding(10).paddingRight(20));

        add(getControlPanel(), new TextLayout(0, 0, 0, 0, null, "100%").padding(10).paddingTop(30));

        //setBackgroundColor(P.BACKGROUND_BLUE);

        StyleIt.add(getTeamNameOut(), playersNormalLayout);
        StyleIt.add(getTeamNameHome(), playersNormalLayout);

        addDomHandler(this, KeyDownEvent.getType());

        setWidth("600px");
        StyleIt.add(this, new TextLayout());
    }


    public HorizontalComponent getTeamNames() {
        if (teamNames == null) {
            teamNames = new HorizontalComponent();
            teamNames.add(getTeamNameHome(), new TextLayout(Horizontal.LEFT, Vertical.MIDDLE).sizeH1().colorBaseDark().padding(10).paddingRight(20));
            teamNames.add(getTeamNameOut(), new TextLayout(Horizontal.RIGHT, Vertical.MIDDLE).sizeH1().colorBaseDark().padding(10).paddingLeft(20));
        }
        return teamNames;
    }

    public LabelComponent getTeamNameHome() {
        if (teamNameHome == null) {
            teamNameHome = new LabelComponent("");
        }
        return teamNameHome;
    }

    public LabelComponent getTeamNameOut() {
        if (teamNameOut == null) {
            teamNameOut = new LabelComponent("");
        }
        return teamNameOut;
    }


    public HorizontalComponent getControlPanel() {
        if (controlPanel == null) {
            controlPanel = new HorizontalComponent();
            controlPanel.add(getErrorMessage(), new TextLayout(Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().colorRed().noWrap());

            //buttons
            SimplePanelComponent sp = new SimplePanelComponent();
            sp.add(getButtons(), new TextLayout(Horizontal.RIGHT, Vertical.TOP));
            controlPanel.add(sp, new TextLayout(null, "100%", Horizontal.RIGHT, Vertical.MIDDLE));

        }
        return controlPanel;
    }

    public LabelComponent getErrorMessage() {
        if (errorMessage == null) {
            errorMessage = new LabelComponent("");
        }
        return errorMessage;
    }


    public HorizontalComponent getButtons() {
        if (buttons == null) {
            buttons = new HorizontalComponent();
            buttons.add(getSaveButton(), new TextLayout(0, 0, 0, 0, Vertical.TOP).sizeSmall().colorBaseDark());
            buttons.add(getCancelButton(), new TextLayout(0, 0, 0, 10, Vertical.TOP).sizeSmall().colorBaseDark());
        }
        return buttons;
    }

    public ButtonComponent getSaveButton() {
        if (saveButton == null) {
            saveButton = new ButtonComponent("Save");
            saveButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    save();
                }
            });
        }
        return saveButton;
    }

    public ButtonComponent getCancelButton() {
        if (cancel == null) {
            cancel = new ButtonComponent("Cancel");
            cancel.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent clickEvent) {
                    PopupManager.hide();
                }
            });
        }
        return cancel;
    }

    public static MatchEditor getInstance() {
        if (instance == null) {
            instance = new MatchEditor();
        }
        return instance;
    }


    public void showMatch(Match m) {
        reset();
        drawAllowed = !m.getLevel().isKnockout();

        this.match = m;
        getTeamNameHome().setText(match.getTeamHome().getName());
        getTeamNameOut().setText(match.getTeamOut().getName());
        getSaveButton().getButton().setEnabled(false);
        setEditor.load(m.getResult());
        setFocus(true);

    }

    public void setFocus(boolean b) {
        setEditor.setFocus(true);
    }

    public void reset() {
        stateErrorMessage = "";
        getErrorMessage().setText(stateErrorMessage);

        StyleIt.add(getTeamNameOut(), playersNormalLayout);
        StyleIt.add(getTeamNameHome(), playersNormalLayout);
        getSaveButton().getButton().setEnabled(false);
        StyleIt.add(setEditor, Name.BORDER, "NONE");
        StyleIt.add(getErrorMessage(), new TextLayout().bold().add(Color.textCompl()));

    }


    public void matchHomeIsWinning() {
        reset();
        StyleIt.add(getTeamNameOut(), playersLooseLayout);

        getSaveButton().getButton().setEnabled(true);
        getErrorMessage().setText("");
        StyleIt.add(getErrorMessage(), new TextLayout().colorBaseDark());

    }


    public void matchIsReset() {
        reset();
        getSaveButton().getButton().setEnabled(true);
    }


    public void matchOutIsWinning() {
        reset();
        StyleIt.add(getTeamNameHome(), playersLooseLayout);
        getSaveButton().getButton().setEnabled(true);

        getErrorMessage().setText("");
        StyleIt.add(getErrorMessage(), new TextLayout().colorBaseDark());
    }

    public void matchIsUnknown() {
        reset();
        stateErrorMessage = "Scoresheet indicates a non finished match";
    }

    public void matchIsDraw() {
        reset();
        if (drawAllowed) {
            //ok - ignore
        }
        else {
            stateErrorMessage = "Draw is not allowed";
            getErrorMessage().setText(stateErrorMessage);
            getSaveButton().getButton().setEnabled(false);
        }
    }

    public void matchIsIllegal() {
        reset();
        getSaveButton().getButton().setEnabled(false);

        StyleIt.add(setEditor, illegalLayout);
        stateErrorMessage = "Scoresheet is illegal";
        getErrorMessage().setText(stateErrorMessage);
    }


    public void onKeyDown(KeyDownEvent event) {
        if (KeyUtil.isEnter(event.getNativeKeyCode())) {
            save();
        }
    }

    private void save() {

    }


}