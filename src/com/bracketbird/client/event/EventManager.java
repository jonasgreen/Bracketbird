package com.bracketbird.client.event;


import com.bracketbird.client.model.*;
import com.google.gwt.event.shared.*;

/**
 *
 */
public class EventManager {
    public static HandlerManager eventBus = new HandlerManager(null);


    public static void tournamentIsCreated() {
        eventBus.fireEvent(new TournamentCreatedEvent());
    }

    public static void onTournamentCreation(TournamentCreatedHandler h) {
        //noinspection unchecked
        eventBus.addHandler(TournamentCreatedEvent.TYPE, h);
    }


    public static void tournamentIsDeleted() {
        eventBus.fireEvent(new TournamentDeletedEvent());
    }

    public static void onTournamentDeletion(TournamentDeletedHandler h) {
        //noinspection unchecked
        eventBus.addHandler(TournamentDeletedEvent.TYPE, h);
    }

    public static void onUserStateChange(UserStateChangedHandler h) {
        eventBus.addHandler(UserStateChangedEvent.TYPE, h);
    }

    public static void userStateChanged(User user, UserStateConstant newState, UserStateConstant oldState) {
        eventBus.fireEvent(new UserStateChangedEvent(user, newState, oldState));
    }


    public static void templateIsCreated() {
        eventBus.fireEvent(new TemplateCreatedEvent());
    }

    public static void onTemplateIsCreation(TemplateCreatedHandler h) {
        //noinspection unchecked
        eventBus.addHandler(TemplateCreatedEvent.TYPE, h);
    }

}
