package com.javasmithy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ApothekeApp extends Application {
    private final String APP_ICON_PATH = "assets/images/potion-1-for-icon.png";
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(new ApothekeController().getView()));
        stage.setTitle("Apotheke");
        stage.getIcons().add(new Image(APP_ICON_PATH));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
