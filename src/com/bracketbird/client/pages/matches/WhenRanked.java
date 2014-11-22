package com.bracketbird.client.pages.matches;

import com.bracketbird.client.model.Team;

import java.util.List;

public interface WhenRanked {

    public void onRanked(List<List<Team>> finalGroupRankings);
}
