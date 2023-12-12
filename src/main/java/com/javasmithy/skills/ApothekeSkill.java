package com.javasmithy.skills;

public enum ApothekeSkill {
    CULTIVATION("Cultivation"), DIAGNOSIS("Diagnosis"), EXTRACTION("Extraction"), SYNTHESIS("Synthesis");

    private final String friendlyName;
    ApothekeSkill(String friendlyName){
        this.friendlyName = friendlyName;
    }

    @Override
    public String toString(){
        return this.friendlyName;
    }
}
