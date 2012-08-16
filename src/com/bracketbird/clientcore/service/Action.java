package com.bracketbird.clientcore.service;

import java.io.Serializable;

/**
 *
 */
public interface Action<R extends Result> extends Serializable {

    public Integer getLanguage();

    public void setLanguage(Integer language);


}
