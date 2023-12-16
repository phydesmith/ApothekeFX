package com.javasmithy.data.entity.factory;

import com.javasmithy.data.adjectives.Adjective;
import com.javasmithy.data.entity.EntityType;
import com.javasmithy.data.entity.Humour;
import com.javasmithy.data.entity.TypedEntity;

public class MaterialEntity extends TypedEntity {
    public MaterialEntity(String spritePath, String description, Humour humour, EntityType type, Adjective adjective) {
        super(spritePath, description, humour, type, adjective);
    }
}
