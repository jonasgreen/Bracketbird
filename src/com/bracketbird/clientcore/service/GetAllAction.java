package com.bracketbird.clientcore.service;

/**
 *
 */
public class GetAllAction extends AbstractAction  implements Action<ListResult>{

    private static final long serialVersionUID = -645574886263670093L;
    private FindIn findIn;

    public GetAllAction() {
    }

    public GetAllAction(FindIn findIn) {
        this.findIn = findIn;
    }

    public FindIn getFindIn() {
        return findIn;
    }

    public void setFindIn(FindIn findIn) {
        this.findIn = findIn;
    }
}