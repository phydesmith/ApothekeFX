package com.javasmithy.data.entity.factory;

import com.javasmithy.data.adjectives.Adjective;
import com.javasmithy.data.entity.EntityType;
import com.javasmithy.data.entity.Humour;
import com.javasmithy.data.entity.TypedEntity;

public class CultivarEntity extends TypedEntity {

    /**
     * Growth time in seconds.
     */
    int growthTime;

    /**
     *  used for GSON loading
     * @param spritePath path to sprite
     * @param description description to act as object name
     * @param humour the humour associated with object
     * @param type the entity type associated with object
     * @param adjective the adjective associated with the object
     * @param growthTime an int to signify seconds of growth
     */
    public CultivarEntity(String spritePath, String description, Humour humour, EntityType type, Adjective adjective, int growthTime) {
        super(spritePath, description, humour, type, adjective);
        this.growthTime = growthTime;
    }

    /**
     *
     * @return int representing growth time in seconds
     */
    public int getGrowthTime() {
        return growthTime;
    }

    /**
     *
     * @param growthTime int to set growth time to (seconds)
     */
    public void setGrowthTime(int growthTime) {
        this.growthTime = growthTime;
    }


}
