package com.bracketbird.client.gui.rtc.matches;

import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.gui.LabelComponent;
import com.bracketbird.clientcore.gui.ListContainer;
import com.bracketbird.clientcore.gui.StringContainer;
import com.bracketbird.clientcore.model.PauseStrategyConstant;
import com.bracketbird.clientcore.model.PlayingStrategyConstant;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextArea;


/**
 *
 */
public class MatchManagerPanel extends FlowComponent {

    private LabelComponent header = new LabelComponent("Automatically assign play fields to upcoming matches");

    private StringContainer playFields = new StringContainer("Name your play fields - one pr. line", true);

    public ListContainer<Integer> playingStrategyContainer = new ListContainer<Integer>("Play", PlayingStrategyConstant.LIST, 1, false, false);
    public ListContainer<Integer> pauseStrategyContainer = new ListContainer<Integer>("Pause", PauseStrategyConstant.LIST, 1, false, false);


    public MatchManagerPanel() {
        setHeight("400px");
        setWidth("600px");
        add(header);

        add(playFields.getLabel());
        add(playFields.getGui());



        FlowPanel fl = new FlowPanel();
        fl.setHeight("40px");
        add(fl);
        fl.add(playingStrategyContainer.getLabel());
        fl.add(playingStrategyContainer.getGui());
        playingStrategyContainer.getLabel().getElement().getStyle().setFloat(Style.Float.LEFT);
        playingStrategyContainer.getGui().getElement().getStyle().setFloat(Style.Float.LEFT);


        add(pauseStrategyContainer.getLabel());
        add(pauseStrategyContainer.getGui());


    }

}
