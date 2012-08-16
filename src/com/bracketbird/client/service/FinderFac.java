package com.bracketbird.client.service;


import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.service.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;

/**
 *
 */
public class FinderFac {
    private static String DATASTORE_TYPE = "com.google.appengine.api.datastore.Key";


    //SINGLEFINDER

    //created date

    public static SingleFinder findByCreatedDate(Date value) {
        return findByCreatedDate(value, SingleFinder.Operator.EQUAL_TO);
    }

    public static SingleFinder findByCreatedDateGreaterThan(Date value) {
        return findByCreatedDate(value, SingleFinder.Operator.GREATER_THAN);
    }

    public static DateSingleFinder findByCreatedDate(Date value, SingleFinder.Operator operator) {
        return new DateSingleFinder("java.util.Date", "createdDate", operator, value);
    }

    public static LongSingleFinder findById(Long id, SingleFinder.Operator operator) {
        return new LongSingleFinder("Long", "id", operator, id);
    }


    //invalidDate

    public static DateSingleFinder findByInvalidDateGreaterThan(Date value) {
        return new DateSingleFinder("java.util.Date", "invalidDate", SingleFinder.Operator.GREATER_THAN_OR_EQUAL, value);
    }

    //eventDate

    public static DateSingleFinder findByEventDateGreaterThan(Date value) {
        return new DateSingleFinder("java.util.Date", "eventDate", SingleFinder.Operator.GREATER_THAN_OR_EQUAL, value);
    }

    public static DateSingleFinder findByEventDateLessThan(Date value) {
        return new DateSingleFinder("java.util.Date", "eventDate", SingleFinder.Operator.LESS_THAN, value);
    }

    public static DateSingleFinder findByNotificationDateLessThan(Date value) {
        return new DateSingleFinder("java.util.Date", "notificationDate", SingleFinder.Operator.LESS_THAN, value);
    }


    public static BooleanSingleFinder findByNotificationSent(Boolean value) {
        return new BooleanSingleFinder("Boolean", "notificationSent", value);
    }


    //nameLowerCaser

    public static StringSingleFinder findByNameLowerCaser(String name) {
        return new StringSingleFinder("nameLowercase", SingleFinder.Operator.EQUAL_TO, name);
    }

    //name

    public static StringSingleFinder findByName(String name) {
        return new StringSingleFinder("name", SingleFinder.Operator.EQUAL_TO, name);
    }


    //email

    public static StringSingleFinder findByEmail(String email) {
        return new StringSingleFinder("email", SingleFinder.Operator.EQUAL_TO, email);
    }

    //group

    public static IntegerSingleFinder findByGroup(Integer group) {
        return new IntegerSingleFinder("Integer", "group", SingleFinder.Operator.EQUAL_TO, group);
    }


    //entityId

    public static EntityIdSingleFinder findByEntityId(EntityId entityId) {
        //value is set on server.
        return new EntityIdSingleFinder(DATASTORE_TYPE, entityId.getDataStoreName(), entityId);
    }


    //AND Finder

    public static Finder findByClubIdAndCreatedDateGreaterThan(ClubId clubId, Date targetDate) {
        return findByEntityId(clubId).and(findByCreatedDateGreaterThan(targetDate));
    }

    public static Finder findByClubIdAndName(ClubId clubId, String name) {
        return findByEntityId(clubId).and(findByName(name));
    }


    public static Finder findByClubIdAndEventDateGreaterThan(ClubId clubId) {
        //minus one day to compensate for the fact that events and news are create at hh:mm = 00:00
        return findByEntityId(clubId).and(findByEventDateGreaterThan(new Date(new Date().getTime() - DateConstants.ONE_DAY)));

    }


    public static Finder findByTournamentAndIdEqualTo(TournamentId tournamentId, Long id) {
        //minus one day to compensate for the fact that events and news are create at hh:mm = 00:00
        return findByEntityId(tournamentId).and(findById(id, SingleFinder.Operator.EQUAL_TO));
    }


    public static Finder findByTournamentAndIdGreaterThan(TournamentId tournamentId, Long id) {
        //minus one day to compensate for the fact that events and news are create at hh:mm = 00:00
        return findByEntityId(tournamentId).and(findById(id, SingleFinder.Operator.GREATER_THAN));
    }

    public static Finder findByClubIdAndInvalidDateGreaterThan(ClubId clubId) {
        //minus one day to compensate for the fact that events and news are create at hh:mm = 00:00
        return findByEntityId(clubId).and(findByInvalidDateGreaterThan(new Date(new Date().getTime() - DateConstants.ONE_DAY)));
    }


    /*
    //TODO - change to method creations

   public static Finder FIND_BY_NAME = new Finder("String", "name");

   public static Finder FIND_ROUTES_BY_USERID = new Finder("Long", "userId");
   public static Finder FIND_ROUTES_BY_CREATEDDATE_GREATER_THAN = new Finder("java.util.Date", "createdDate", Operator.GREATER_THAN);
   public static Finder FIND_ROUTES_BY_TODATE_GREATER_THAN = new Finder("java.util.Date", "toDate", Operator.GREATER_THAN);

   public static Finder FIND_ROUTES_DELETED = new Finder("Boolean", "deleted");
    */



}
