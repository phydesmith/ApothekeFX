package com.javasmithy;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Builder;
import javafx.scene.control.Button;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;

import static com.javasmithy.widgetsfx.ButtonWidgets.createButtonWithAction;
import static com.javasmithy.widgetsfx.ButtonWidgets.createExitButton;

public class ApothekeViewBuilder implements Builder<Region> {

    private final ApothekeModel model;
    private final StackPane root;

    public ApothekeViewBuilder(ApothekeModel model) {
        this.model = model;
        this.root = new StackPane();
    }

    @Override
    public Region build() {
        root.getStylesheets().add("style/style.css");
        root.getChildren().add(createStartMenu());
        return root;
    }


    private EventHandler<ActionEvent> switchWindowEventHandler(Node destination){
        return e -> {
            this.root.getChildren().clear();
            this.root.getChildren().add(destination);
          resizeWindow();
        };
    }

    private void resizeWindow(){
        Platform.runLater( () -> {
            this.root.getScene().getWindow().sizeToScene();
        });
    }

    private Node createStartMenu(){
        VBox menuRoot = new VBox();
        VBox.setVgrow(menuRoot, Priority.ALWAYS);
        menuRoot.getStyleClass().add("start-menu-root");
        Label startMenuHeader = new Label("Apotheke");
        startMenuHeader.getStyleClass().add("header");
        Button startButton = createButtonWithAction("Start Game", switchWindowEventHandler(createPlayerCreationView()));
        Button optionsButton = new Button("Options");
        Button exitButton = createExitButton("Exit Game");
        menuRoot.getChildren().addAll(startMenuHeader, startButton, optionsButton, exitButton);
        return menuRoot;
    }

    private Node createPlayerCreationView(){
        return new VBox(
               new HBox( createPortraitSelector(),
                            createSkillUpdater()),
                createButtonWithAction("Submit", switchWindowEventHandler(createGameView()))
        );
    }

    private Node createSkillUpdater() {
        return new VBox(
                playerNameChoiceContainer(),
                skillLineContainer("Cultivation", "Cultivation Description", model.playerCultivationSkillValueProperty()),
                skillLineContainer("Extraction", "Extraction Description", model.playerExtractionSkillValueProperty()),
                skillLineContainer("Synthesis", "Synthesis Description", model.playerSynthesisSkillValueProperty()),
                skillLineContainer("Diagnosis", "Diagnosis Description", model.playerDiagnosisSkillValueProperty()),
                allocatableSkillPointsContainer()
        );
    }

    private Node skillLineContainer(String skill, String skillDescription, SimpleIntegerProperty skillValue){
        HBox skillLineContainer = new HBox();
        Label skillLabel = new Label(skill);
        Tooltip skillToolTip = new Tooltip(skillDescription);
        skillToolTip.setShowDelay(Duration.ZERO);
        Tooltip.install(skillLabel, skillToolTip);
        Button skillDecreaseButton = new Button(" - ");
        Button skillIncreaseButton = new Button(" + " );
        Label skillValueLabel = new Label();
        skillValueLabel.textProperty().bindBidirectional(skillValue, new NumberStringConverter());
        skillLineContainer.getChildren().addAll(
                skillLabel,
                skillDecreaseButton,
                skillValueLabel,
                skillIncreaseButton
        );
        return skillLineContainer;
    }

    private Node allocatableSkillPointsContainer(){
        Label label = new Label("Skill points to allocate: ");
        Label allocatableSkillPointsLabel = new Label();
        allocatableSkillPointsLabel.textProperty().bindBidirectional(model.skillPointsToAllocateProperty(), new NumberStringConverter());
        return new HBox(label, allocatableSkillPointsLabel);
    }

    private Node playerNameChoiceContainer(){
        Label playerNameLabel = new Label("Player Name : ");
        TextField playerNameTextField = new TextField();
        playerNameTextField.textProperty().bindBidirectional(model.playerNameProperty());
        return new HBox(
                playerNameLabel,
                playerNameTextField
        );
    }

    private Node createPortraitSelector(){
        HBox portraitButtonContainer = new HBox();
        Button leftPortraitButton = new Button(" < ");
        Button rightPortraitButton = new Button(" > " );
        portraitButtonContainer.getChildren().addAll(
                leftPortraitButton,
                rightPortraitButton
        );

        Image image = new Image("assets/images/portraits/portrait1.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(330);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        VBox portraitSelectionContainer = new VBox();
        portraitSelectionContainer.getChildren().addAll(
                imageView,
                portraitButtonContainer
        );

        return portraitSelectionContainer ;
    }

    private Node createGameView(){
        VBox menuRoot = new VBox();
        VBox.setVgrow(menuRoot, Priority.ALWAYS);
        menuRoot.getStyleClass().add("start-menu-root");
        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setOnAction( e -> {
                this.root.getChildren().clear();
                this.root.getChildren().add(createStartMenu());
                resizeWindow();
        });
        menuRoot.getChildren().add(
                mainMenuButton
                //createButtonWithAction("Character Creation", switchWindowEventHandler(createPlayerCreationView()))
                //createExitButton("Exit")
        );
        return menuRoot;
    }
//    private Node createGameView(){
//        return new VBox(
//                //createButtonWithAction("Garden", switchWindowEventHandler(createCultivationView())),
//                //createButtonWithAction("Workbench", switchWindowEventHandler(createWorkBenchView())),
//                //createButtonWithAction("Laboratory", switchWindowEventHandler(createLaboratoryView())),
//                //createButtonWithAction("Market", switchWindowEventHandler(createMarketView())),
//                //createButtonWithAction("Clients", switchWindowEventHandler(createClientsView())),
//                createButtonWithAction("Main Menu", switchWindowEventHandler(createStartMenu()))
//        );
//    }
//
//    private Node createCultivationView() {
//        return new VBox();
//    }
//
//    private Node createClientsView() {
//        return new VBox();
//    }
//
//    private Node createMarketView() {
//        return new VBox();
//    }
//
//    private Node createLaboratoryView() {
//        return new VBox();
//    }
//
//    private Node createWorkBenchView() {
//        return new VBox();
//    }
}
