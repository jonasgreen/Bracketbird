package com.bracketbird.server.repository;


import com.bracketbird.client.model.*;
import com.bracketbird.server.jdo.club.*;
import com.bracketbird.clientcore.util.*;


/**
 *
 */
public class MemberConverter extends Converter<MemberJDO, Member, CreateMember> {

    public MemberJDO convert(CreateMember model) {
        if (model == null) {
            return null;
        }

        MemberJDO jdo = new MemberJDO();

        jdo.setKey(KeyFac.createMemberKey(model.getClubId()));
        jdo.setClubKey(KeyFac.convert(model.getClubId()));
        jdo.setUserKey(KeyFac.convert(model.getUserId()));

        jdo.setMemberType(model.getMemberType());
        jdo.setFirstName(StringUtil.capitalizeFirst(model.getFirstName()));
        jdo.setLastName(StringUtil.capitalizeFirst(model.getLastName()));
        jdo.setNickName(StringUtil.capitalizeFirst(model.getNickName()));

        return jdo;
    }

    public Member convert(MemberJDO jdo) {
        if (jdo == null) {
            return null;
        }
        Member member = new Member();

        //keys
        member.setId(KeyFac.getMemberId(jdo.getKey()));
        member.setClubId(KeyFac.getClubId(jdo.getClubKey()));
        member.setUserId(KeyFac.getUserId(jdo.getUserKey()));

        member.setMemberType(jdo.getMemberType());
        member.setFirstName(jdo.getFirstName());
        member.setLastName(jdo.getLastName());
        member.setNickName(jdo.getNickName());

        member.setLastChangeDate(jdo.getLastChangeDate());
        member.setCreatedDate(jdo.getCreatedDate());
        return member;
    }


    public void updateJDO(MemberJDO jdo, Member m) {
        jdo.setFirstName(m.getFirstName());
        jdo.setLastName(m.getLastName());
        jdo.setNickName(m.getNickName());
        jdo.setMemberType(m.getMemberType());
    }
}