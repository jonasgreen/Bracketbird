package com.bracketbird.client.service;

import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class ClubResult implements Result {
    private static final long serialVersionUID = -4735170017724185929L;

    private Club club;
    private Member member;

    public ClubResult() {
    }

    public ClubResult(Club club) {
        this.club = club;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}