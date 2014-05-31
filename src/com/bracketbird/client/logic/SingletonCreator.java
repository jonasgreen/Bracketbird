package com.bracketbird.client.logic;

/**
 *
 */
public abstract class SingletonCreator<T> extends GwtCreator<T>{

    private T instance;

    @Override
    T get() {
        if(instance == null){
            instance = create();
        }
        return instance;
    }

    @Override
    public abstract T create();
}
