package com.bracketbird.client.rtc;


/**
 *
 */
public class OkWarning extends ProceedOrCancelWarning {

    public OkWarning(String header, String text) {
        super((text));
        getHeaderLabel().setText(header);
        getOkButton().setText("Ok");
        getCancelButton().removeFromParent();
    }

    @Override
    protected void setFocus() {
        getOkButton().setFocus(true);
    }
}
