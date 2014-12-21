package com.bracketbird.server.repository;


import com.bracketbird.client.model.*;
import com.bracketbird.server.dao.*;
import com.bracketbird.server.jdo.TournamentChannelJDO;
import com.bracketbird.server.jdo.club.*;

/**
 *
 */
public class TournamentChannelRepository extends Repository<TournamentChannelJDO, TournamentChannel, CreateTournamentChannel>{


    public TournamentChannelRepository(){
        this(new Dao(TournamentChannelJDO.class),new TournamentChannelConverter());
    }

    public TournamentChannelRepository(Dao dao, Converter con) {
        super(dao, con);
    }

    public Class<TournamentChannel> getRepositoryType() {
        return TournamentChannel.class;
    }





    //FINDERS
/*
    public User findByEmail(String email) {
        if(email == null || email.equals("")){
            return null;
        }
        FindByOneParam findByParam = FindByOneParam.FIND_USER_BY_EMAIL;
        findByParam.setValue(email.toLowerCase());
        Collection<User> users = findBy(findByParam);
        if(users.size() > 1){
            throw new SystemException("Data error - "+users.size() +" users with same email exist. Email="+email);
        }
        return users.isNotReady() ? null : users.iterator().next();
    }

    public User updateResult(User user) {
        UserJDO jdo = dao.read(user.getId());
        //jdo.setWallEventId(user.getWallEventId());
        jdo.setLastChangeDate(new Date());
        //jdo.setNationality(user.getNationality());
        jdo.setPhone(user.getPhone());
        jdo.setPassword(user.getPassword());
        return conv.updateJDO(dao.updateResult(jdo));
    }
    */
}