package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.gui.rtc.RTCLayoutFac2;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.model.tournament.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 *
 */
public class EnterResultsPage extends FlowPanelPage<EnterResultsPageController> {

    private VerticalComponent content;
    private SetupPanel setupPanel;
    private AllResultsPanel allResultsPanel;
    private TournamentListener<TournamentStateChangedEvent> stateListener = new TournamentListener<TournamentStateChangedEvent>() {
        public void onChange(TournamentStateChangedEvent event) {
            repaint();
        }
    };

    public EnterResultsPage() {
        super();
        //content = new VerticalComponent();
        //add(content);
    }

    public void init() {
        /*RTC.getInstance().getTournament().addStateListener(stateListener);

        StyleIt.add(content, RTCLayoutFac2.CONTENT);
        content.add(getSetupPanel());
        content.add(getAllResultsPanel(), new TextLayout(null, "100%"));
        repaint();
*/

    }

    protected void setSubPageHolder(Page subPage) {
        //ignore
    }


    public SetupPanel getSetupPanel() {
        if (setupPanel == null) {
            setupPanel = new SetupPanel();
        }
        return setupPanel;
    }


    public void showMatchesPanel() {
        getSetupPanel().setVisible(false);
        getAllResultsPanel().setVisible(true);
    }

    public void showSetupPanel(boolean hasTeams, boolean hasLevels) {
        getAllResultsPanel().setVisible(false);
        getSetupPanel().setVisible(true);
        getSetupPanel().show(hasTeams, hasLevels);
    }

    public AllResultsPanel getAllResultsPanel() {
        if (allResultsPanel == null) {
            allResultsPanel = new AllResultsPanel();
        }
        return allResultsPanel;
    }


    public void repaint() {
        Tournament t = RTC.getInstance().getTournament();
        if (t.isNotReady()) {
            showSetupPanel(t.hasTeams(), t.hasLevels());
        }
        else {
            showMatchesPanel();
        }
    }


}