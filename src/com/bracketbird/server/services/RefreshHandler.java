package com.bracketbird.server.services;


import com.bracketbird.client.service.FinderFac;
import com.bracketbird.client.service.GetTournamentAction;
import com.bracketbird.client.service.RefreshAction;
import com.bracketbird.clientcore.appcontrol.ApplicationException;
import com.bracketbird.clientcore.service.*;
import com.bracketbird.clientcore.util.CU;
import com.bracketbird.server.Logger;
import com.bracketbird.server.dao.Dao;
import com.bracketbird.server.jdo.club.RTCEventJDO;

import java.util.List;

/**
 *
 */
public class RefreshHandler extends AbstractActionHandler implements ActionHandler<RefreshAction, VoidResult> {


    public VoidResult execute(RefreshAction action) throws ApplicationException {
        GetTournamentAction a = new GetTournamentAction();
        try {
            Result result = ActionHandlerRegistry.executeAction(a);
            Logger.log(result.toString());
        }
        catch (Exception e) {
            //ignore
        }

        try {
            Dao<RTCEventJDO> rtcDao = new Dao<RTCEventJDO>(RTCEventJDO.class);
            List<RTCEventJDO> jdos = CU.convert(rtcDao.findBy(FinderFac.findById(1L, SingleFinder.Operator.EQUAL_TO)));
            Logger.log("" + jdos.size());
        }
        catch (Exception e) {
            //ignore
        }

        return new VoidResult();
    }


    public Class<RefreshAction> getActionType() {
        return RefreshAction.class;
    }


}