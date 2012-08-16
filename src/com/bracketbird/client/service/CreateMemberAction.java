package com.bracketbird.client.service;

import com.bracketbird.client.model.*;
import com.bracketbird.clientcore.service.*;

/**
 *
 */
public class CreateMemberAction extends AbstractAction implements Action<MemberResult>{
    private static final long serialVersionUID = -606828148718592865L;

    private CreateMember createMember;


    public CreateMemberAction() {
    }

    public CreateMemberAction(CreateMember createMember) {
        this.createMember = createMember;
    }

    public CreateMember getCreateMember() {
        return createMember;
    }

    public void setCreateMember(CreateMember createMember) {
        this.createMember = createMember;
    }
}