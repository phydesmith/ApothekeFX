package com.javasmithy.data.entity.factory;

import com.javasmithy.data.adjectives.Adjective;
import com.javasmithy.data.entity.EntityType;
import com.javasmithy.data.entity.Humour;
import com.javasmithy.data.entity.TypedEntity;

public class AilmentEntity extends TypedEntity {

    private int challengeLevel;

    public AilmentEntity(String spritePath, String description, Humour humour, EntityType type, Adjective adjective, int challengeLevel) {
        super(spritePath, description, humour, type, adjective);
    }

    public int getChallengeLevel() {
        return challengeLevel;
    }

    public void setChallengeLevel(int challengeLevel) {
        this.challengeLevel = challengeLevel;
    }

}
