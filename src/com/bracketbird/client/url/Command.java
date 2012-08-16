package com.bracketbird.client.url;

import java.util.Map;

/**
 *
 */
public interface Command {
    public void execute(Map<String, String> pMap);

    public String getName();
}
