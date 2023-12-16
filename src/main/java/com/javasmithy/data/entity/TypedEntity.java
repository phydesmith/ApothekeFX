package com.javasmithy.data.entity;

import com.javasmithy.data.adjectives.Adjective;

/**
 * A class that adds Humour, Entity TYpe and Adjective fields to the entity base class
 */
public class TypedEntity extends Entity{
    /**
     *  The humour enum associated with this object.
     */
    Humour humour;
    /**
     * The entity type associated with this object.
     */
    EntityType type;
    /**
     * An adjective object with its own humour, entity types and description.
     */
    Adjective adjective;

    /**
     * For use with loading gson
     * @param spritePath - path of the sprite image
     * @param description - description of object to be used as name
     * @param humour - humour enum
     * @param type - type enum
     * @param adjective - adjective object
     */
    public TypedEntity(String spritePath, String description, Humour humour, EntityType type, Adjective adjective) {
        super(spritePath, description);
        this.humour = humour;
        this.type = type;
        this.adjective = adjective;
    }

    public Humour getHumour() {
        return humour;
    }

    public void setHumour(Humour humour) {
        this.humour = humour;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }


    public Adjective getAdjective() {
        return adjective;
    }

    public void setAdjective(Adjective adjective) {
        this.adjective = adjective;
    }
}
