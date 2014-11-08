package com.bracketbird.client.gui.rtc.matches;

import com.bracketbird.client.model.tournament.Result;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SetEditorState {

    private Result result = null;
    private boolean validResult;

    public SetEditorState(String text, List<SetItem> items) {
        this.validResult = validate(items);
        if(validResult){
            this.result = createResult(text);
        }
    }

    private boolean validate(List<SetItem> items) {
        return validSize(items) && validLastItem(items);
    }


    private Result createResult(String text){
     List<Integer> homeResult = new ArrayList<Integer>();
        List<Integer> outResult = new ArrayList<Integer>();
        int index = 0;
        StringBuffer sb = new StringBuffer();
        boolean home = true;
        while (index < text.length()){
            char c = text.charAt(index++);
            if(Character.isDigit(c)){
                sb.append(c);
            }
            else{
                if(sb.length() != 0){
                    Integer value = Integer.valueOf(sb.toString());
                    if(home){
                        homeResult.add(value);
                        home = false;
                    }
                    else{
                        outResult.add(Integer.valueOf(sb.toString()));
                        home = true;
                    }

                    sb = new StringBuffer();
                }
            }
        }
        //in case out has not been emptied
        if(sb.length() != 0){
            outResult.add(Integer.valueOf(sb.toString()));
        }
        return Result.newInstance(homeResult, outResult);
    }

    public Result getResult() {
        return result;
    }

    public boolean isValidResult() {
        return validResult;
    }

    private boolean validLastItem(List<SetItem> items) {
        SetItem lastItem = items.get(items.size() - 1);
        boolean outResult = lastItem instanceof NumberItem && !((NumberItem) lastItem).isHome();
        return outResult || lastItem instanceof SetSepItem;
    }

    private boolean validSize(List<SetItem> items) {
        return items.size() > 3;
    }


}
