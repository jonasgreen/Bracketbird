package com.bracketbird.server.repository;

import com.bracketbird.client.model.TournamentBrick;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.server.dao.*;
import com.bracketbird.server.jdo.*;
import com.bracketbird.server.jdo.club.*;
import com.google.appengine.api.datastore.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.keys.*;

import java.util.*;

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

    public static List<Key> convert(List<MemberId> ids) {
        if (ids == null) {
            return null;
        }
        List<Key> keys = new ArrayList<Key>();
        for (EntityId id : ids) {
            keys.add(convert(id));
        }
        return keys;
    }

    //************************//
    // USER
    //************************//

    public static Key createUserKey(Long userId) {
        return createKey(UserJDO.class, userId);
    }


    //************************//
    //  - TOURNAMNET
    //************************//

    public static Key createTournamentKey() {
        long id = new CounterDao().nextTournamentCounter();
        return createKey(TournamentJDO.class, id);
    }

    //************************//
    // CONSTANT
    //************************//

    public static Key createConstantKey(Long id) {
        return createKey(ConstantJDO.class, id);
    }

    //************************//
    // CLUB
    //************************//            

    public static Key createClubKey(long id) {
        return createKey(ClubJDO.class, id);
    }

    //************************//
    // CLUB
    //  - MEMBER
    //************************//

    public static Key createMemberKey(ClubId clubId) {
        Key parent = convert(clubId);
        long id = new CounterDao().nextMemberCounter(clubId);
        return createChild(parent, MemberJDO.class, id);
    }

    //************************//
    // USER
    //  - TOURNAMENTBRICK
    //************************//

    public static Key createTournamentBrickKey(UserId userId) {
        Key parent = convert(userId);
        long id = new CounterDao().nextTournamentBrickCounter(userId);
        return createChild(parent, TournamentBrickJDO.class, id);
    }



    //************************//
    // CLUB
    //  - WALLEVENT
    //************************//

    public static Key createWallEventKey(ClubId clubId) {
        Key parent = convert(clubId);
        long id = new CounterDao().nextWallEventCounter(clubId);
        return createChild(parent, WallEventJDO.class, id);
    }


    //************************//
    // CLUB
    //  - WALLNEWS
    //************************//

    public static Key createWallNewsKey(ClubId clubId) {
        Key parent = convert(clubId);
        long id = new CounterDao().nextWallNewsCounter(clubId);
        return createChild(parent, WallNewsJDO.class, id);
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
    // CLUB
    //  - TOURNAMNET
    //      - PLAYING TEAM
    //************************//

    public static Key createPlayingTeamKey(TournamentId tId) {
        Key parent = convert(tId);
        long id = new CounterDao().nextPlayingTeamCounter(tId);
        return createChild(parent, PlayingTeamJDO.class, id);
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


    //************************//
    // CLUB
    //  - TOURNAMNET
    //      - SUBTOURNAMNET
    //************************//

    public static Key createSubtournamentKey(TournamentId tournamentId) {
        Key parent = convert(tournamentId);
        long id = new CounterDao().nextSubtournamentCounter(tournamentId);
        return createChild(parent, TournamentLevelJDO.class, id);
    }


    //************************//
    // CLUB
    //  - TOURNAMNET
    //      - SUBTOURNAMENT
    //          - MATCH
    //************************//

    public static Key createMatchKey(TournamentLevelId tournamentLevelId) {
        Key parent = convert(tournamentLevelId);
        long id = new CounterDao().nextMatchCounter(tournamentLevelId);
        return createChild(parent, MatchJDO.class, id);
    }


    public static ConstantId getConstantId(Key key) {
        validate(key, ConstantJDO.class);
        return new ConstantId(KeyFactory.keyToString(key));
    }

    public static TournamentLevelId getSubtournamentId(Key key) {
        validate(key, TournamentLevelJDO.class);
        return new TournamentLevelId(KeyFactory.keyToString(key));
    }

    public static MatchId getMatchId(Key key) {
        validate(key, MatchJDO.class);
        return new MatchId(KeyFactory.keyToString(key));
    }

    public static UserId getUserId(Key key) {
        validate(key, UserJDO.class);
        return new UserId(KeyFactory.keyToString(key));
    }

    public static ClubId getClubId(Key key) {
        validate(key, ClubJDO.class);
        return new ClubId(KeyFactory.keyToString(key));
    }

    public static List<MemberId> getMemberIds(List<Key> keys) {
        List<MemberId> ids = new ArrayList<MemberId>();
        for (Key key : keys) {
            ids.add(getMemberId(key));
        }
        return ids;
    }

    public static MemberId getMemberId(Key key) {
        validate(key, MemberJDO.class);
        return new MemberId(KeyFactory.keyToString(key));
    }


    public static TeamId getPlayingTeamId(Key k) {
        validate(k, PlayingTeamJDO.class);
        return new TeamId(KeyFactory.keyToString(k));
    }

    public static RTCEventId getRTCEventId(Key k) {
        validate(k, RTCEventJDO.class);
        return new RTCEventId(KeyFactory.keyToString(k));
    }

    public static TournamentId getTournamentId(Key key) {
        validate(key, TournamentJDO.class);
        return new TournamentId(KeyFactory.keyToString(key));
    }

    public static TournamentBrickId getTournamentBrickId(Key key){
        validate(key, TournamentBrickJDO.class);
        return new TournamentBrickId(KeyFactory.keyToString(key));
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

    private static Key createChild(Key parent, Class cl, String id) {
        return new KeyFactory.Builder(parent).addChild(cl.getSimpleName(), id).getKey();

    }

    public static Key stringToKey(String encodedKey) {
        return KeyFactory.stringToKey(encodedKey);
    }
}
