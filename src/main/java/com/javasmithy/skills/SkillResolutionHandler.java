package com.javasmithy.skills;

import com.javasmithy.ApothekeModel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface SkillResolutionHandler {
    public int getSkillVal(ApothekeModel model);
    public void setSkillVal(ApothekeModel model, int value);
    public int getSavedSkillVal(ApothekeModel model);
    public void setSavedSkillVal(ApothekeModel model, int value);
}

//
//    private void initializeSetterMap() {
//        Map<ApothekeSkill, Consumer<Integer>> setterMap = new HashMap<>();
//        setterMap.put(ApothekeSkill.CULTIVATION, model::setPlayerCultivationSkillValue);
//    }
//
//    private void initializeGetterMap() {
//        Map<ApothekeSkill, Supplier<Integer>> getterMap = new HashMap<>();
//        getterMap.put(ApothekeSkill.CULTIVATION, model::getPlayerCultivationSkillValue);
//    }



