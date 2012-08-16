package com.bracketbird.clientcore.service;


import com.bracketbird.clientcore.model.*;

/**
 *
 */
public class CreateConstantAction extends AbstractAction  implements Action<SingleResult>{
    private static final long serialVersionUID = 2869051220047735395L;

    private CreateConstant createConstant;


    public CreateConstantAction() {
    }


    public CreateConstantAction(CreateConstant createConstant) {
        this.createConstant = createConstant;
    }

    public CreateConstant getCreateConstant() {
        return createConstant;
    }

    public void setCreateConstant(CreateConstant createConstant) {
        this.createConstant = createConstant;
    }
}