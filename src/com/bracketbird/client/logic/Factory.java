package com.bracketbird.client.logic;

import java.util.HashMap;
import java.util.Map;


public class Factory implements GwtFactory {
    private Map<Class<?>, GwtCreator<?>> map = new HashMap<Class<?>, GwtCreator<?>>();

    private Factory() {
    }



    @Override
    public <T> T get(Class<T> clazz) {
        GwtCreator<T> creator = getCreator(clazz);
        if (creator == null) {
            throw new RuntimeException("No creator found for " + clazz.getName() + ". Please register a creator.");
        }

        return creator.get();
    }


    @Override
    public <W extends Wrapper<T>, T> W get(Class<W> clazz, T instance) {
        W wrapper = getCreator(clazz).get();
        wrapper.set(instance);
        return wrapper;
    }

    @Override
    public <T, K extends T> void register(Class<T> clazz, GwtCreator<K> creator) {
        map.put(clazz, creator);
    }

    @SuppressWarnings("unchecked")
    private <T, K extends T> GwtCreator<K> getCreator(Class<T> clazz) {
        return (GwtCreator<K>) map.get(clazz);
    }

}
