package com.javasmithy.data.entity;

import com.javasmithy.data.adjectives.Adjective;

public class TypedEntity extends Entity{
    Humour humour;
    EntityType type;
    Adjective adjective;
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
