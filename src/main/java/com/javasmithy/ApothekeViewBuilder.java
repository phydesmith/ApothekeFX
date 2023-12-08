package com.javasmithy;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Builder;
import javafx.scene.control.Button;

public class ApothekeViewBuilder implements Builder<Region> {

    private final ApothekeModel model;
    private final BorderPane root;

    public ApothekeViewBuilder(ApothekeModel model) {
        this.model = model;
        this.root = new BorderPane();
    }

    @Override
    public Region build() {
        root.getStylesheets().add("style/style.css");
        root.setCenter(createStartMenu());

        return root;
    }

    private Node createStartMenu(){
        VBox menuRoot = new VBox();
        VBox.setVgrow(menuRoot, Priority.ALWAYS);
        menuRoot.getStyleClass().add("start-menu-root");

        Label startMenuHeader = new Label("Apotheke");
        startMenuHeader.getStyleClass().add("header");
        Button startButton = new Button("Start Game");
        startButton.setOnAction( e -> {
            this.root.getChildren().clear();
            this.root.setCenter(createPlayerCreationView());
        });
        Button optionsButton = new Button("Options");
        Button exitButton = new Button("Exit Game");
        exitButton.setOnAction( e -> {
            Platform.exit();
            System.exit(0);
        });
        menuRoot.getChildren().addAll(startMenuHeader, startButton, optionsButton, exitButton);
        return menuRoot;
    }

    private Node createPlayerCreationView(){
        Image image = new Image("assets/images/portraits/portrait1.png");
        ImageView imageView = new ImageView(image);


        return imageView;
    }

    private Node createPlayerCreationViewOld(){
        HBox creationViewRoot = new HBox();
        HBox.setHgrow(creationViewRoot, Priority.ALWAYS);

        //  Image Selector
        VBox characterImageSelector = new VBox();
        characterImageSelector.getChildren().addAll(
                new ImageView(new Image("assets/images/portraits/portrait1.png")),
                new HBox(
                        new Button("<"),
                        new Button(">")
                )
        );

        //  Player Name
        Label label = new Label("Player Name:");
        label.setLabelFor(new TextField());

        //  Skills
        HBox skills = new HBox();
        Label cultivationSkillLabel = new Label("Cultivation");
        Button lefCultivationButton = new Button(" < ");
        Button righCultivationButton = new Button(" > ");
        Label cultivationSkillValueLabel = new Label("3");
        skills.getChildren().addAll(
                cultivationSkillLabel,
                lefCultivationButton,
                cultivationSkillValueLabel,
                righCultivationButton
        );

        VBox rightContent = new VBox();
        rightContent.getChildren().addAll(
                label,
                skills
        );

        creationViewRoot.getChildren().addAll(
                characterImageSelector,
                rightContent
        );
        return creationViewRoot;
    }

    private Node createGameView(){
        BorderPane gameRoot = new BorderPane();
        gameRoot.getStyleClass().add("game-view-root");
        StackPane centerGameItem = new StackPane();
        centerGameItem.getStyleClass().add("game-center-item");
        gameRoot.setCenter(centerGameItem);
        Button backToMenuButton = new Button("Return");
        backToMenuButton.setOnAction( e -> {
            root.getChildren().clear();
            root.getChildren().add(createStartMenu());
        });
        gameRoot.setBottom(backToMenuButton);
        return gameRoot;
    }
}
