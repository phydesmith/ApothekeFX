package com.javasmithy.widgetsfx;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class ButtonWidgets {

    public static Button createButtonWithAction(String text, EventHandler<ActionEvent> e) {
        Button button = new Button(text);
        button.setOnAction(e);
        return button;
    }

    public static Button createButtonWithActionAndBinding(String text, EventHandler<ActionEvent> e, SimpleBooleanProperty binding){
        Button button = createButtonWithAction(text, e);
        button.disableProperty().bind(binding);
        return button;
    }

    public static Button createExitButton(String text){
        return createButtonWithAction(text, exitButtonEventHandler());
    }
    private static EventHandler<ActionEvent> exitButtonEventHandler(){
        return e -> {
            Platform.exit();
            System.exit(0);
        };
    }



}
