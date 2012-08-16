package com.bracketbird.client.model.keys;

import com.bracketbird.clientcore.model.keys.*;

/**
 *
 */
public class SubtournamentSettingsId extends EntityId {
    private static final long serialVersionUID = -8542917434809713271L;

    public SubtournamentSettingsId() {
    }

    public SubtournamentSettingsId(String encodedKey) {
        super(encodedKey, "subtournamentSettingsKey");
    }
}