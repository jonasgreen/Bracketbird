package com.bracketbird.client.service;

import java.io.Serializable;

/**
 *
 */
public class AbstractAction  implements Serializable {
    private static final long serialVersionUID = -6345103274608935009L;

    private Integer language;

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }
}
