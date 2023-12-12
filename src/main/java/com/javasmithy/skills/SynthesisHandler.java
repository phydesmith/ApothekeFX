package com.javasmithy.skills;

import com.javasmithy.ApothekeModel;

public class SynthesisHandler implements SkillResolutionHandler {


    @Override
    public int getSkillVal(ApothekeModel model) {
        return model.getPlayerSynthesisSkillValue();
    }

    @Override
    public void setSkillVal(ApothekeModel model, int value) {
        model.setPlayerSynthesisSkillValue(value);
    }
}
