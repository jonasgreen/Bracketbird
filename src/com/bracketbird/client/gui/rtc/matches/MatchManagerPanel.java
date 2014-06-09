package com.bracketbird.client.gui.rtc.matches;

import com.bracketbird.client.gui.grid.Grid;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.model.PauseStrategyConstant;
import com.bracketbird.clientcore.model.PlayingStrategyConstant;
import com.bracketbird.clientcore.style.*;
import com.google.gwt.dom.client.Style;


/**
 *
 */
public class MatchManagerPanel extends FlowComponent {

    protected TextLayout labelLayout = new TextLayout(Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().colorBaseDark().padding(6, 0, 2, 0);

    protected TextLayout textAreaLayout = new TextLayout(0, 0, 20, 0, "140px", "400px", Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().colorBaseDark();
    protected TextLayout contentLayout = new TextLayout(0, 0, 0, 10, Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().padding(2).colorBaseDark();
    public TextLayout contentListLayout = new TextLayout(0, 0, 0, 10, Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().padding(2).colorBaseDark();
    protected TextLayout contentAddRemoveListLayout = new TextLayout(0, 0, 0, 10, Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().padding(2).colorBaseDark();

    protected TextLayout contentIntegerLayout = new TextLayout(0, 0, 0, 10, "18px", "50px", Horizontal.LEFT, Vertical.MIDDLE).sizeSmall().padding(2).alignRight().colorBaseDark();



    //private LabelComponent l = Texts.label("");


    private StringAreaContainer playFields = new StringAreaContainer("Name your play fields - one pr. line", false);
    public ListContainer<Integer> playingStrategyContainer = new ListContainer<Integer>("Assigning strategy", PlayingStrategyConstant.LIST, 1, false, false);
    public ListContainer<Integer> pauseStrategyContainer = new ListContainer<Integer>("Stop assigning", PauseStrategyConstant.LIST, 1, false, false);


    public MatchManagerPanel() {
        setHeight("600px");
        setWidth("400px");
        getElement().getStyle().setPadding(20, Style.Unit.PX);

        add(Texts.title("Automatically assign play fields to upcoming matches"));

        add(playFields.getLabel(), labelLayout);
        add(playFields.getGui(), textAreaLayout);

        Grid grid = new Grid();
        add(grid);
        grid.addRow(50, 50, playingStrategyContainer);
        grid.addRow(50, 50, pauseStrategyContainer);
        //playingStrategyContainer.getGui().getElement().getStyle().setFloat(Style.Float.RIGHT);
        //pauseStrategyContainer.getGui().getElement().getStyle().setFloat(Style.Float.RIGHT);

    }

}
