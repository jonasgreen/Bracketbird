package com.bracketbird.client.gui.rtc.matches;

import com.bracketbird.client.model.Team;
import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.gui.HorizontalComponent;
import com.bracketbird.clientcore.gui.LabelComponent;
import com.bracketbird.clientcore.gui.TextBoxComponent;
import com.bracketbird.clientcore.style.Horizontal;
import com.bracketbird.clientcore.style.P;
import com.bracketbird.clientcore.style.StyleIt;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.util.MouseOver;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;

/**
 *
 */
public class SeedingPanelComponent extends HorizontalComponent {

    private static TextLayout DEFAULT_TEAM_NAME = new TextLayout(null, "70%", Horizontal.LEFT).sizeNormal().padding(6, 0, 0, 6).colorBaseDark().noWrap();
    private static TextLayout DEFAULT_SEEDING = new TextLayout(null, "30%", Horizontal.RIGHT).sizeNormal().colorBaseDark().alignRight().noWrap();

    private LabelComponent teamName;
    private TextBoxComponent seeding;

    private SeedingPanel parent;
    private Team team;

    public SeedingPanelComponent(SeedingPanel parent, Team team) {
        super();
        this.team = team;
        this.parent = parent;
        init();
    }

    private void init() {
        add(getTeamName(), DEFAULT_TEAM_NAME);
        add(getSeeding(), DEFAULT_SEEDING);
        addMouseOverHandler(MouseOver.POINTER);
        addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                setFocus(true);
            }
        });
    }

    public LabelComponent getTeamName() {
        if (teamName == null) {
            teamName = new LabelComponent("");

        }
        return teamName;
    }

    public Team getTeam() {
        return team;
    }

    public TextBoxComponent getSeeding() {
        if (seeding == null) {
            seeding = new TextBoxComponent();
            seeding.addKeyDownHandler(new KeyDownHandler() {
                public void onKeyDown(KeyDownEvent event) {
                    handleKeyEvent(event);
                }
            });
            seeding.getTextBox().addBlurHandler(new BlurHandler() {
                public void onBlur(BlurEvent event) {
                    StyleIt.add(SeedingPanelComponent.this, new TextLayout().borderColor(P.BACKGROUND_F0));


                }
            });
            seeding.getTextBox().addChangeHandler(new ChangeHandler() {
                public void onChange(ChangeEvent event) {
                    int index = parent.getIndexOf(SeedingPanelComponent.this);

                    if (seeding.isEmpty()) {
                        //moves to bottom
                        parent.setSeeding(SeedingPanelComponent.this, 100000);
                    }
                    else {
                        try {
                            int seed = Integer.valueOf(seeding.getText());
                            parent.setSeeding(SeedingPanelComponent.this, seed);
                        }
                        catch (NumberFormatException e) {
                            getSeeding().setText("" + parent.getSeeding(SeedingPanelComponent.this));
                        }
                    }
                    //focus stays
                    parent.setFocus(index);
                }
            });

        }
        return seeding;
    }

    private void handleKeyEvent(KeyDownEvent event) {
        int keyCode = event.getNativeKeyCode();
        boolean modifier = event.isAnyModifierKeyDown();
        if (KeyCodes.KEY_UP == keyCode) {
            if (modifier) {
                parent.moveUp(this);
            }
            else {
                parent.focusPrevious(this);
            }
        }
        else if (KeyCodes.KEY_DOWN == keyCode) {
            if (modifier) {
                parent.moveDown(this);
            }
            else {
                parent.focusNext(this);
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SeedingPanelComponent that = (SeedingPanelComponent) o;

        if (!team.equals(that.team)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return team.hashCode();
    }

    @Override
    public void setFocus(boolean focus) {
        getSeeding().setFocus(true);
        StyleIt.add(this, new TextLayout().borderColor(P.COLOR_BLUE));


    }

    public boolean isEmpty() {
        return getSeeding().isEmpty();
    }
}
