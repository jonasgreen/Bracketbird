package com.bracketbird.clientcore.gui;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

/**
 *
 */
public class ButtonComponent extends GuiComponent{
    private Button button = new Button();

    public ButtonComponent() {
        super();
        add(button);
    }

    private void add(Button button2) {
		// TODO Auto-generated method stub
		
	}

	public ButtonComponent(String s){
        super();
        add(button);
        button.setText(s);
    }

    public Button getButton() {
        return button;
    }

    public void setText(String s) {
        button.setText(s);
    }

    public void addClickHandler(ClickHandler clickHandler) {
        button.addClickHandler(clickHandler);
    }
}
