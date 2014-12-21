package com.bracketbird.server.services;

import com.bracketbird.client.appcontrol.ApplicationException;
import com.bracketbird.client.model.TournamentChannel;
import com.bracketbird.client.service.rtc.CreateChannelTokenAction;
import com.bracketbird.client.service.rtc.CreateChannelTokenResult;
import com.bracketbird.server.repository.TournamentChannelRepository;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

/**
 *
 */
public class CreateChannelTokenHandler extends AbstractActionHandler implements ActionHandler<CreateChannelTokenAction, CreateChannelTokenResult> {


    public CreateChannelTokenResult execute(CreateChannelTokenAction action) throws ApplicationException {
        String clientId = action.getUid();

        TournamentChannelRepository tCRep = new TournamentChannelRepository();
        TournamentChannel channel = tCRep.get(action.getChannelId());

        ChannelService channelService = ChannelServiceFactory.getChannelService();

        String token = channelService.createChannel(clientId);
        channel.getClients().add(clientId);
        tCRep.update(channel);

        return new CreateChannelTokenResult(token, clientId);

    }

    public Class<CreateChannelTokenAction> getActionType() {
        return CreateChannelTokenAction.class;
    }


}