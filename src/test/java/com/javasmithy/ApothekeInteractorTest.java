package com.javasmithy;

import com.javasmithy.skills.ApothekeSkill;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApothekeInteractorTest {

    @Test
    void increment() {
        ApothekeModel apothekeModel= new ApothekeModel();
        ApothekeInteractor interactor = new ApothekeInteractor(apothekeModel);

        int expectedValue = (apothekeModel.getPlayerCultivationSkillValue() + 1);
        interactor.increment(ApothekeSkill.CULTIVATION);
        assertEquals(expectedValue, apothekeModel.getPlayerCultivationSkillValue(), "Incrementing gives value + 1");

        expectedValue = (apothekeModel.getPlayerExtractionSkillValue() + 1);
        interactor.increment(ApothekeSkill.EXTRACTION);
        assertEquals(expectedValue, apothekeModel.getPlayerExtractionSkillValue(), "Incrementing gives value + 1");

        expectedValue = (apothekeModel.getPlayerSynthesisSkillValue() + 1);
        interactor.increment(ApothekeSkill.SYNTHESIS);
        assertEquals(expectedValue, apothekeModel.getPlayerSynthesisSkillValue(), "Incrementing gives value + 1");

        expectedValue = (apothekeModel.getPlayerDiagnosisSkillValue() + 1);
        interactor.increment(ApothekeSkill.DIAGNOSIS);
        assertEquals(expectedValue, apothekeModel.getPlayerDiagnosisSkillValue(), "Incrementing gives value + 1");
    }

    @Test
    void decrement() {
        ApothekeModel apothekeModel= new ApothekeModel();
        ApothekeInteractor interactor = new ApothekeInteractor(apothekeModel);

        int expectedValue = (apothekeModel.getPlayerCultivationSkillValue() - 1);
        interactor.decrement(ApothekeSkill.CULTIVATION);
        assertEquals(expectedValue, apothekeModel.getPlayerCultivationSkillValue(), "Incrementing gives value - 1");

        expectedValue = (apothekeModel.getPlayerExtractionSkillValue() - 1);
        interactor.decrement(ApothekeSkill.EXTRACTION);
        assertEquals(expectedValue, apothekeModel.getPlayerExtractionSkillValue(), "Incrementing gives value - 1");

        expectedValue = (apothekeModel.getPlayerSynthesisSkillValue() - 1);
        interactor.decrement(ApothekeSkill.SYNTHESIS);
        assertEquals(expectedValue, apothekeModel.getPlayerSynthesisSkillValue(), "Incrementing gives value - 1");

        expectedValue = (apothekeModel.getPlayerDiagnosisSkillValue() - 1);
        interactor.decrement(ApothekeSkill.DIAGNOSIS);
        assertEquals(expectedValue, apothekeModel.getPlayerDiagnosisSkillValue(), "Incrementing gives value - 1");
    }
}