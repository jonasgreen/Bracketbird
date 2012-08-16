package com.bracketbird.server.repository;


import com.bracketbird.client.model.*;
import com.bracketbird.client.service.*;
import com.bracketbird.server.dao.*;
import com.bracketbird.server.jdo.club.*;
import com.bracketbird.clientcore.model.keys.*;

import java.util.*;

/**
 *
 */
public class MemberRepository extends Repository<MemberJDO, Member, CreateMember> {

    public MemberRepository() {
        this(new Dao(MemberJDO.class), new MemberConverter());
    }

    public MemberRepository(Dao dao, Converter con) {
        super(dao, con);
    }

    public Class<Member> getRepositoryType() {
        return Member.class;
    }


    //FINDERS

    public Collection<Member> findByClubId(EntityId clubId) {
        return findBy(FinderFac.findByEntityId(clubId));
    }

    public Collection<Member> findByUserId(EntityId userId) {
        return findBy(FinderFac.findByEntityId(userId));
    }

}