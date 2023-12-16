package com.javasmithy.data.skills;

/**
 * Enums outlining the skills of the game
 */
public enum ApothekeSkill {
    CULTIVATION("Cultivation", "Skill with growing medicinal plants."),
    DIAGNOSIS("Diagnosis", "Skill diagnosing ailments and what ingredients treat them."),
    EXTRACTION("Extraction", "Skill with extracting compounds from raw materials."),
    SYNTHESIS("Synthesis", "Skill creating remedies from ingredients.");

    /**
     * Friendly name to display in GUI
     */
    private final String friendlyName;
    /**
     * Brief description about purpose of skill
     */
    private final String description;

    ApothekeSkill(String friendlyName, String description){
        this.friendlyName = friendlyName;
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    @Override
    public String toString(){
        return this.friendlyName;
    }
}
