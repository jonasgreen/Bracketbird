package com.bracketbird.client.service;


import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.clientcore.model.keys.EntityId;
import com.bracketbird.clientcore.service.EntityIdSingleFinder;
import com.bracketbird.clientcore.service.Finder;
import com.bracketbird.clientcore.service.LongSingleFinder;
import com.bracketbird.clientcore.service.SingleFinder;

/**
 *
 */
public class FinderFac {
    private static String DATASTORE_TYPE = "com.google.appengine.api.datastore.Key";


    public static LongSingleFinder findById(Long id, SingleFinder.Operator operator) {
        return new LongSingleFinder("Long", "id", operator, id);
    }

    public static EntityIdSingleFinder findByEntityId(EntityId entityId) {
        //value is set on server.
        return new EntityIdSingleFinder(DATASTORE_TYPE, entityId.getDataStoreName(), entityId);
    }




    public static Finder findByTournamentAndIdEqualTo(TournamentId tournamentId, Long id) {
        //minus one day to compensate for the fact that events and news are createGroupMatch at hh:mm = 00:00
        return findByEntityId(tournamentId).and(findById(id, SingleFinder.Operator.EQUAL_TO));
    }


    public static Finder findByTournamentAndIdGreaterThan(TournamentId tournamentId, Long id) {
        //minus one day to compensate for the fact that events and news are createGroupMatch at hh:mm = 00:00
        return findByEntityId(tournamentId).and(findById(id, SingleFinder.Operator.GREATER_THAN));
    }




}
