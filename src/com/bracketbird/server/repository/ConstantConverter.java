package com.bracketbird.server.repository;


import com.bracketbird.server.jdo.club.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.model.*;
import com.bracketbird.clientcore.util.*;

import java.util.*;


/**
 *
 */
public class ConstantConverter extends Converter<ConstantJDO, Constant, CreateConstant> {

    public ConstantJDO convert(CreateConstant cr) {
        if (cr == null) {
            return null;
        }

        ConstantJDO jdo = new ConstantJDO();
        jdo.setKey(KeyFac.createConstantKey(cr.getId()));
        jdo.setName(StringUtil.capitalizeFirst(cr.getName().toLowerCase()));
        jdo.setGroup(cr.getGroup().getValue());
        jdo.setCreatedDate(new Date());
        return jdo;
    }

    public Constant convert(ConstantJDO jdo) {
        if (jdo == null) {
            return null;
        }
        Constant c = new Constant();
        c.setId(KeyFac.getConstantId(jdo.getKey()));

        c.setGroup(jdo.getGroup());
        c.setName(jdo.getName());
        c.setValue(jdo.getValue());
        return c;
    }

    @Override
    public void updateJDO(ConstantJDO jdo, Constant model) {
        throw new SystemException("updateJDO(Model m) not supported in "+this.getClass().getName());

    }


}