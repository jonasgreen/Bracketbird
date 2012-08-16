package com.bracketbird.clientcore.service;

/**
 *
 */
public class FindByAction extends AbstractAction  implements Action<ListResult>{
    private static final long serialVersionUID = -5730956869371851311L;

    private FindIn findIn;
    private Finder finder;

    public FindByAction() {
    }

    public FindByAction(FindIn findIn, Finder f) {
        this.findIn = findIn;
        this.finder = f;
    }

    public FindIn getFindIn() {
        return findIn;
    }

    public void setFindIn(FindIn findIn) {
        this.findIn = findIn;
    }

    public Finder getFinder() {
        return finder;
    }

    public void setFinder(Finder finder) {
        this.finder = finder;
    }
}