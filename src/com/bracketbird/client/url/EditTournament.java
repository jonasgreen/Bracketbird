package com.bracketbird.client.url;

import com.bracketbird.client.*;
import com.bracketbird.client.gui.main.SinglePageNotice;
import com.bracketbird.client.gui.rtc.RTC;
import com.bracketbird.client.gui.rtc.RunningTournamentPageController;
import com.bracketbird.client.gui.rtc.RunningTournamentTop;
import com.bracketbird.client.gui.rtc.teams.TeamsPageController;
import com.bracketbird.client.model.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.client.service.*;
import com.google.gwt.user.client.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.service.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;



/**
 *
 */
public class EditTournament implements Command {

    public static String name = "EDIT_TOURNAMENT";
    private static GlobalKeyboardHandler gh = new GlobalKeyboardHandler();

    public void execute(Map<String, String> pMap) {
        //Color.setScheme(new ColorSchemeRTC());

        Long invalidDate = Long.valueOf(pMap.get(UrlParam.INVALID_DATE));
        if (new Date().getTime() > invalidDate) {
            SinglePageNotice spn = new SinglePageNotice("Url is no longer valid", "Because of security reasons, this url is only refreshable one hour.");
            spn.getSubtitleLabel().setText("When running a tournament you should not refresh the site. Go to Bracketbird and open the tournament again. No data is lost.");
            spn.show();
            return;
        }

        gh.setupSingleAppKeyboardShortcuts();

        PageFlow.activeController = RunningTournamentPageController.getInstance();

        TournamentId tournamentId = new TournamentId(pMap.get(UrlParam.TOURNAMENT_ID));
        final UserId userId = new UserId(pMap.get(UrlParam.USER_ID));

        StringBuffer sb = new StringBuffer();
        sb.append("UserId:").append(userId);
        sb.append("TournamentId:").append(tournamentId);


    }


    public String getName() {
        return name;
    }

}