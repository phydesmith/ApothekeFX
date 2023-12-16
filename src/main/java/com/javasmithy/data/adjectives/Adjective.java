package com.javasmithy.data.adjectives;

import com.javasmithy.data.entity.EntityType;
import com.javasmithy.data.entity.Humour;

public class Adjective {
    String adjective;
    Humour humour;
    EntityType entityType;

    public Adjective(String adjective, Humour humour, EntityType entityType) {
        this.adjective = adjective;
        this.humour = humour;
        this.entityType = entityType;
    }

    public String getAdjective() {
        return adjective;
    }

    public void setAdjective(String adjective) {
        this.adjective = adjective;
    }

    public Humour getHumour() {
        return humour;
    }

    public void setHumour(Humour humour) {
        this.humour = humour;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

}
