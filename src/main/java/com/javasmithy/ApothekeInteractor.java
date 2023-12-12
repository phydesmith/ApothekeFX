package com.javasmithy;

import com.javasmithy.skills.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

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


    public void increment(ApothekeSkill skill) {
        int skillValue = skillHandlerMap.get(skill).getSkillVal(this.model);
        skillHandlerMap.get(skill).setSkillVal(this.model, ++skillValue);
    }

    public void decrement(ApothekeSkill skill) {
        int skillValue = skillHandlerMap.get(skill).getSkillVal(this.model);
        skillHandlerMap.get(skill).setSkillVal(this.model, --skillValue);
    }
}
