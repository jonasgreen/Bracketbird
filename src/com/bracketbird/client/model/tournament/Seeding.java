package com.bracketbird.client.model.tournament;

import com.bracketbird.client.model.*;
import com.bracketbird.client.model.keys.*;

/**
 *
 */
public class Seeding extends TournamentLevel {

    private static final long serialVersionUID = 304980511576964854L;


    public Seeding(Tournament t){
        super(t);
    }

    @Override
    public Scheduler getScheduler() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Seeding() {
        super();
    }

}
