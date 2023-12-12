package com.javasmithy;

import com.javasmithy.skills.ApothekeSkill;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.javasmithy.widgetsfx.ButtonWidgets.*;

public class ApothekeViewBuilder implements Builder<Region> {

    private final ApothekeModel model;
    private final StackPane root;
    private final Consumer<ApothekeSkill> incrementHandler;
    private final Consumer<ApothekeSkill> decrementHandler;

    public ApothekeViewBuilder(ApothekeModel model, Consumer<ApothekeSkill> incrementHandler, Consumer<ApothekeSkill> decrementHandler) {
        this.model = model;
        this.root = new StackPane();
        this.incrementHandler = incrementHandler;
        this.decrementHandler = decrementHandler;
    }

    @Override
    public Region build() {
        root.getStylesheets().add("style/style.css");
        root.getChildren().add(createStartMenu());
        return root;
    }

    private EventHandler<ActionEvent> switchWindowEventHandler(Supplier<Node> destination){
        return e -> {
            this.root.getChildren().clear();
            this.root.getChildren().add(destination.get());
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
        menuRoot.getStyleClass().add("start-menu-root");
        Label startMenuHeader = new Label("Apotheke");
        startMenuHeader.getStyleClass().add("header");
        Button optionsButton = new Button("Options");
        Button exitButton = createExitButton("Exit Game");
        menuRoot.getChildren().addAll(
                startMenuHeader,
                createButtonWithAction("Start Game", switchWindowEventHandler(this::createPlayerCreationView)),
                optionsButton,
                exitButton);
        return menuRoot;
    }

    private Node createPlayerCreationView(){
        return new VBox(
               new HBox( createPortraitSelector(),
                            createSkillUpdater()),
                createButtonWithAction("Submit", switchWindowEventHandler(this::createGameView))
        );
    }

    private Node createSkillUpdater() {
        return new VBox(
                playerNameChoiceContainer(),
                skillLineContainer(ApothekeSkill.CULTIVATION, "Cultivation Description", model.playerCultivationSkillValueProperty()),
                skillLineContainer(ApothekeSkill.EXTRACTION, "Extraction Description", model.playerExtractionSkillValueProperty()),
                skillLineContainer(ApothekeSkill.SYNTHESIS, "Synthesis Description", model.playerSynthesisSkillValueProperty()),
                skillLineContainer(ApothekeSkill.DIAGNOSIS, "Diagnosis Description", model.playerDiagnosisSkillValueProperty()),
                allocatableSkillPointsContainer()
        );
    }

    private Node skillLineContainer(ApothekeSkill skill, String skillDescription, SimpleIntegerProperty skillValue){
        HBox skillLineContainer = new HBox();
        Label skillLabel = new Label(skill.toString());
        Tooltip skillToolTip = new Tooltip(skillDescription);
        skillToolTip.setShowDelay(Duration.ZERO);
        Tooltip.install(skillLabel, skillToolTip);
        Button skillDecreaseButton = new Button(" - ");
        skillDecreaseButton.setOnAction( e -> {
            decrementHandler.accept(skill);
        });
        Button skillIncreaseButton = new Button(" + " );
        skillIncreaseButton.setOnAction( e -> {
            incrementHandler.accept(skill);
        });
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
        menuRoot.getStyleClass().add("start-menu-root");
        menuRoot.getChildren().addAll(
                createButtonWithAction("Garden", switchWindowEventHandler(this::createCultivationView)),
                createButtonWithAction("Workbench", switchWindowEventHandler(this::createWorkBenchView)),
                createButtonWithAction("Laboratory", switchWindowEventHandler(this::createLaboratoryView)),
                createButtonWithAction("Market", switchWindowEventHandler(this::createMarketView)),
                createButtonWithAction("Clients", switchWindowEventHandler(this::createClientsView)),
                createButtonWithAction("Main Menu", switchWindowEventHandler(this::createStartMenu))
        );
        return menuRoot;
    }

    private Node createCultivationView() {
        return new VBox(
                createButtonWithAction("Return", switchWindowEventHandler(this::createGameView))
        );
    }

    private Node createClientsView() {
        return new VBox(
                createButtonWithAction("Return", switchWindowEventHandler(this::createGameView))
        );
    }

    private Node createMarketView() {
        return new VBox(
                createButtonWithAction("Return", switchWindowEventHandler(this::createGameView))
        );
    }

    private Node createLaboratoryView() {
        return new VBox(
                createButtonWithAction("Return", switchWindowEventHandler(this::createGameView))
        );
    }

    private Node createWorkBenchView() {
        return new VBox(
                createButtonWithAction("Return", switchWindowEventHandler(this::createGameView))
        );
    }
}
