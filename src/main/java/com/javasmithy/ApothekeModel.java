package com.javasmithy;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ApothekeModel {
    private SimpleIntegerProperty playerCultivationSkillValue;
    private SimpleIntegerProperty playerExtractionSkillValue;
    private SimpleIntegerProperty playerSynthesisSkillValue;
    private SimpleIntegerProperty playerDiagnosisSkillValue;
    private SimpleIntegerProperty skillPointsToAllocate;
    private SimpleStringProperty playerName;

    public ApothekeModel() {
        this.playerCultivationSkillValue = new SimpleIntegerProperty(3);
        this.playerExtractionSkillValue = new SimpleIntegerProperty(3);
        this.playerSynthesisSkillValue = new SimpleIntegerProperty(3);
        this.playerDiagnosisSkillValue = new SimpleIntegerProperty(3);
        this.skillPointsToAllocate = new SimpleIntegerProperty(0);
        this.playerName = new SimpleStringProperty("");
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
}
