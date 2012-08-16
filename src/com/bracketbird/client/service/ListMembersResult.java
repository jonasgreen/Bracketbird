package com.bracketbird.client.service;

import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.service.*;

import java.util.*;

/**
 *
 */
public class ListMembersResult implements Result {
    private static final long serialVersionUID = 4314006242238883235L;

    private Collection<Member> members;

    public ListMembersResult(Collection<Member> members) {
        this.members = members;
    }

    public ListMembersResult() {
    }

    public Collection<Member> getMembers() {
        return members;
    }

    public void setMembers(Collection<Member> members) {
        this.members = members;
    }
}