package com.bracketbird.clientcore.validation;

import com.bracketbird.clientcore.gui.*;

import java.util.*;

/**
 *
 */
public class ValidateManager {
    List<DataContainer> errorDCs = new ArrayList<DataContainer>();
    List<String> errorMsgs = new ArrayList<String>();


    public boolean validate(Collection<DataContainer> dcs) {
        String errorMsg;
        for (DataContainer dc : dcs) {
            errorMsg = validate(dc);
            if (errorMsg != null) {
                errorDCs.add(dc);
                errorMsgs.add(errorMsg);
            }
            dc.setDataIsIllegal(errorMsg != null);
        }
        return errorDCs.isEmpty();
    }

    public void showErrors() {
        final ChooseDialog dialog = new ChooseDialog(getErrorsAsStringList(), "Følgende felter er ikke korrekt udfyldt", DialogComponent.Response.OK);
        dialog.show(new DialogComponent.DialogCallBack() {
            public void onClose(DialogComponent.Response r) {
                errorDCs.get(dialog.getChosenItem()).setFocus(true);
            }
        });
        dialog.setWidth("400px");
        dialog.getButton(DialogComponent.Response.OK).setText("Ok");
    }

    private List<String> getErrorsAsStringList() {
        List<String> retList = new ArrayList<String>();
        int i = 0;
        for (DataContainer c : errorDCs) {
            String erMsg = c.getName() + " - " + errorMsgs.get(i++);
            retList.add(erMsg);
        }
        return retList;
    }




    private String validate(DataContainer dc) {
        String errorMsg = null;
        for (Validator v : dc.getValidators()) {
            errorMsg = v.validate(dc);
            if (errorMsg != null) {
                return errorMsg;
            }
        }
        return errorMsg;
    }

    public List<DataContainer> getErrorDCs() {
        return errorDCs;
    }

    public List<String> getErrorMsgs() {
        return errorMsgs;
    }
}
