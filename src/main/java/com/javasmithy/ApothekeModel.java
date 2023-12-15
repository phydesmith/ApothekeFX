package com.javasmithy;

import com.javasmithy.data.DataAccess;
import com.javasmithy.data.NameList;
import com.javasmithy.entity.Entity;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ApothekeModel {
    private final Random RANDOM = new Random();
    private SimpleStringProperty currentCultivarTimeLeft;
    private SimpleIntegerProperty playerCultivationSkillValue;
    private SimpleIntegerProperty playerExtractionSkillValue;
    private SimpleIntegerProperty playerSynthesisSkillValue;
    private SimpleIntegerProperty playerDiagnosisSkillValue;
    private SimpleIntegerProperty lastSavedPlayerCultivationSkillValue;
    private SimpleIntegerProperty lastSavedPlayerExtractionSkillValue;
    private SimpleIntegerProperty lastSavedPlayerSynthesisSkillValue;
    private SimpleIntegerProperty lastSavedPlayerDiagnosisSkillValue;
    private SimpleIntegerProperty skillPointsToAllocate;
    private SimpleStringProperty playerName;
    private SimpleStringProperty playerPortraitPath;
    private SimpleStringProperty clientName;
    private SimpleStringProperty clientPortraitPath;
    private ObservableList<Entity> playerInventory;
    private LinkedList<String> portraitPathList;
    private List<String> clientPortraitPathList;
    private SimpleBooleanProperty disableContinueButton;

    private final NameList nameList;

    public ApothekeModel() {
        this.playerCultivationSkillValue = new SimpleIntegerProperty(3);
        this.playerExtractionSkillValue = new SimpleIntegerProperty(3);
        this.playerSynthesisSkillValue = new SimpleIntegerProperty(3);
        this.playerDiagnosisSkillValue = new SimpleIntegerProperty(3);
        this.lastSavedPlayerCultivationSkillValue = new SimpleIntegerProperty(3);
        this.lastSavedPlayerExtractionSkillValue = new SimpleIntegerProperty(3);
        this.lastSavedPlayerSynthesisSkillValue = new SimpleIntegerProperty(3);
        this.lastSavedPlayerDiagnosisSkillValue = new SimpleIntegerProperty(3);
        this.skillPointsToAllocate = new SimpleIntegerProperty(5);
        this.currentCultivarTimeLeft = new SimpleStringProperty("");
        this.playerName = new SimpleStringProperty("");
        this.clientName = new SimpleStringProperty("");
        this.playerPortraitPath = new SimpleStringProperty("");
        this.clientPortraitPath = new SimpleStringProperty("");
        this.disableContinueButton = new SimpleBooleanProperty(true);
        this.nameList = DataAccess.getInstance().nameList();
        randomizeClientName();
        debugInitializePlayerInventory();
        debugInitializePortraitPathList();
        debugInitializeCurrentCultivarStatus();
        debutInitializeClientPortraitPathList();
    }

    private void debutInitializeClientPortraitPathList() {
        this.clientPortraitPathList = new ArrayList<>();
        clientPortraitPathList.addAll(List.of(
                "assets/images/portraits/client-portrait1.png",
                "assets/images/portraits/client-portrait2.png",
                "assets/images/portraits/client-portrait3.png"
        ));
    }

    private void debugInitializeCurrentCultivarStatus() {
        this.currentCultivarTimeLeft.setValue("1 min 18 seconds");
    }

    private void debugInitializePortraitPathList() {
        this.portraitPathList = new LinkedList<>();
        portraitPathList.addAll(List.of(
                "assets/images/portraits/portrait1.png",
                "assets/images/portraits/portrait2.png",
                "assets/images/portraits/portrait3.png"
        ));
    }

    private void debugInitializePlayerInventory() {
        this.playerInventory = FXCollections.observableArrayList(
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric"),
                new Entity("assets/images/cultivation/harvested/p1-harvested.png", "Harvested Choleric")
        );
    }

    public int getLastSavedPlayerCultivationSkillValue() {
        return lastSavedPlayerCultivationSkillValue.get();
    }

    public SimpleIntegerProperty lastSavedPlayerCultivationSkillValueProperty() {
        return lastSavedPlayerCultivationSkillValue;
    }

    public void setLastSavedPlayerCultivationSkillValue(int lastSavedPlayerCultivationSkillValue) {
        this.lastSavedPlayerCultivationSkillValue.set(lastSavedPlayerCultivationSkillValue);
    }

    public int getLastSavedPlayerExtractionSkillValue() {
        return lastSavedPlayerExtractionSkillValue.get();
    }

    public SimpleIntegerProperty lastSavedPlayerExtractionSkillValueProperty() {
        return lastSavedPlayerExtractionSkillValue;
    }

    public void setLastSavedPlayerExtractionSkillValue(int lastSavedPlayerExtractionSkillValue) {
        this.lastSavedPlayerExtractionSkillValue.set(lastSavedPlayerExtractionSkillValue);
    }

    public int getLastSavedPlayerSynthesisSkillValue() {
        return lastSavedPlayerSynthesisSkillValue.get();
    }

    public SimpleIntegerProperty lastSavedPlayerSynthesisSkillValueProperty() {
        return lastSavedPlayerSynthesisSkillValue;
    }

    public void setLastSavedPlayerSynthesisSkillValue(int lastSavedPlayerSynthesisSkillValue) {
        this.lastSavedPlayerSynthesisSkillValue.set(lastSavedPlayerSynthesisSkillValue);
    }

    public int getLastSavedPlayerDiagnosisSkillValue() {
        return lastSavedPlayerDiagnosisSkillValue.get();
    }

    public SimpleIntegerProperty lastSavedPlayerDiagnosisSkillValueProperty() {
        return lastSavedPlayerDiagnosisSkillValue;
    }

    public void setLastSavedPlayerDiagnosisSkillValue(int lastSavedPlayerDiagnosisSkillValue) {
        this.lastSavedPlayerDiagnosisSkillValue.set(lastSavedPlayerDiagnosisSkillValue);
    }

    public int getSkillPointsToAllocate() {
        return skillPointsToAllocate.get();
    }

    public SimpleIntegerProperty skillPointsToAllocateProperty() {
        return skillPointsToAllocate;
    }

    public void setSkillPointsToAllocate(int skillPointsToAllocate) {
        this.skillPointsToAllocate.set(skillPointsToAllocate);
    }

    public int getPlayerCultivationSkillValue() {
        return playerCultivationSkillValue.get();
    }

    public SimpleIntegerProperty playerCultivationSkillValueProperty() {
        return playerCultivationSkillValue;
    }

    public void setPlayerCultivationSkillValue(int playerCultivationSkillValue) {
        this.playerCultivationSkillValue.set(playerCultivationSkillValue);
    }

    public int getPlayerExtractionSkillValue() {
        return playerExtractionSkillValue.get();
    }

    public SimpleIntegerProperty playerExtractionSkillValueProperty() {
        return playerExtractionSkillValue;
    }

    public void setPlayerExtractionSkillValue(int playerExtractionSkillValue) {
        this.playerExtractionSkillValue.set(playerExtractionSkillValue);
    }

    public int getPlayerSynthesisSkillValue() {
        return playerSynthesisSkillValue.get();
    }

    public SimpleIntegerProperty playerSynthesisSkillValueProperty() {
        return playerSynthesisSkillValue;
    }

    public void setPlayerSynthesisSkillValue(int playerSynthesisSkillValue) {
        this.playerSynthesisSkillValue.set(playerSynthesisSkillValue);
    }

    public int getPlayerDiagnosisSkillValue() {
        return playerDiagnosisSkillValue.get();
    }

    public SimpleIntegerProperty playerDiagnosisSkillValueProperty() {
        return playerDiagnosisSkillValue;
    }

    public void setPlayerDiagnosisSkillValue(int playerDiagnosisSkillValue) {
        this.playerDiagnosisSkillValue.set(playerDiagnosisSkillValue);
    }

    public String getPlayerName() {
        return playerName.get();
    }

    public SimpleStringProperty playerNameProperty() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }

    public String getPlayerPortraitPath() {
        return playerPortraitPath.get();
    }

    public SimpleStringProperty playerPortraitPathProperty() {
        return playerPortraitPath;
    }

    public void setPlayerPortraitPath(String playerPortraitPath) {
        this.playerPortraitPath.set(playerPortraitPath);
    }

    public List<Entity> getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(ObservableList<Entity> playerInventory) {
        this.playerInventory = playerInventory;
    }

    public Entity getInventoryItemAt(int index){
        return this.playerInventory.get(index);
    }

    public List<Entity> getPlayerInventoryBatch(int startIndex, int endIndex){
        try {
            return this.playerInventory.subList(startIndex, endIndex);
        } catch (IndexOutOfBoundsException e){
            System.err.println("Error getting player inventory batch. Given indexes out of bounds for inventory sublist.");
        }
        return new ArrayList<>();
    }

    public LinkedList<String> getPortraitPathList() {
        return portraitPathList;
    }

    public void setPortraitPathList(LinkedList<String> portraitPathList) {
        this.portraitPathList = portraitPathList;
    }

    public boolean getDisableContinueButton() {
        return disableContinueButton.get();
    }

    public SimpleBooleanProperty disableContinueButtonProperty() {
        return disableContinueButton;
    }

    public void setDisableContinueButton(boolean disableContinueButton) {
        this.disableContinueButton.set(disableContinueButton);
    }

    public String getCurrentCultivarTimeLeft() {
        return currentCultivarTimeLeft.get();
    }

    public SimpleStringProperty currentCultivarTimeLeftProperty() {
        return currentCultivarTimeLeft;
    }

    public void setCurrentCultivarTimeLeft(String currentCultivarTimeLeft) {
        this.currentCultivarTimeLeft.set(currentCultivarTimeLeft);
    }

    public NameList nameList() {
        return nameList;
    }



    public String getClientPortraitPath() {
        randomizeClientPortraitPath();
        return clientPortraitPath.get();
    }
    private void randomizeClientPortraitPath(){
        int size = this.clientPortraitPathList.size();
        this.clientPortraitPath.setValue(
                this.clientPortraitPathList.get(RANDOM.nextInt(size))
        );
    }

    public SimpleStringProperty clientPortraitPathProperty() {
        return clientPortraitPath;
    }

    private void randomizeClientName(){
        int size = this.nameList.getNames().size();
        this.clientNameProperty().setValue(
                this.nameList.getNameAt(RANDOM.nextInt(size))
        );
    }
    public String getClientName() {
        randomizeClientName();
        return clientName.get();
    }
    public SimpleStringProperty clientNameProperty() {
        return clientName;
    }

}
