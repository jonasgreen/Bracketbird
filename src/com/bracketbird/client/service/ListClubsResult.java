package com.bracketbird.client.service;

import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.service.*;

import java.util.*;

/**
 *
 */
public class ListClubsResult implements Result {
    private static final long serialVersionUID = 5611141055205870144L;

    private Collection<Club> clubs;

    public ListClubsResult(Collection<Club> clubs) {
        this.clubs = clubs;
    }

    public ListClubsResult() {
    }

    public Collection<Club> getClubs() {
        return clubs;
    }

    public void setClubs(Collection<Club> clubs) {
        this.clubs = clubs;
    }
}