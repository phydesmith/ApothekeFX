package com.javasmithy.data.skills;

import com.javasmithy.ApothekeModel;

public class CultivationHandler implements SkillResolutionHandler {

    @Override
    public int getSkillVal(ApothekeModel model) {
        return model.getPlayerCultivationSkillValue();
    }

    @Override
    public void setSkillVal(ApothekeModel model, int val) {
        model.setPlayerCultivationSkillValue(val);
    }

    @Override
    public int getSavedSkillVal(ApothekeModel model) {
        return model.getLastSavedPlayerCultivationSkillValue();
    }

    @Override
    public void setSavedSkillVal(ApothekeModel model, int value) {

    }
}
