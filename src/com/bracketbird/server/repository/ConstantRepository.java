package com.bracketbird.server.repository;


import com.bracketbird.client.service.*;
import com.bracketbird.server.dao.*;
import com.bracketbird.server.jdo.club.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.service.*;

import java.util.*;

/**
 *
 */
public class ConstantRepository extends Repository<ConstantJDO, Constant, CreateConstant> {

    public ConstantRepository() {
        this(new Dao(ConstantJDO.class), new ConstantConverter());
    }

    public ConstantRepository(Dao dao, Converter con) {
        super(dao, con);
    }

    public Class<Constant> getRepositoryType() {
        return Constant.class;
    }


    //only call when in transaction
    public Constant create(CreateConstant creater) throws ApplicationException {
        ConstantJDO jdo = getConverter().convert(creater);
        IntegerSingleFinder f = FinderFac.findByGroup(creater.getGroup().getValue());
        f.setOrdering("group asc");
        Collection<ConstantJDO> jdos = getDao().findBy(f);
        

        if(jdos.isEmpty()){
            jdo.setValue(0);
        }
        else{
            int higestValue = 0;
            //validate name does not exist
            for (ConstantJDO j : jdos) {
                if(j.getName().equalsIgnoreCase(jdo.getName())){
                    throw new ApplicationException("Type of club does already exist.");
                }
                if(j.getValue() > higestValue){
                    higestValue = j.getValue();
                }
            }
            jdo.setValue(higestValue+1);
        }

        return getConverter().convert(getDao().create(jdo));
    }





}