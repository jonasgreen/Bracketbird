package com.bracketbird.client.logic;


public abstract class Registrator {
    protected GwtFactory factory;

    public void setFactory(GwtFactory factory) {
        this.factory = factory;
    }

    public abstract void register();

    public <BASE, EXTENDER extends BASE> void register(Class<BASE> baseClass, GwtCreator<EXTENDER> creator){
        factory.register(baseClass, creator);
    }
}
