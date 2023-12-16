package com.javasmithy.data.entity.factory;

import com.javasmithy.data.adjectives.Adjective;
import com.javasmithy.data.entity.EntityType;
import com.javasmithy.data.entity.Humour;
import com.javasmithy.data.entity.TypedEntity;

public class MaterialEntity extends TypedEntity {
    /**
     *  used for GSON loading
     * @param spritePath path to sprite
     * @param description description to act as object name
     * @param humour the humour associated with object
     * @param type the entity type associated with object
     * @param adjective the adjective associated with the object
     */
    public MaterialEntity(String spritePath, String description, Humour humour, EntityType type, Adjective adjective) {
        super(spritePath, description, humour, type, adjective);
    }
}
