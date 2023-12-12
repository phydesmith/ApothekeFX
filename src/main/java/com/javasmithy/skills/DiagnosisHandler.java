package com.javasmithy.skills;

import com.javasmithy.ApothekeModel;

public class DiagnosisHandler implements SkillResolutionHandler {

    @Override
    public int getSkillVal(ApothekeModel model) {
        return model.getPlayerDiagnosisSkillValue();
    }

    @Override
    public void setSkillVal(ApothekeModel model, int value) {
        model.setPlayerDiagnosisSkillValue(value);
    }
}
