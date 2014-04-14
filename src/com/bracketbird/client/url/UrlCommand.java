package com.bracketbird.client.url;

import com.bracketbird.client.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;

/**
 *
 */
public class UrlCommand {

    private static int count = 0;

    private UrlParam urlParam;
    private String baseUrl;

    public static String URL_COMMAND = "URL_COMMAND";

    private static Map<String, Command> commands = new HashMap<String, Command>();

    static {
        commands.put(CreateTournament.name, new CreateTournament());
        commands.put(EditTournament.name, new EditTournament());
    }


    //FACTORY METHODS
    public static UrlCommand createTournament(String baseUrl, CreateTournament command) {
        UrlParam up = getUrlParam(command);
        //up.add(UrlParam.CLUB_ID, UserManager.getInstance().getUser().getPersonalClubId().getEncodedKey());
        //up.add(UrlParam.TOURNAMENT_NAME, tournamentName);
        //up.add(UrlParam.INVALID_DATE, String.valueOf(new Date().getTime()));
        return new UrlCommand(baseUrl, up);
    }

    public static UrlCommand createEditTournament(String baseUrl, EditTournament command, UserId userId, TournamentId tId) {
        UrlParam up = getUrlParam(command);
        up.add(UrlParam.TOURNAMENT_ID, tId.getEncodedKey());
        up.add(UrlParam.INVALID_DATE, String.valueOf(DateUtil.addHoursToDate(new Date(), 1).getTime()));
        return new UrlCommand(baseUrl, up);
    }

    
    private static UrlParam getUrlParam(Command c) {
        UrlParam up = new UrlParam();
        up.add(URL_COMMAND, c.getName());
        return up;
    }


    public UrlCommand(String baseUrl, UrlParam urlParam) {
        this.baseUrl = baseUrl + "#";
        this.urlParam = urlParam;

    }

    public String getUrl() {
        return baseUrl + urlParam.toString();
    }

    public String toString() {
        return getUrl();
    }

    public static void execute(Map<String, String> pMap) {
        Command c = commands.get(pMap.get(URL_COMMAND));
        if (c == null) {
            throw new SystemException("Command object not setup for " + pMap.get(URL_COMMAND));
        }
        c.execute(pMap);
    }


    public static native void openUrl(String url, String name)/*-{
        $wnd.open(url, name);
    }-*/;

    
    public static void openTournament(String tournament, String name) {
        StringBuffer sb = new StringBuffer(name);
        int i = 0;
        while (i < count) {
            sb.append(" ");
            i++;
        }
        count++;
        try {
            openUrl(UrlUtil.getBaseUrl()+"#"+tournament, sb.toString());
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
