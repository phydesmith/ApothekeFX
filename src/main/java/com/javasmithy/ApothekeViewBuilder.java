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

import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.javasmithy.widgetsfx.ButtonWidgets.*;

public class ApothekeViewBuilder implements Builder<Region> {

    private final ApothekeModel model;
    private final StackPane root;
    private final Consumer<ApothekeSkill> incrementHandler;
    private final Consumer<ApothekeSkill> decrementHandler;
    private final Consumer<String> playerPortraitPathHandler;

    public ApothekeViewBuilder(ApothekeModel model, Consumer<ApothekeSkill> incrementHandler, Consumer<ApothekeSkill> decrementHandler, Consumer<String> playerPortraitPathHandler) {
        this.model = model;
        this.root = new StackPane();
        this.incrementHandler = incrementHandler;
        this.decrementHandler = decrementHandler;
        this.playerPortraitPathHandler = playerPortraitPathHandler;
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
                skillLineContainer(ApothekeSkill.CULTIVATION, model.playerCultivationSkillValueProperty()),
                skillLineContainer(ApothekeSkill.DIAGNOSIS,  model.playerDiagnosisSkillValueProperty()),
                skillLineContainer(ApothekeSkill.EXTRACTION,  model.playerExtractionSkillValueProperty()),
                skillLineContainer(ApothekeSkill.SYNTHESIS,  model.playerSynthesisSkillValueProperty()),
                allocatableSkillPointsContainer()
        );
    }

    private Node skillLineContainer(ApothekeSkill skill, SimpleIntegerProperty skillValue){
        HBox skillLineContainer = new HBox();
        Label skillLabel = new Label(skill.toString());
        Tooltip skillToolTip = new Tooltip(skill.getDescription());
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
        LinkedList<String> portraitList = new LinkedList<>();
        portraitList.add("assets/images/portraits/portrait1.png");
        portraitList.add("assets/images/portraits/portrait2.png");
        portraitList.add("assets/images/portraits/portrait3.png");

        this.playerPortraitPathHandler.accept(portraitList.getFirst());
        Image image = new Image(this.model.getPlayerPortraitPath());
        System.out.println(image.getUrl());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(330);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        HBox portraitButtonContainer = new HBox();
        Button leftPortraitButton = new Button(" < ");
        leftPortraitButton.setOnAction( e -> {
            portraitList.addLast(portraitList.remove());
            this.playerPortraitPathHandler.accept(portraitList.getFirst());
            imageView.setImage(new Image(this.model.getPlayerPortraitPath()));
        });
        Button rightPortraitButton = new Button(" > " );
        rightPortraitButton.setOnAction( e -> {
            portraitList.addFirst(portraitList.removeLast());
            this.playerPortraitPathHandler.accept(portraitList.getFirst());
            imageView.setImage(new Image(this.model.getPlayerPortraitPath()));
        });
        portraitButtonContainer.getChildren().addAll(
                leftPortraitButton,
                rightPortraitButton
        );



        VBox portraitSelectionContainer = new VBox();
        portraitSelectionContainer.getChildren().addAll(
                imageView,
                portraitButtonContainer
        );

        return portraitSelectionContainer ;
    }

    private Node createGameView(){
        VBox actionMenu = new VBox();
        actionMenu.getChildren().addAll(
                createButtonWithAction("Garden", switchWindowEventHandler(this::createCultivationView)),
                createButtonWithAction("Workbench", switchWindowEventHandler(this::createWorkBenchView)),
                createButtonWithAction("Laboratory", switchWindowEventHandler(this::createLaboratoryView)),
                createButtonWithAction("Market", switchWindowEventHandler(this::createMarketView)),
                createButtonWithAction("Clients", switchWindowEventHandler(this::createClientsView)),
                createButtonWithAction("Main Menu", switchWindowEventHandler(this::createStartMenu))
        );
        BorderPane gameViewRoot = new BorderPane();
        gameViewRoot.getStyleClass().add("start-menu-root");
        gameViewRoot.setLeft(createCharacterView());
        gameViewRoot.setRight(actionMenu);
        return gameViewRoot;
    }

    private Node createCharacterView() {
        Image image = new Image(this.model.getPlayerPortraitPath());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(165);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);
        Label playerNameLabel = new Label();
        playerNameLabel.textProperty().bind(this.model.playerNameProperty());
        return new VBox(
                imageView,
                playerNameLabel,
                skillLinesReadOnly(ApothekeSkill.CULTIVATION, this.model.playerCultivationSkillValueProperty()),
                skillLinesReadOnly(ApothekeSkill.DIAGNOSIS, this.model.playerDiagnosisSkillValueProperty()),
                skillLinesReadOnly(ApothekeSkill.EXTRACTION, this.model.playerExtractionSkillValueProperty()),
                skillLinesReadOnly(ApothekeSkill.SYNTHESIS, this.model.playerSynthesisSkillValueProperty())
        );
    }
    private Node skillLinesReadOnly(ApothekeSkill skill, SimpleIntegerProperty skillValue){
        HBox skillLineContainer = new HBox();
        Label skillLabel = new Label(skill.toString());
        Tooltip skillToolTip = new Tooltip(skill.getDescription());
        skillToolTip.setShowDelay(Duration.ZERO);
        Tooltip.install(skillLabel, skillToolTip);
        Label skillValueLabel = new Label();
        skillValueLabel.textProperty().bindBidirectional(skillValue, new NumberStringConverter());
        skillLineContainer.getChildren().addAll(
                skillLabel,
                skillValueLabel
        );
        return skillLineContainer;
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
