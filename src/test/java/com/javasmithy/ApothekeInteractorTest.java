package com.javasmithy;

import com.javasmithy.skills.ApothekeSkill;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApothekeInteractorTest {

    @Test
    void increment() {
        ApothekeModel apothekeModel= new ApothekeModel();
        ApothekeInteractor interactor = new ApothekeInteractor(apothekeModel);
        apothekeModel.setSkillPointsToAllocate(1);

        int expectedValue = (apothekeModel.getPlayerCultivationSkillValue() + 1);
        interactor.incrementSkillValue(ApothekeSkill.CULTIVATION);
        assertEquals(expectedValue, apothekeModel.getPlayerCultivationSkillValue(), "Incrementing gives value + 1");

        expectedValue = (apothekeModel.getPlayerExtractionSkillValue() + 1);
        interactor.incrementSkillValue(ApothekeSkill.EXTRACTION);
        assertEquals(expectedValue, apothekeModel.getPlayerExtractionSkillValue(), "Incrementing gives value + 1");

        expectedValue = (apothekeModel.getPlayerSynthesisSkillValue() + 1);
        interactor.incrementSkillValue(ApothekeSkill.SYNTHESIS);
        assertEquals(expectedValue, apothekeModel.getPlayerSynthesisSkillValue(), "Incrementing gives value + 1");

        expectedValue = (apothekeModel.getPlayerDiagnosisSkillValue() + 1);
        interactor.incrementSkillValue(ApothekeSkill.DIAGNOSIS);
        assertEquals(expectedValue, apothekeModel.getPlayerDiagnosisSkillValue(), "Incrementing gives value + 1");
    }

    @Test
    void decrement() {
        ApothekeModel apothekeModel= new ApothekeModel();
        ApothekeInteractor interactor = new ApothekeInteractor(apothekeModel);
        apothekeModel.setLastSavedPlayerSynthesisSkillValue(1);
        apothekeModel.setLastSavedPlayerDiagnosisSkillValue(1);
        apothekeModel.setLastSavedPlayerCultivationSkillValue(1);
        apothekeModel.setLastSavedPlayerExtractionSkillValue(1);

        int expectedValue = (apothekeModel.getPlayerCultivationSkillValue() - 1);
        interactor.decrementSkillValue(ApothekeSkill.CULTIVATION);
        assertEquals(expectedValue, apothekeModel.getPlayerCultivationSkillValue(), "Incrementing gives value - 1");

        expectedValue = (apothekeModel.getPlayerExtractionSkillValue() - 1);
        interactor.decrementSkillValue(ApothekeSkill.EXTRACTION);
        assertEquals(expectedValue, apothekeModel.getPlayerExtractionSkillValue(), "Incrementing gives value - 1");

        expectedValue = (apothekeModel.getPlayerSynthesisSkillValue() - 1);
        interactor.decrementSkillValue(ApothekeSkill.SYNTHESIS);
        assertEquals(expectedValue, apothekeModel.getPlayerSynthesisSkillValue(), "Incrementing gives value - 1");

        expectedValue = (apothekeModel.getPlayerDiagnosisSkillValue() - 1);
        interactor.decrementSkillValue(ApothekeSkill.DIAGNOSIS);
        assertEquals(expectedValue, apothekeModel.getPlayerDiagnosisSkillValue(), "Incrementing gives value - 1");
    }
}