package com.javasmithy.data.adjectives;

import com.javasmithy.data.entity.EntityType;
import com.javasmithy.data.entity.Humour;

public class Adjective {
    /**
     * Description of adjective.
     */
    String adjective;
    /**
     *  The humour enum associated with this object.
     */
    Humour humour;
    /**
     * The entity type associated with this object.
     */
    EntityType entityType;

    /**
     * Constructor with all parameters for use with gson.
     * @param adjective a string to describe the adjective
     * @param humour a humour enum
     * @param entityType an entity type enum
     */
    public Adjective(String adjective, Humour humour, EntityType entityType) {
        this.adjective = adjective;
        this.humour = humour;
        this.entityType = entityType;
    }

    /**
     *  Gets adjective field.
     * @return the adjective field.
     */
    public String getAdjective() {
        return adjective;
    }

    /**
     *  Sets adjective field.
     * @param adjective string to describe the adjective.
     */
    public void setAdjective(String adjective) {
        this.adjective = adjective;
    }

    /**
     *  Gets Humour enum associated with this object.
     * @return Humour enum
     */
    public Humour getHumour() {
        return humour;
    }

    /**
     * Sets humour enum associated with this object.
     * @param humour humour enum
     */
    public void setHumour(Humour humour) {
        this.humour = humour;
    }

    /**
     * Gets the entity type enum associated with this object.
     * @return entity type enum
     */
    public EntityType getEntityType() {
        return entityType;
    }

    /**
     * Sets the entity type associated with this object.
     * @param entityType entity type enum
     */
    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

}
