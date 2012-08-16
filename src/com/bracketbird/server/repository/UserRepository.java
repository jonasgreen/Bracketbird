package com.bracketbird.server.repository;


import com.bracketbird.client.model.*;
import com.bracketbird.client.model.tournament.Tournament;
import com.bracketbird.client.service.*;
import com.bracketbird.server.dao.*;
import com.bracketbird.server.jdo.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.keys.*;
import com.bracketbird.clientcore.service.*;
import com.google.appengine.api.datastore.Key;

import java.util.*;

/**
 *
 */
public class UserRepository extends Repository<UserJDO, User, CreateUser> {

    private TournamentBrickRepository tournamentBrickRep = new TournamentBrickRepository();



    public UserRepository() {
        this(new Dao(UserJDO.class), new UserConverter());
    }

    public UserRepository(Dao dao, Converter con) {
        super(dao, con);
    }

    public Class<User> getRepositoryType() {
        return User.class;
    }


    //FINDERS

    public void delete(UserId id) {
        UserJDO jdo = getDao().read(id);
        jdo.setDeleted(true);
        dao.update(jdo);
    }


    public User findByEmail(String email) {
        if (email == null || email.equals("")) {
            return null;
        }
        SingleFinder findByParam = FinderFac.findByEmail(email.toLowerCase());
        Collection<User> users = findBy(findByParam);
        if (users.size() > 1) {
            throw new SystemException("Data error - " + users.size() + " users with same email exist. Email=" + email);
        }
        return users.isEmpty() ? null : users.iterator().next();
    }


    public User reopen(UserId id) {
        UserJDO jdo = dao.read(id);
        jdo.setDeleted(false);
        return conv.convert(dao.update(jdo));
    }

}
