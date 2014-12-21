package com.bracketbird.server.services;

import com.bracketbird.client.appcontrol.ApplicationException;
import com.bracketbird.client.appcontrol.SystemException;
import com.bracketbird.client.service.Action;
import com.bracketbird.client.service.FindIn;
import com.bracketbird.client.service.Result;
import com.bracketbird.server.Logger;
import com.bracketbird.server.repository.Repository;
import com.bracketbird.server.repository.TournamentChannelRepository;
import com.bracketbird.server.repository.TournamentRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 *
 */
public class ActionHandlerRegistry {

    //Obs - for performance issues this class could be converted to a relative singleton (relativ to each servlet)
    // because of the synchronization in the bottom at set- and getRequest methods.

    private static Map<FindIn, Repository> repos = new HashMap<FindIn, Repository>();
    private static String baseUrl;

    private static boolean isLoaded = false;
    private static Map<Class, ActionHandler> handlers = new HashMap<Class, ActionHandler>();

    public static synchronized void init(ServletContext servletContext) {
        if (isLoaded) {
            return;
        }
        Logger.init(servletContext);

        //loading constants - a bit ugly
        initRepositories();
        initHandlers();
        isLoaded = true;
    }

    private static void initRepositories() {
        repos.put(FindIn.tournament, new TournamentRepository());
        repos.put(FindIn.tournamentchannel, new TournamentChannelRepository());
    }


    private static void initHandlers() {
        //TOURNAMENT
        add(new CreateTournamentHandler());
        add(new GetTournamentHandler());

        //TOURNAMENT CHANNEL
        add(new CreateChannelTokenHandler());

        //RTC (Running tournament control)
        add(new RTCActionHandler());
        add(new GetREventHandler());

        //EMAIL - SUBSCRIPTIONS

    }

    private static void add(ActionHandler handler) {
        handlers.put(handler.getActionType(), handler);
    }


    public static Result executeAction(Action a) throws ApplicationException {
        try {
            ActionHandler actionHandler = getHandler(a);
            if (actionHandler == null) {
                String s = a.getClass().getName() + " has no ActionHandler";
                Logger.log(s);
                throw new SystemException(s);
            }
            return actionHandler.execute(a);
        }
        catch (Throwable t) {
            String s = "Error caught in Servlet: " + t.getClass().getName();
            Logger.log(s, t);
            if (t instanceof SystemException) {
                throw (SystemException) t;
            }
            if (t instanceof ApplicationException) {
                throw (ApplicationException) t;
            }
            throw new SystemException(t.getMessage());
        }
    }

    private static ActionHandler getHandler(Action a) {
        ActionHandler handler = handlers.get(a.getClass());
        if (handler == null) {
            throw new SystemException("ActionHandler not setup for Action: " + a.getClass());
        }
        return handler;
    }

    public static void setRequestSettings(HttpServletRequest r) {
        if (baseUrl == null) {
            if (r.getServerName().indexOf("http://") != -1) {
                baseUrl = r.getServerName();
            }
            else if (r.getServerName().indexOf("www") != -1) {
                baseUrl = "http://" + r.getServerName();
            }
            else {
                baseUrl = "http://www." + r.getServerName();
            }
        }
    }


}