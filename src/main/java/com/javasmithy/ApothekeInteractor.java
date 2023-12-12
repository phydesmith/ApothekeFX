package com.javasmithy;

import com.javasmithy.skills.*;

import java.util.HashMap;
import java.util.Map;

public class ApothekeInteractor {
    private final ApothekeModel model;
    private Map<ApothekeSkill, SkillResolutionHandler> skillHandlerMap;
    public ApothekeInteractor(ApothekeModel model) {
        this.model = model;
        initSkillHandlerMap();
    }

    private void initSkillHandlerMap() {
        skillHandlerMap = new HashMap<>();
        skillHandlerMap.put(ApothekeSkill.CULTIVATION, new CultivationHandler());
        skillHandlerMap.put(ApothekeSkill.DIAGNOSIS, new DiagnosisHandler());
        skillHandlerMap.put(ApothekeSkill.EXTRACTION, new ExtractionHandler());
        skillHandlerMap.put(ApothekeSkill.SYNTHESIS, new SynthesisHandler());
    }


    public void incrementSkillValue(ApothekeSkill skill) {
        SkillResolutionHandler skillHandler = this.skillHandlerMap.get(skill);
        if (this.model.getSkillPointsToAllocate() > 0){
            int skillValue = skillHandler.getSkillVal(this.model);
            skillHandler.setSkillVal(this.model, ++skillValue);
            changeAllocatableSkillPoints(-1);
        }
    }

    public void decrementSkillValue(ApothekeSkill skill) {
        SkillResolutionHandler skillHandler = this.skillHandlerMap.get(skill);
        if (skillHandler.getSkillVal(this.model) > skillHandler.getSavedSkillVal(this.model)) {
            int skillValue = skillHandler.getSkillVal(this.model);
            skillHandler.setSkillVal(this.model, --skillValue);
            changeAllocatableSkillPoints(1);
        }

    }

    private void changeAllocatableSkillPoints(int value){
        int skillPointsToAllocate = this.model.getSkillPointsToAllocate();
        skillPointsToAllocate += value;
        this.model.setSkillPointsToAllocate(skillPointsToAllocate);
    }
}
