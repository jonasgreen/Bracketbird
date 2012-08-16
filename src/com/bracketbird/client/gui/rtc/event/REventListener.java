package com.bracketbird.client.gui.rtc.event;

import com.bracketbird.clientcore.service.OnceADayCronJobAction;

/**
 *
 */
public interface REventListener {
    public void onChange(REvent<?,?> event);
}
