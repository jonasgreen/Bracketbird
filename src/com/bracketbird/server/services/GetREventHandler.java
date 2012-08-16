package com.bracketbird.server.services;

import com.bracketbird.client.service.FinderFac;
import com.bracketbird.client.service.rtc.GetREventAction;
import com.bracketbird.client.service.rtc.GetREventResult;
import com.bracketbird.clientcore.appcontrol.ApplicationException;
import com.bracketbird.server.dao.Dao;
import com.bracketbird.server.jdo.club.RTCEventJDO;
import com.bracketbird.server.repository.RTCEventConverter;

import java.util.Collection;


/**
 *
 */
public class GetREventHandler extends AbstractActionHandler implements ActionHandler<GetREventAction, GetREventResult> {

    private Dao<RTCEventJDO> rtcDao = new Dao<RTCEventJDO>(RTCEventJDO.class);
    private RTCEventConverter rtcEventConverter = new RTCEventConverter();

    public GetREventResult execute(GetREventAction action) throws ApplicationException {
        Collection<RTCEventJDO> jdos = rtcDao.findBy(FinderFac.findByTournamentAndIdEqualTo(action.getTournamentId(), action.getEventId()));
        return new GetREventResult(rtcEventConverter.create(jdos).get(0));

    }

    public Class<GetREventAction> getActionType() {
        return GetREventAction.class;
    }


}