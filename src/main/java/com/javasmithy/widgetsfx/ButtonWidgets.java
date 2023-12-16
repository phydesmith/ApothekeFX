package com.javasmithy.widgetsfx;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Class to place some helper methods for commonly used controls in javafx.
 */
public class ButtonWidgets {

    /**
     *  Sets the onAction prop of button to specified event handler.
     * @param text - for setting button text to
     * @param e - event handler implementation
     * @return - button with an event handler associated to it
     */
    public static Button createButtonWithAction(String text, EventHandler<ActionEvent> e) {
        Button button = new Button(text);
        button.setOnAction(e);
        return button;
    }

    /**
     *  Sets the onAction prop of button to specified event handler. Also has boolean binding for disabling.
     * @param text - for setting button text to
     * @param e - event handler implementation
     * @param binding - boolean property for binding to disable
     * @return - button with an event handler associated to it
     */
    public static Button createButtonWithActionAndBinding(String text, EventHandler<ActionEvent> e, SimpleBooleanProperty binding){
        Button button = createButtonWithAction(text, e);
        button.disableProperty().bind(binding);
        return button;
    }

    /**
     * A button for exiting the program.
     * @param text - text on button
     * @return A button that calls Platform.exit() and System.exit(0) when pressed.
     */
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
