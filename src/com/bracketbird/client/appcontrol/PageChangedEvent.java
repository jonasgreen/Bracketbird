package com.bracketbird.client.appcontrol;

/**
 *
 */
public class PageChangedEvent {

    private PageController previousPage;
    private PageController currentPage;


    public PageChangedEvent(PageController previousPage, PageController currentPage) {
        this.previousPage = previousPage;
        this.currentPage = currentPage;
    }

    public PageController getPreviousPage() {
        return previousPage;
    }

    public PageController getCurrentPage() {
        return currentPage;
    }
}
