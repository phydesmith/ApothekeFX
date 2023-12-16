package com.javasmithy.data.entity.factory;

import com.javasmithy.data.adjectives.Adjective;
import com.javasmithy.data.entity.EntityType;
import com.javasmithy.data.entity.Humour;
import com.javasmithy.data.entity.TypedEntity;

public class AilmentEntity extends TypedEntity {

    /**
     *   int Challenge level associated with ailment
     */
    private int challengeLevel;

    /**
     *  used for GSON loading
     * @param spritePath path to sprite
     * @param description description to act as object name
     * @param humour the humour associated with object
     * @param type the entity type associated with object
     * @param adjective the adjective associated with the object
     * @param challengeLevel the challeng level of the object
     */
    public AilmentEntity(String spritePath, String description, Humour humour, EntityType type, Adjective adjective, int challengeLevel) {
        super(spritePath, description, humour, type, adjective);
    }

    /**
     *
     * @return int representing challenge level
     */
    public int getChallengeLevel() {
        return challengeLevel;
    }

    /**
     *
     * @param challengeLevel - int to set challenge level to
     */
    public void setChallengeLevel(int challengeLevel) {
        this.challengeLevel = challengeLevel;
    }

}
