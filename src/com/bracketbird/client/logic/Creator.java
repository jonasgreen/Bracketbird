package com.bracketbird.client.logic;

/**
 *
 */
public abstract class Creator<T> extends GwtCreator<T>{
    @Override
    T get() {
        return create();
    }

    @Override
    public abstract T create();
}
