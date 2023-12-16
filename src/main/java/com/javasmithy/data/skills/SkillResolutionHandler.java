package com.javasmithy.data.skills;

import com.javasmithy.ApothekeModel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Interface class for handling skill resolution actions used for Strategy Pattern
 */
public interface SkillResolutionHandler {
    public int getSkillVal(ApothekeModel model);
    public void setSkillVal(ApothekeModel model, int value);
    public int getSavedSkillVal(ApothekeModel model);
    public void setSavedSkillVal(ApothekeModel model, int value);
}




