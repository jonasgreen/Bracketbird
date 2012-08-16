package com.bracketbird.client.gui.rtc.matches;

import com.bracketbird.client.gui.rtc.Warning;
import com.bracketbird.client.model.tournament.*;
import com.google.gwt.event.dom.client.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.bracketbird.clientcore.util.*;

/**
 *
 */
public class MatchComponent extends HorizontalComponent implements FocusComponentIntfc, ClickHandler, MouseOutHandler, TournamentListener<MatchEvent> {

    public static TextLayout tLoser = new TextLayout().normal().colorBaseDark();
    public static TextLayout tNormal = new TextLayout().normal().colorBaseDark();
    public static TextLayout tWinnner = new TextLayout().colorDarkBlue();



    //TOD
    private static TextLayout L_ONE = new TextLayout().sizeSmall().colorBaseDark().noWrap().overflowHidden().padding(2, 0, 2, 0);
    private static TextLayout L_TWO = new TextLayout().sizeSmall().colorBaseDark().noWrap().overflowHidden().alignCenter().padding(2, 0, 2, 0);
    private static TextLayout L_THREE = new TextLayout().sizeSmall().colorBaseDark().noWrap().overflowHidden().padding(2, 0, 2, 0);
    private static TextLayout L_FOUR = new TextLayout().sizeSmall().colorBaseDark().noWrap().padding(2, 0, 2, 0);
    private static TextLayout L_FIVE = new TextLayout().sizeSmall().colorBaseDark().noWrap().padding(2, 0, 2, 0);
    private static TextLayout L_FOCUS = new TextLayout("18px", "0px").border(0);

    private Match match;
    private VerticalFocusManager fManager;

    private LabelComponent colOne;
    private LabelComponent colTwo;
    private LabelComponent colThree;
    private LabelComponent colFour;

    private P focusColor = P.BACKGROUND_BLUE;
    private P mouseOverColor = P.BACKGROUND_SUBPAGE_LIGTH;

    private P color = P.BACKGROUND_WHITE;

    private LabelComponent colFive;
    private TextBoxComponent focusComponent;


    public MatchComponent(Match m) {
        super();
        this.match = m;
        m.addMatchChangedListener(this);
        HorizontalComponent content = new HorizontalComponent();
        add(content, new TextLayout(null, "100%"));

        TableHeader.createColumn(content, new LabelComponent(m.getName()), L_ONE, RoundPanel.hBeforeOne);
        TableHeader.createColumn(content, getColOne(), L_ONE, RoundPanel.hOne);
        TableHeader.createColumn(content, getColTwo(), L_TWO, RoundPanel.hTwo);
        TableHeader.createColumn(content, getcolThree(), L_THREE, RoundPanel.hThree);
        TableHeader.createColumn(content, getColFour(), L_FOUR, RoundPanel.hFour);
        TableHeader.createColumn(content, getColFive(), L_FIVE, RoundPanel.hFive);
        TableHeader.createColumn(content, getFocusComponent(), L_FOCUS, new TableHeader("", "2px", 0));

        getColOne().setText(match.getTeamHome().getName());
        getcolThree().setText(match.getTeamOut().getName());

        getColFour().setText(match.resultAsString());
        addDomHandler(this, ClickEvent.getType());
        addDomHandler(this, MouseOutEvent.getType());

        onChange(null);

    }


    public LabelComponent getColOne() {
        if (colOne == null) {
            colOne = new LabelComponent("");
        }
        return colOne;
    }

    public LabelComponent getColTwo() {
        if (colTwo == null) {
            colTwo = new LabelComponent("-");
        }
        return colTwo;
    }

    public LabelComponent getcolThree() {
        if (colThree == null) {
            colThree = new LabelComponent("");
        }
        return colThree;
    }

    public LabelComponent getColFour() {
        if (colFour == null) {
            colFour = new LabelComponent("");
        }
        return colFour;
    }

    public LabelComponent getColFive() {
        if (colFive == null) {
            colFive = new LabelComponent("");
        }
        return colFive;
    }

