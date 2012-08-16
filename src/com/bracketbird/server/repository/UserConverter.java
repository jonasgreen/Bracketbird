package com.bracketbird.server.repository;


import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.server.jdo.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.util.*;
import com.google.appengine.api.datastore.KeyFactory;

import java.util.*;

/**
 *
 */
public class UserConverter extends Converter<UserJDO, User, CreateUser> {

    public UserJDO convert(CreateUser model) {
        if (model == null) {
            return null;
        }
        UserJDO jdo = new UserJDO();

        jdo.setKey(KeyFac.createUserKey(model.getId()));
        jdo.setFirstName(StringUtil.capitalizeFirst(model.getFirstName()));
        jdo.setLastName(StringUtil.capitalizeFirst(model.getLastName()));
        jdo.setNickName(model.getNickName());

        jdo.setDeleted(false);

        jdo.setEmail(model.getEmail().toLowerCase());
        jdo.setPassword(model.getPassword());
        jdo.setPhone(model.getPhone());
        return jdo;
    }

    public User convert(UserJDO jdo) {
        if (jdo == null) {
            return null;
        }
        User user = new User();

        //system
        user.setId(KeyFac.getUserId(jdo.getKey()));
        user.setCreatedDate(jdo.getCreatedDate());
        user.setLastChangeDate(jdo.getLastChangeDate());

        user.setFirstName(jdo.getFirstName());
        user.setLastName(jdo.getLastName());
        user.setNickName(jdo.getNickName());

        user.setEmail(jdo.getEmail());
        user.setPassword(jdo.getPassword());
        user.setPhone(jdo.getPhone());


        Address add = new Address();
        add.setCity(add.getCity());
        add.setCityCode(add.getCityCode());
        add.setStreet(add.getStreet());
        add.setNationalCode(add.getNationalCode());
        user.setAddress(add);

        user.setDeleted(jdo.isDeleted());

        return user;
    }


    public void updateJDO(UserJDO jdo, User user) {
        jdo.setLastChangeDate(new Date());

        jdo.setFirstName(StringUtil.capitalizeFirst(user.getFirstName()));
        jdo.setLastName(StringUtil.capitalizeFirst(user.getLastName()));
        jdo.setNickName(user.getNickName());

        jdo.setPassword(user.getPassword());
        jdo.setPhone(user.getPhone());

        jdo.setStreet(user.getAddress().getStreet());
        jdo.setCity(user.getAddress().getCity());
        jdo.setCityCode(user.getAddress().getCityCode());
        jdo.setCountry(user.getAddress().getNationalCode());

        jdo.setDeleted(user.isDeleted());
    }


}
