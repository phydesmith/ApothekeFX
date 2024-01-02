/*
GOD BLESS THIS MESS
 */

package com.javasmithy;

import com.javasmithy.data.entity.Entity;
import com.javasmithy.data.skills.ApothekeSkill;
import com.javasmithy.widgetsfx.ButtonWidgets;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Builder;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.javasmithy.widgetsfx.ButtonWidgets.*;

/**
 * Contains all the logic for building the view
 */
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

    /**
     * The root of the scene
     * @return - the root Region of the scene
     */
    @Override
    public Region build() {
        root.getStylesheets().add("style/style.css");
        root.getChildren().add(createStartMenu());
        return root;
    }

    /**
     * An event handler to specify the next view method to load
     * @param destination - the next method call to create in the form of a supplier
     * @return - an event handler that points to next view to create, uses supplier to call method
     */
    private EventHandler<ActionEvent> switchWindowEventHandler(Supplier<Node> destination){
        return e -> {
            this.root.getChildren().clear();
            this.root.getChildren().add(destination.get());
            resizeWindow();
        };
    }

    /**
     * An event handler specific to character creation, sets 'continue' button to enabled
     * @param destination - the next method call to create in the form of a supplier
     * @return - an event handler that points to next view to create, uses supplier to call method
     */
    private EventHandler<ActionEvent> characterCreationSubmitHandler(Supplier<Node> destination){
        this.model.setDisableContinueButton(false);
        return switchWindowEventHandler(destination);
    }

    /**
     * Helper method that resizes window to ensure newly loaded view is resized correctly
     */
    private void resizeWindow(){
        Platform.runLater( () -> {
            this.root.getScene().getWindow().sizeToScene();
        });
    }

    private Node createStartMenu(){
        VBox menuRoot = new VBox();
        menuRoot.getStyleClass().add("game-background-skin");
        Label startMenuHeader = new Label("Apotheke");
        startMenuHeader.getStyleClass().add("header");
        Button saveButton = new Button("Save");
        Button loadButton = new Button("Load");
        Button exitButton = createExitButton("Exit Game");
        menuRoot.getChildren().addAll(
                startMenuHeader,
                createButtonWithAction("Start Game", switchWindowEventHandler(this::createPlayerCreationView)),
                createButtonWithActionAndBinding("Continue", switchWindowEventHandler(this::createGameView), this.model.disableContinueButtonProperty()),
                saveButton,
                loadButton,
                exitButton);
        return menuRoot;
    }

    private Node createPlayerCreationView(){
        VBox vbox = new VBox();
        vbox.getStyleClass().add("game-background-skin");
        vbox.getChildren().addAll(
               new HBox( createPortraitSelector(),
                            createSkillUpdater()),
                createButtonWithAction("Submit", characterCreationSubmitHandler(this::createGameView))
        );
        return vbox;
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
        skillDecreaseButton.getStyleClass().add("small-button");
        skillDecreaseButton.setOnAction( e -> {
            decrementHandler.accept(skill);
        });
        Button skillIncreaseButton = new Button(" + " );
        skillIncreaseButton.getStyleClass().add("small-button");
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
        LinkedList<String> portraitList = this.model.getPortraitPathList();

        this.playerPortraitPathHandler.accept(portraitList.getFirst());
        Image image = new Image(this.model.getPlayerPortraitPath());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(330);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        HBox portraitButtonContainer = new HBox();
        Button leftPortraitButton = new Button(" < ");
        leftPortraitButton.getStyleClass().add("small-button");
        leftPortraitButton.setOnAction( e -> {
            portraitList.addLast(portraitList.remove());
            this.playerPortraitPathHandler.accept(portraitList.getFirst());
            imageView.setImage(new Image(this.model.getPlayerPortraitPath()));
        });
        Button rightPortraitButton = new Button(" > " );
        rightPortraitButton.getStyleClass().add("small-button");
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
        gameViewRoot.getStyleClass().add("game-background-skin");
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
                skillLinesReadOnly(ApothekeSkill.SYNTHESIS, this.model.playerSynthesisSkillValueProperty()),
                new HBox(
                        labeledValue("Gold: ", this.model.playerMoneyProperty() ),
                        labeledValue("Reputation: ", this.model.playerReputationProperty())
                )
        );
    }
    private Node labeledValue(String text, SimpleIntegerProperty value){
        Label label = new Label();
        label.setText(text);
        Label valueLabel = new Label();
        valueLabel.textProperty().bind(value.asString());
        return new HBox(
                label,
                valueLabel
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
        BorderPane cultivationRoot = new BorderPane();

        cultivationRoot.setTop(createInventoryButton());
        cultivationRoot.getStyleClass().add("game-view-root");
        cultivationRoot.getStyleClass().add("game-background-skin");
        cultivationRoot.setRight(createInventoryViewPane(12, Orientation.VERTICAL));
        cultivationRoot.setLeft(createGardenPlotView());
        Button button = createButtonWithAction("Return", switchWindowEventHandler(this::createGameView));
        BorderPane.setAlignment(button, Pos.CENTER);
        BorderPane.setMargin(button, new Insets(20));
        cultivationRoot.setBottom(button);
        return cultivationRoot;
    }

    private Node createInventoryButton() {
        return ButtonWidgets.createButtonWithAction("Inventory", e -> {
            ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Login Dialog");
            dialog.setContentText("Would you like to log in?");
            dialog.getDialogPane().getButtonTypes().add(loginButtonType);
            boolean disabled = false; // computed based on content of text fields, for example
            dialog.getDialogPane().lookupButton(loginButtonType).setDisable(disabled);
            dialog.showAndWait();
        });
    }

    private Node createGardenPlotView(){
        VBox vBox = new VBox();
        Label gardenPlotHeader = new Label("Garden Plot");
        Label gardenPlotStatus = new Label("");
        gardenPlotStatus.textProperty().bind(this.model.currentCultivarTimeLeftProperty());
        gardenPlotStatus.getStyleClass().add("small-text-label");
        Button harvestButton = new Button("Harvest");
        harvestButton.setDisable(true);
        vBox.getChildren().addAll(
                gardenPlotHeader,
                createSingleCraftingNode(),
                gardenPlotStatus,
                harvestButton
        );
        BorderPane.setAlignment(vBox, Pos.CENTER);
        return vBox;
    }

    private Node createSingleCraftingNode(){
        ImageView destinationImageView = new ImageView();
        destinationImageView.setFitWidth(32);
        destinationImageView.setFitHeight(32);
        destinationImageView.setPreserveRatio(true);
        StackPane craftNode = new StackPane(destinationImageView);
        craftNode.setMaxHeight(32);
        craftNode.setMaxWidth(32);
        craftNode.getStyleClass().add("crafting-node-bordered");

        craftNode.setOnDragEntered(e -> {
            if (e.getGestureSource() != craftNode && e.getDragboard().hasImage()) {
                craftNode.setBackground(Background.fill(Color.AZURE));
            }
        });

        craftNode.setOnDragExited(e -> {
            if (e.getGestureSource() != craftNode && e.getDragboard().hasImage()) {
                craftNode.setBackground(Background.fill(Color.LIGHTGREEN));
            }
        });

        craftNode.setOnDragOver(e -> {
            if (e.getGestureSource() != destinationImageView && e.getDragboard().hasImage()) {
                e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            e.consume();
        });

        craftNode.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                destinationImageView.setImage(db.getImage());
                success = true;
            }
            e.setDropCompleted(success);
            e.consume();
        });

