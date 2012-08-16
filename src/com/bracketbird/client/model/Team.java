package com.bracketbird.client.model;


import com.bracketbird.client.gui.rtc.event.*;
import com.bracketbird.client.model.keys.*;
import com.bracketbird.clientcore.model.*;

/**
 *
 */
public class Team extends Model<TeamId> {
    private static final long serialVersionUID = -8413373363468003258L;

    protected String name;
    protected String info;
    protected Integer seeding;

    public Team() {
        super();
    }

    public Integer getSeeding() {
        return seeding;
    }

    public void setSeeding(UpdateTeamSeedingEvent event) {
        this.seeding = event.getSeeding();
        fireEvent(event);
    }

    public String getName() {
        return name;
    }

    public void setName(UpdateTeamNameEvent event) {
        this.name = event.getName();
        fireEvent(event);
    }

    public boolean isEmpty() {
        return name == null || name.equals("");
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(UpdateTeamInfoEvent event) {
        this.info = event.getInfo();
        fireEvent(event);
    }

    public boolean isWalkover() {
        return this instanceof WalkOverTeam;
    }

    public boolean hasSeeding() {
        return seeding != null;
    }

    public void setSeedingSilent(int i) {
        this.seeding = i;
    }

    public boolean isSeedingTeam() {
        return this instanceof SeedingTeam;
    }

    public boolean isARealTeam(){
        return true;
    }
}
