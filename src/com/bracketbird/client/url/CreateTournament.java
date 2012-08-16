package com.bracketbird.client.url;

import com.bracketbird.client.applicationstarter.CreateTournamentStarter;
import com.bracketbird.clientcore.model.keys.UserId;
import java.util.Map;


/**
 *
 */
public class CreateTournament implements Command {

    public static String name = "CREATE_TOURNAMENT";

    public void execute(Map<String, String> pMap) {
        String userId = pMap.get(UrlParam.USER_ID);
        new CreateTournamentStarter(userId == null ? null : new UserId(userId)).start();
    }


    public String getName() {
        return name;
    }

}