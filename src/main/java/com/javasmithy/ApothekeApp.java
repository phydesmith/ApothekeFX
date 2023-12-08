package com.javasmithy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ApothekeApp extends Application {
    private final String APP_ICON_PATH = "assets/images/a-jblack-glyph.png";
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(new ApothekeController().getView()));
        stage.getIcons().add(new Image(APP_ICON_PATH));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
