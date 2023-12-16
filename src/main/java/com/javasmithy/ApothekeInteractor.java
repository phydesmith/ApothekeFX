package com.javasmithy;

import com.javasmithy.data.skills.*;

import java.util.HashMap;
import java.util.Map;

/**
 * where the controller and view send actions to interact with the model and trigger business logic
 */
public class ApothekeInteractor {
    /**
     * Instance of the Model
     */
    private final ApothekeModel model;
    /**
     * Map used in the strategy pattern
     */
    private Map<ApothekeSkill, SkillResolutionHandler> skillHandlerMap;

    /**
     * Constructor, model is passed from controller, inits skillhandlermap
     * @param model
     */
    public ApothekeInteractor(ApothekeModel model) {
        this.model = model;
        initSkillHandlerMap();
    }

    /**
     * Initiates the skill handler map for strategy pattern
     */
    private void initSkillHandlerMap() {
        skillHandlerMap = new HashMap<>();
        skillHandlerMap.put(ApothekeSkill.CULTIVATION, new CultivationHandler());
        skillHandlerMap.put(ApothekeSkill.DIAGNOSIS, new DiagnosisHandler());
        skillHandlerMap.put(ApothekeSkill.EXTRACTION, new ExtractionHandler());
        skillHandlerMap.put(ApothekeSkill.SYNTHESIS, new SynthesisHandler());
    }

    /**
     * modifies the model and skill of player, checks for allocatable skillpoints > 0
     * @param skill skill to change
     */
    public void incrementSkillValue(ApothekeSkill skill) {
        SkillResolutionHandler skillHandler = this.skillHandlerMap.get(skill);
        if (this.model.getSkillPointsToAllocate() > 0){
            int skillValue = skillHandler.getSkillVal(this.model);
            skillHandler.setSkillVal(this.model, ++skillValue);
            changeAllocatableSkillPoints(-1);
        }
    }

    /**
     * modifies the model and skill of player, checks for last saved skill to prevent player from subtracting skills
     * @param skill skill to change
     */
    public void decrementSkillValue(ApothekeSkill skill) {
        SkillResolutionHandler skillHandler = this.skillHandlerMap.get(skill);
        if (skillHandler.getSkillVal(this.model) > skillHandler.getSavedSkillVal(this.model)) {
            int skillValue = skillHandler.getSkillVal(this.model);
            skillHandler.setSkillVal(this.model, --skillValue);
            changeAllocatableSkillPoints(1);
        }

    }

    /**
     * Ads to the amount of available skill points to allocate
     * @param value - the amount to add to current value
     */
    private void changeAllocatableSkillPoints(int value){
        int skillPointsToAllocate = this.model.getSkillPointsToAllocate();
        skillPointsToAllocate += value;
        this.model.setSkillPointsToAllocate(skillPointsToAllocate);
    }

    /**
     * Updates the model property of player portrait path
     * @param path - the path of the player portrait
     */
    public void updatePlayerPortraitPath(String path){
        this.model.setPlayerPortraitPath(path);
    }
}
