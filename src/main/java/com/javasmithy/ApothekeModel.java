package com.javasmithy;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;

public class ApothekeModel {
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
        this.playerName = new SimpleStringProperty("");
        this.playerPortraitPath = new SimpleStringProperty("");
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
}