    public TextBoxComponent getFocusComponent() {
        if (focusComponent == null) {
            focusComponent = new TextBoxComponent();
            focusComponent.getTextBox().addKeyDownHandler(new KeyDownHandler() {
                public void onKeyDown(KeyDownEvent event) {
                    if (KeyUtil.isDownArrow(event.getNativeKeyCode())) {
                        fManager.down(new FocusKeyDownEvent(MatchComponent.this, event.getNativeKeyCode()));
                    }
                    else if (KeyUtil.isUpArrow(event.getNativeKeyCode())) {
                        fManager.up(new FocusKeyDownEvent(MatchComponent.this, event.getNativeKeyCode()));
                    }
                    else if (KeyUtil.isEnter(event.getNativeKeyCode())) {
                        showMatch();
                    }
                    else {
                        getFocusComponent().setText("");
                    }
                }
            });
            focusComponent.getTextBox().addBlurHandler(new BlurHandler() {
                public void onBlur(BlurEvent event) {
                    focusLost();
                }
            });

            focusComponent.getTextBox().addFocusHandler(new FocusHandler() {
                public void onFocus(FocusEvent event) {
                    doFocus(null);
                }
            });


        }
        return focusComponent;
    }


    public boolean isLeavable(int keyCode) {
        return true;
    }

    public void addParent(FocusManager fm) {
        fManager = (VerticalFocusManager) fm;
    }

    public void focusLost() {
        setFocus(false);
        color = P.BACKGROUND_WHITE;
        updateColor();
    }

    private void updateColor() {
        setBackgroundColor(color);
        getFocusComponent().setBackgroundColor(color);

    }


        private void showMatch() {
            if (shouldWarn()) {
                final Warning w = new Warning("Changing the result of this match, will affect the final ranking of this stage and reset any following stages.");
                PopupManager.show(w, new OnClose() {
                    public void onClose() {
                        if (w.isProceed()) {
                            doShowMatch();
                        }
                        else {
                            MatchComponent.this.doFocus(null);
                        }
                    }
                });

            }
            else {
                doShowMatch();
            }
        }

        private void doShowMatch() {
            PopupManager.show(MatchEditor.getInstance(), new OnClose() {
                public void onClose() {
                    MatchComponent.this.doFocus(null);
                }
            });
            MatchEditor.getInstance().showMatch(match);
        }


        private boolean shouldWarn() {
            return match.getLevel().getState() instanceof LevelStateAllMatchesPlayed || match.getLevel().getState() instanceof LevelStateInFinished;


        }

    public void doFocus(FocusKeyDownEvent e) {
        color = focusColor;
        getFocusComponent().setFocus(true);
        fManager.setLastFocus(this);
        updateColor();
    }

    public void onClick(ClickEvent event) {
        doFocus(null);
        showMatch();
    }

    public void onMouseOut(MouseOutEvent e) {
        updateColor();
    }

    public void onMouseOver(MouseOverEvent e) {
        super.onMouseOver(e);
        e.getRelativeElement().getStyle().setProperty("cursor", "pointer");
        if (!color.getValue().equals(focusColor.getValue())) {
            setBackgroundColor(mouseOverColor);
            getFocusComponent().setBackgroundColor(mouseOverColor);
        }
    }


    public void onChange(MatchEvent event) {
        //updating result
        getColFour().setText(match.resultAsString());
        getColFive().setText(match.getField());
        //update teams
        getColOne().setText(match.getTeamHome().getName());
        getcolThree().setText(match.getTeamOut().getName());

        if(match.isFinish()){
            if(match.getWinningTeam().equals(match.getTeamHome())){
                StyleIt.add(getColOne(), tWinnner);
                StyleIt.add(getcolThree(), tLoser);
            }
            else {
                StyleIt.add(getColOne(), tLoser);
                StyleIt.add(getcolThree(), tWinnner);
            }
        }
        else{
            StyleIt.add(getColOne(), tNormal);
            StyleIt.add(getcolThree(), tNormal);
        }
    }
}