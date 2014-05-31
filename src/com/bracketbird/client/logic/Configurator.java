package com.bracketbird.client.logic;


public abstract class Configurator {
    public abstract void configure();

    protected final void registerProject(Registrator registrator) {
        registrator.setFactory(getFactory());
        registrator.register();
    }

    
    protected GwtFactory getFactory() {
    	/*if(GwtFactory.Get.INSTANCE == null) {
    		GwtFactory.Get.INSTANCE = new Factory();
    	}*/
    	return null;
    }

}
