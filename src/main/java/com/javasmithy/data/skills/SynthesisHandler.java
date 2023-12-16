package com.javasmithy.data.skills;

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

    @Override
    public int getSavedSkillVal(ApothekeModel model) {
        return model.getLastSavedPlayerSynthesisSkillValue();
    }

    @Override
    public void setSavedSkillVal(ApothekeModel model, int value) {
        model.setLastSavedPlayerSynthesisSkillValue(value);
    }
}
