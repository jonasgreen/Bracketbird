package com.bracketbird.server.repository;

import com.bracketbird.client.model.keys.TournamentChannelId;
import com.bracketbird.client.model.keys.TournamentId;
import com.bracketbird.client.appcontrol.SystemException;
import com.bracketbird.client.model.keys.EntityId;
import com.bracketbird.server.dao.CounterDao;
import com.bracketbird.server.jdo.RTCEventJDO;
import com.bracketbird.server.jdo.TournamentChannelJDO;
import com.bracketbird.server.jdo.TournamentJDO;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 *
 */
public class KeyFac {

    private KeyFac() {
        super();
    }


    public static Key convert(EntityId id) {
        if(id == null){
            throw new SystemException("EntityId is Null");
        }
        return KeyFactory.stringToKey(id.getEncodedKey());
    }



    //************************//
    //  - TOURNAMNET
    //************************//

    public static Key createTournamentKey() {
        long id = new CounterDao().nextTournamentCounter();
        return createKey(TournamentJDO.class, id);
    }


    //************************//
    // CLUB
    //  - TOURNAMNET
    //      - TOURNAMENT CHANNEL
    //************************//

    public static Key createTournamentChannelKey(TournamentId tId) {
        Key parent = convert(tId);
        long id = new CounterDao().nextTournamentChannelCounter(tId);
        return createChild(parent, TournamentChannelJDO.class, id);
    }



    //************************//
    // USER
    //  - TOURNAMNET
    //      - RTC EVENT
    //************************//

    public static Key createRTCEventKey(TournamentId tId) {
        Key parent = convert(tId);
        long id = new CounterDao().nextRTCEventLogCounter(tId);
        return createChild(parent, RTCEventJDO.class, id);
    }


    //************************//
    // USER
    //  - TOURNAMNET
    //      - RTC EVENT STATE
    //************************//

    public static Key createRTCEventStateKey(TournamentId tId) {
        Key parent = convert(tId);
        long id = new CounterDao().nextRTCEventStateLogCounter(tId);
        return createChild(parent, RTCEventJDO.class, id);
    }


    public static TournamentId getTournamentId(Key key) {
        validate(key, TournamentJDO.class);
        return new TournamentId(KeyFactory.keyToString(key));
    }

    public static TournamentChannelId getTournamentChannelId(Key key){
        validate(key, TournamentChannelJDO.class);
        return new TournamentChannelId(KeyFactory.keyToString(key));
    }

    private static void validate(Key key, Class kind) {
        if (!key.getKind().equals(kind.getSimpleName())) {
            throw new SystemException("Key validation error. Key:" + key + " kind: " + kind.getSimpleName());
        }
    }

    private static Key createKey(Class cl, long id) {
        return KeyFactory.createKey(cl.getSimpleName(), id);

    }

    private static Key createChild(Key parent, Class cl, long id) {
        return new KeyFactory.Builder(parent).addChild(cl.getSimpleName(), id).getKey();
    }

    public static Key stringToKey(String encodedKey) {
        return KeyFactory.stringToKey(encodedKey);
    }
}
