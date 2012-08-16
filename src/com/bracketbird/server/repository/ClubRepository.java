package com.bracketbird.server.repository;


import com.bracketbird.client.model.*;
import com.bracketbird.server.dao.*;
import com.bracketbird.server.jdo.club.*;

/**
 *
 */
public class ClubRepository extends Repository<ClubJDO, Club, CreateClub>{


    public ClubRepository(){
        this(new Dao(ClubJDO.class), new ClubConverter());
    }

    public ClubRepository(Dao dao, Converter con) {
        super(dao, con);
    }

    public Class<Club> getRepositoryType() {
        return Club.class;
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
        return users.isEmpty() ? null : users.iterator().next();
    }

    public User update(User user) {
        UserJDO jdo = dao.read(user.getId());
        //jdo.setWallEventId(user.getWallEventId());
        jdo.setLastChangeDate(new Date());
        //jdo.setNationality(user.getNationality());
        jdo.setPhone(user.getPhone());
        jdo.setPassword(user.getPassword());
        return conv.updateJDO(dao.update(jdo));
    }
    */
}