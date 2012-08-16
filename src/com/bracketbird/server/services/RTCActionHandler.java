package com.bracketbird.server.services;


import com.bracketbird.client.gui.rtc.event.REvent;
import com.bracketbird.client.model.TournamentChannel;
import com.bracketbird.client.model.keys.TournamentChannelId;
import com.bracketbird.client.model.tournament.Tournament;
import com.bracketbird.client.service.FinderFac;
import com.bracketbird.client.service.rtc.*;
import com.bracketbird.clientcore.util.CU;
import com.bracketbird.server.dao.*;
import com.bracketbird.server.jdo.club.RTCEventJDO;
import com.bracketbird.server.repository.RTCEventConverter;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.server.repository.TournamentChannelRepository;
import com.bracketbird.server.repository.tournament.TournamentRepository;
import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

import java.util.List;

/**
 *
 */
public class RTCActionHandler extends AbstractActionHandler implements ActionHandler<RTCAction, RTCResult> {
    private RTCEventConverter rtcConv = new RTCEventConverter();
    private Dao<RTCEventJDO> rtcDao = new Dao<RTCEventJDO>(RTCEventJDO.class);
    private TournamentRepository tRep = new TournamentRepository();
    private TournamentChannelRepository ctRep = new TournamentChannelRepository();


    public RTCResult execute(RTCAction action) throws ApplicationException {

        RTCResponse response;

        try {
            PMF.startTransaction();
            response = updateRTCEventLog(action.getRequest());
            PMF.commitTransaction();

        }
        finally {
            PMF.endTransaction();
        }

        notifyClients(action.getRequest(), response);
        return new RTCResult(response);

    }

    public RTCResponse updateRTCEventLog(RTCRequest req) throws ApplicationException {
        REvent event = req.getEvent();
        List<RTCEventJDO> jdos = CU.convert(rtcDao.findBy(FinderFac.findByTournamentAndIdGreaterThan(req.getTournamentId(), req.getLastDoneEventId())));

        if (hasStateChanged(jdos, event.getStateId())) {
            return new RTCResponse(RTCResponse.State.state_changed_wait_for_update);
        }
        else {
            RTCResponse.State state = jdos.isEmpty() ? RTCResponse.State.succeed : RTCResponse.State.merge;
            RTCEventJDO jdo = rtcConv.create(req.getTournamentId(), req.getEvent());

            RTCEventJDO eventJDO = rtcDao.create(jdo);
            return new RTCResponse(eventJDO.getStateId(), eventJDO.getId(), state);
        }
    }

    private void notifyClients(RTCRequest req, RTCResponse response) {
        if (response.getState() == RTCResponse.State.state_changed_wait_for_update) {
            return;
        }

        Tournament t = tRep.get(req.getTournamentId());
        TournamentChannelId tcId = t.getTournamentChannelId();
        TournamentChannel tournamentChannel = ctRep.get(tcId);

        ChannelService channelService = ChannelServiceFactory.getChannelService();

        boolean notifyAll = RTCResponse.State.merge == response.getState() && req.getEvent().isUpdateEvent();

        for (String client : tournamentChannel.getClients()) {
            if (notifyAll || !client.equals(req.getClientId())) {
                channelService.sendMessage(new ChannelMessage(client, "" + response.getEventId()));
            }
        }
    }

    private boolean hasStateChanged(List<RTCEventJDO> jdos, long lastState) {
        for (RTCEventJDO jdo : jdos) {
            if (jdo.getEvent().getStateId() > lastState) {
                return true;
            }
        }
        return false;
    }


    private boolean clientIsUpdToDate(List<RTCEventJDO> jdos) {
        return jdos.size() == 0;
    }


    public Class<RTCAction> getActionType() {
        return RTCAction.class;
    }


}