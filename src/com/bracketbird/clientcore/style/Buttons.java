package com.bracketbird.clientcore.style;

import com.bracketbird.clientcore.gui.ButtonComponent;

/**
 *
 */
public class Buttons {

    public static ButtonComponent primary(){
        return ButtonStyle.primary(create());
    }

    private static ButtonComponent create(){
        return new ButtonComponent();
    }

}
