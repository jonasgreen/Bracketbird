package com.bracketbird.server.repository;


import com.bracketbird.client.model.*;
import com.bracketbird.server.jdo.club.*;
import com.bracketbird.clientcore.appcontrol.*;


/**
 *
 */
public class ClubConverter extends Converter<ClubJDO, Club, CreateClub> {

    public ClubJDO convert(CreateClub model) {
        if (model == null) {
            return null;
        }

        ClubJDO jdo = new ClubJDO();

        jdo.setKey(KeyFac.createClubKey(model.getId()));
        jdo.setName(model.getName());
        jdo.setNameLowercase(model.getName().toLowerCase());
        jdo.setDescription(model.getDescription());
        jdo.setSportType(model.getSportType());


        jdo.setTimeZoneOffset(model.getTimeZoneOffset());
        jdo.setVisibility(model.getVisibility());
        jdo.setDeleted(false);
        return jdo;
    }

    public Club convert(ClubJDO jdo) {
        if (jdo == null) {
            return null;
        }
        Club club = new Club();

        club.setId(KeyFac.getClubId(jdo.getKey()));
        club.setLastChangeDate(jdo.getLastChangeDate());
        club.setCreatedDate(jdo.getCreatedDate());

        club.setName(jdo.getName());
        club.setNameLowerCase(jdo.getNameLowercase());
        club.setDescription(jdo.getDescription());
        club.setSportType(jdo.getSportType());
        club.setVisibility(jdo.getVisibility());
        club.setTimeZoneOffset(jdo.getTimeZoneOffset());
        
        return club;
    }

    @Override
    public void updateJDO(ClubJDO jdo, Club model) {
        throw new SystemException("updateJDO(Model m) not supported in "+this.getClass().getName());
    }


}