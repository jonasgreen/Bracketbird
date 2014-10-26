package com.bracketbird.client.model;


public enum LevelType {
    missing("?"),
    group("Group"),
    knockout("Knockout");

    private String levelName;

    LevelType(String name) {
        this.levelName = name;
    }

    public String getLevelName() {
        return levelName;
    }
}
