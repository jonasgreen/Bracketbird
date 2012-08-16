package com.bracketbird.server.dao;

import com.bracketbird.client.model.keys.*;
import com.bracketbird.server.*;
import com.bracketbird.server.jdo.club.*;
import com.google.appengine.api.datastore.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.keys.*;

import javax.jdo.*;
import java.util.*;

/**
 *
 */
public class CounterDao {

    public enum CounterType {
        club,
        constant,
        user,
        match,
        playingteam,
        member,
        tournament,
        tournamentbrick,
        tournamentchannel,
        subtournament,
        wallevent,
        wallnews,
        wallmessage,
        rtceventlog,
        rtceventstate
    }


    private Class<CounterJDO> jdoClass = CounterJDO.class;

    public CounterDao() {
    }



    public long nextUserCounts(){
        return next(CounterType.user);
    }

    public long nextClubCounts(){
        return next(CounterType.club);
    }

    public long nextConstantCounts(){
        return next(CounterType.constant);
    }

    public long nextTournamentCounter() {
        return next(CounterType.tournament);
    }

    public long nextTournamentBrickCounter(UserId id) {
        return next(id, CounterType.tournamentbrick);
    }

    public long nextSubtournamentCounter(TournamentId id) {
        return next(id, CounterType.subtournament);
    }

    public long nextMatchCounter(TournamentLevelId id) {
        return next(id, CounterType.match);
    }

    public long nextPlayingTeamCounter(TournamentId id) {
        return next(id, CounterType.playingteam);
    }


    public long nextTournamentChannelCounter(TournamentId id) {
        return next(id, CounterType.tournamentchannel);
    }

    public long nextRTCEventLogCounter(TournamentId id) {
        return next(id, CounterType.rtceventlog);
    }


    public long nextRTCEventStateLogCounter(TournamentId id) {
        return next(id, CounterType.rtceventstate);
    }

    public long nextMemberCounter(ClubId id) {
        return next(id, CounterType.member);
    }

    public long nextWallEventCounter(ClubId id) {
        return next(id, CounterType.wallevent);
    }


    public long nextWallNewsCounter(ClubId id) {
        return next(id, CounterType.wallnews);
    }

    public long nextWallMessageCounter(ClubId id) {
        return next(id, CounterType.wallmessage);
    }

    //PRIVATE METHODS

    //only used for top level jdos like Club and User
    private long read(CounterType counter) {
        Key key = KeyFactory.createKey(jdoClass.getSimpleName(), counter.name());
        return read(key);
    }

    private long read(ClubId clubId, CounterType counter) {
        Key key = getKey(clubId, counter.name());
        return read(key);
    }

    private long next(EntityId id, CounterType counter) {
        Key key = getKey(id, counter.name());
        return next(key, counter);
    }

    //only used for top level jdos like Club and User
    private long next(CounterType counter) {
        Key key = KeyFactory.createKey(jdoClass.getSimpleName(), counter.name());
        return next(key, counter);
    }

    private long next(Key key, CounterType counter) {
        PersistenceManager pm = PMF.getPersistenceManager();
        try {
            if (!pm.currentTransaction().isActive()) {
                throw new SystemException("incrementing counter has to be in a transaction. CounterType=" + counter.name());
            }
            long newCount = 1;
            try {
                CounterJDO jdo = pm.getObjectById(jdoClass, key);
                newCount = jdo.getCount() + 1;
                jdo.setCount(newCount);
                pm.makePersistent(jdo);
            }
            catch (javax.jdo.JDOObjectNotFoundException e) {
                CounterJDO newJdo = new CounterJDO();
                newJdo.setKey(key);
                newJdo.setCount(newCount);
                newJdo.setCreatedDate(new Date());
                newJdo.setName(counter.name());
                pm.makePersistent(newJdo);
            }

            return newCount;
        }
        catch (Throwable t) {
            throw log("next", key, t);
        }
        finally {
            PMF.close(pm);
        }
    }


    private long read(Key key) {
        PersistenceManager pm = PMF.getPersistenceManager();
        try {
            try {
                CounterJDO jdo = pm.getObjectById(jdoClass, key);
                return jdo.getCount();
            }
            catch (javax.jdo.JDOObjectNotFoundException e) {
                return 0;
            }
        }
        catch (Throwable t) {
            throw log("read", key, t);
        }
        finally {
            PMF.close(pm);
        }
    }

    private Key getKey(EntityId key) {
        return KeyFactory.stringToKey(key.getEncodedKey());
    }


    private Key getKey(EntityId id, String name) {
        return new KeyFactory.Builder(getKey(id)).addChild(jdoClass.getSimpleName(), name).getKey();
    }

    private SystemException log(String type, Object obj, Throwable t) {
        String s = "DAO: " + type + ". " + t.getClass().getName() + (obj != null ? " Object=" + obj.toString() : "");
        Logger.log(s, t);
        return new SystemException(s);
    }


}
