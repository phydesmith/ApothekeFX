package com.javasmithy.data.adjectives;

import com.javasmithy.data.entity.EntityType;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AdjectiveLoaderTest {

    @Test
    void getAdjectiveByEntityType() {
        Random random = new Random(11111);
        AdjectiveLoader adjectiveLoader = AdjectiveLoader.getInstance();
        Adjective adj = adjectiveLoader.getRandomAdjectiveByEntityType(EntityType.CURE, random);
        assertEquals(EntityType.CURE, adj.entityType, "Types should match");
    }
}