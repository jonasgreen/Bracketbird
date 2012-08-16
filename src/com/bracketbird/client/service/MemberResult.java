package com.bracketbird.client.service;

import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class MemberResult implements Result {
    private static final long serialVersionUID = 1570180859405649043L;

    private Member member;

    public MemberResult() {
    }

    public MemberResult(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}