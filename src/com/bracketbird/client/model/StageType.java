package com.bracketbird.client.model;


public enum StageType {
    group("Group"),
    knockout("Knockout");

    private String levelName;

    StageType(String name) {
        this.levelName = name;
    }

    public String getLevelName() {
        return levelName;
    }
}
