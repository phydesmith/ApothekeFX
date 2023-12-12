package com.javasmithy.skills;

import com.javasmithy.ApothekeModel;

public class ExtractionHandler implements SkillResolutionHandler {


    @Override
    public int getSkillVal(ApothekeModel model) {

        return model.getPlayerExtractionSkillValue();
    }

    @Override
    public void setSkillVal(ApothekeModel model, int value) {
        model.setPlayerExtractionSkillValue(value);
    }

    @Override
    public int getSavedSkillVal(ApothekeModel model) {
        return model.getLastSavedPlayerExtractionSkillValue();
    }

    @Override
    public void setSavedSkillVal(ApothekeModel model, int value) {
        model.setPlayerExtractionSkillValue(value);
    }
}
