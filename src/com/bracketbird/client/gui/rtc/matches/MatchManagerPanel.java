package com.bracketbird.client.gui.rtc.matches;

import com.bracketbird.clientcore.gui.FlowComponent;
import com.bracketbird.clientcore.gui.LabelComponent;
import com.bracketbird.clientcore.gui.ListContainer;
import com.bracketbird.clientcore.model.PlayingStrategyConstant;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextArea;


/**
 *
 */
public class MatchManagerPanel extends FlowComponent {

    private LabelComponent header;
    private LabelComponent playFieldsLabel;
    private TextArea playFields;

    private LabelComponent playingStrategyLabel;
    public ListContainer<Integer> playingStrategyContainer = new ListContainer<Integer>("list", PlayingStrategyConstant.LIST, 1, false, false);

    private LabelComponent pauseStrategyLabel;
    public ListContainer<Integer> pauseStrategyContainer = new ListContainer<Integer>("list", PlayingStrategyConstant.LIST, 1, false, false);


    public MatchManagerPanel() {
        add(getHeader());
        add(getPlayFieldsLabel());
        add(getPlayFields());
        FlowPanel fl = new FlowPanel();
       // fl.add(get);
        add(fl);
    }

    public LabelComponent getHeader() {
        if (header == null) {
            header = new LabelComponent("Automatically assign play fields to upcoming matches");
        }
        return header;
    }

    public LabelComponent getPlayFieldsLabel() {
        if (playFieldsLabel == null) {
            playFieldsLabel = new LabelComponent("Name your play fields - one pr. line");
        }
        return playFieldsLabel;
    }

    public TextArea getPlayFields() {
        if (playFields == null) {
            playFields = new TextArea();
        }
        return playFields;
    }


}
