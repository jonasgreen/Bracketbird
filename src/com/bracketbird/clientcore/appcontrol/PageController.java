package com.bracketbird.clientcore.appcontrol;


/**
 *
 */
public abstract class PageController<P extends Page> {

    private static int counter = 0;
    private final int id = counter++;

    private boolean firstLoad = true;

    protected P page;

    protected PageController() {
    }

    public abstract P newInstance();


    public void handleAfterLoad(){
        if(firstLoad){
            firstLoad = false;
            afterFirstLoad();
        }
        afterLoad();
    }

    public void afterLoad() {
    }

    public void afterFirstLoad() {
    }


    public void beforeUnload() {
    }

    //lazy loading
    public P getPage() {
        if (page == null) {
            page = newInstance();
            //noinspection unchecked
            page.setController(this);
            page.init();
        }
        return page;
    }


    public void clear() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PageController that = (PageController) o;

        if (id != that.id) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
