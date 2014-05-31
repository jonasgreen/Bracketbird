package com.bracketbird.client.logic;

public interface GwtFactory {

    <T, K extends T> void register(Class<T> clazz, GwtCreator<K> creator);

    <T> T get(Class<T> clazz);

    <W extends Wrapper<T>,T> W get(Class<W> clazz, T instance);



}