//        destinationImageView.setOnDragOver(e -> {
//            if (e.getGestureSource() != destinationImageView && e.getDragboard().hasImage()) {
//                e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//            }
//            e.consume();
//        });
//
//        destinationImageView.setOnDragEntered(e -> {
//            if (e.getGestureSource() != destinationImageView && e.getDragboard().hasImage()) {
//                craftNode.setBackground(Background.fill(Color.AZURE));
//            }
//            e.consume();
//        });
//
//        destinationImageView.setOnDragExited(e -> {
//            if (e.getGestureSource() != destinationImageView && e.getDragboard().hasImage()) {
//                craftNode.setBackground(Background.fill(Color.LIGHTGREEN));
//            }
//            e.consume();
//        });
//
//        destinationImageView.setOnDragDropped(e -> {
//           Dragboard db = e.getDragboard();
//           boolean success = false;
//           if (db.hasImage()) {
//               destinationImageView.setImage(db.getImage());
//               success = true;
//           }
//           e.setDropCompleted(success);
//           e.consume();
//        });



        return craftNode;
    }

    private Node createInventoryViewPane(int size, Orientation orientation) {
        Button leftInventoryScrollButton = new Button(" < ");
        leftInventoryScrollButton.getStyleClass().add("small-button");
        Button rightInventoryScrollButton = new Button(" > " );
        rightInventoryScrollButton.getStyleClass().add("small-button");

        List<Entity> inventoryBatch = this.model.getPlayerInventoryBatch(0, size);
        if (inventoryBatch.isEmpty()) return new FlowPane();

        FlowPane flowPane = new FlowPane(orientation);
        flowPane.setVgap(8);
        flowPane.setHgap(4);
        flowPane.setPrefWrapLength(300);
        for (Entity e : inventoryBatch) {
            flowPane.getChildren().add(createInventoryItemView(32, 32, e));
        }

        VBox vbox = new VBox();
        BorderPane.setAlignment(vbox, Pos.CENTER);
        vbox.getChildren().addAll(
            new Label("Inventory"),
            flowPane,
            new HBox(
                leftInventoryScrollButton,
                rightInventoryScrollButton
            )
        );
        return vbox;
    }
    private Node createInventoryItemView(int fitWidth, int fitHeight, Entity entity){
        ImageView imageView = new ImageView();
        imageView.setFitWidth(fitWidth);
        imageView.setFitHeight(fitHeight);
        imageView.setPreserveRatio(true);
        imageView.setImage(entity.getSprite());

        imageView.setOnDragDetected( e -> {
            Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(imageView.getImage());

            db.setContent(content);
            e.consume();

        });

        imageView.setOnDragDone( e -> {
            if (e.getTransferMode() == TransferMode.MOVE){
                imageView.setImage(null);
            }
        });

        Tooltip tooltip = new Tooltip(entity.getDescription());
        tooltip.setShowDelay(Duration.ZERO);
        Tooltip.install(imageView, tooltip);



        return imageView;
    }

    private Node createClientsView() {
        BorderPane clientViewRoot = new BorderPane();
        clientViewRoot.getStyleClass().addAll("game-view-root", "game-background-skin");
        Image image = new Image(this.model.getClientPortraitPath());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(165);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);
        Label clientNameLabel = new Label();
        clientNameLabel.textProperty().bind(this.model.clientNameProperty());
        clientViewRoot.setLeft(new VBox(
                imageView,
                clientNameLabel
        ));

        clientViewRoot.setRight(
                clientDescriptionLines()
        );

        Button button = createButtonWithAction("Return", switchWindowEventHandler(this::createGameView));
        BorderPane.setAlignment(button, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(button, new Insets(50));
        clientViewRoot.setBottom(button);

        return clientViewRoot;
    }

    private Node clientDescriptionLines(){
        VBox clientLinesRoot = new VBox();
        clientLinesRoot.getChildren().addAll(
                createClientDescriptionLine(this.model.clientAilmentDescriptionLine1Property(), this.model.has1DiagnosisProperty()),
                createClientDescriptionLine(this.model.clientAilmentDescriptionLine1Property(), this.model.has10DiagnosisProperty()),
                createClientDescriptionLine(this.model.clientAilmentDescriptionLine1Property(), this.model.has20DiagnosisProperty()),
                createClientDescriptionLine(this.model.clientAilmentDescriptionLine1Property(), this.model.has30DiagnosisProperty())
        );
        return clientLinesRoot;
    }

    private Node createClientDescriptionLine(SimpleStringProperty property, SimpleBooleanProperty diagnosisThresholdProperty) {
        StackPane descriptionLineRoot = new StackPane();
        Label hideDescriptionLabel = new Label("?????????????????????????????????????");
        hideDescriptionLabel.visibleProperty().bind(diagnosisThresholdProperty.not());
        Label descriptionLabel = new Label();
        descriptionLabel.visibleProperty().bind(diagnosisThresholdProperty);
        descriptionLabel.textProperty().bind(property);
        descriptionLineRoot.getChildren().addAll(
                descriptionLabel,
                hideDescriptionLabel
        );
        return descriptionLineRoot;
    }

    private Node createMarketView() {
        BorderPane marketViewRoot = new BorderPane();
        marketViewRoot.getStyleClass().add("game-view-root");
        marketViewRoot.getStyleClass().add("game-background-skin");
        marketViewRoot.setTop(createMarketInventoryPane(12, Orientation.HORIZONTAL, "Buy"));
        marketViewRoot.setCenter(createMarketInventoryPane(12, Orientation.HORIZONTAL, "Sell"));
        Button button = createButtonWithAction("Return", switchWindowEventHandler(this::createGameView));
        BorderPane.setAlignment(button, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(button, new Insets(50));

        marketViewRoot.setBottom(
                button
        );
        return marketViewRoot;
    }

    private Node createMarketInventoryPane(int size, Orientation orientation, String buttonText){
        Button leftInventoryScrollButton = new Button(" < ");
        leftInventoryScrollButton.getStyleClass().add("small-button");
        Button rightInventoryScrollButton = new Button(" > " );
        rightInventoryScrollButton.getStyleClass().add("small-button");
        Button sellButton = new Button(buttonText);

        List<Entity> inventoryBatch = this.model.getPlayerInventoryBatch(0, size);
        if (inventoryBatch.isEmpty()) return new FlowPane();

        FlowPane flowPane = new FlowPane(orientation);
        flowPane.setVgap(8);
        flowPane.setHgap(4);
        flowPane.setPrefWrapLength(300);
        for (Entity e : inventoryBatch) {
            flowPane.getChildren().add(createInventoryItemView(32, 32, e));
        }

        VBox vbox = new VBox();
        BorderPane.setAlignment(vbox, Pos.CENTER);
        vbox.getChildren().addAll(
                new Label("Inventory"),
                flowPane,
                new HBox(
                        leftInventoryScrollButton,
                        sellButton,
                        rightInventoryScrollButton
                )
        );
        return vbox;
    }

    private Node createLaboratoryView() {
        BorderPane laboratoryRoot = new BorderPane();
        laboratoryRoot.getStyleClass().add("game-view-root");
        laboratoryRoot.getStyleClass().add("game-background-skin");
        laboratoryRoot.setTop(createInventoryViewPane(12, Orientation.HORIZONTAL));
        Button button = createButtonWithAction("Return", switchWindowEventHandler(this::createGameView));
        BorderPane.setAlignment(button, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(button, new Insets(50));
        laboratoryRoot.setCenter(createLabNode());
        laboratoryRoot.setBottom(
            button
        );
        return laboratoryRoot;
    }

    private Node createLabNode() {
        VBox vBox = new VBox();
        Label labNodeHeader = new Label("Laboratory");
        Button synthesizeButton = new Button("Synthesize");
        synthesizeButton.setDisable(true);
        vBox.getChildren().addAll(
                labNodeHeader,
                new HBox(
                        createSingleCraftingNode(),
                        createSingleCraftingNode(),
                        createSingleCraftingNode()
                ),
                createSingleCraftingNode(),
                synthesizeButton
        );
        BorderPane.setAlignment(vBox, Pos.CENTER);
        return vBox;
    }

    private Node createWorkBenchView() {
        BorderPane workBenchRoot = new BorderPane();
        workBenchRoot.getStyleClass().add("game-view-root");
        workBenchRoot.getStyleClass().add("game-background-skin");
        workBenchRoot.setRight(createInventoryViewPane(12, Orientation.VERTICAL));
        workBenchRoot.setLeft(createWorkBenchNode());
        Button button = createButtonWithAction("Return", switchWindowEventHandler(this::createGameView));
        BorderPane.setAlignment(button, Pos.CENTER);
        BorderPane.setMargin(button, new Insets(20));
        workBenchRoot.setBottom(button);
        return workBenchRoot;

    }

    private Node createWorkBenchNode() {
        VBox vBox = new VBox();
        Label workBenchNodeHeader = new Label("Work Bench");
        Button extractButton = new Button("Extract");
        extractButton.setDisable(true);
        vBox.getChildren().addAll(
                workBenchNodeHeader,
                createSingleCraftingNode(),
                new HBox(
                        createSingleCraftingNode(),
                        createSingleCraftingNode()
                ),
                extractButton
        );
        BorderPane.setAlignment(vBox, Pos.CENTER);
        return vBox;
    }


}
