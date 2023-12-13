package com.javasmithy.skills;

public enum ApothekeSkill {
    CULTIVATION("Cultivation", "Skill with growing medicinal plants."),
    DIAGNOSIS("Diagnosis", "Skill diagnosing ailments and what ingredients treat them."),
    EXTRACTION("Extraction", "Skill with extracting compounds from raw materials."),
    SYNTHESIS("Synthesis", "Skill creating remedies from ingredients.");

    private final String friendlyName;
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
