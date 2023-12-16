package com.javasmithy.data.entity.factory;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.javasmithy.data.adjectives.Adjective;
import com.javasmithy.data.adjectives.AdjectiveLoader;
import com.javasmithy.data.entity.EntityType;
import com.javasmithy.data.entity.TypedEntity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.*;

public class TypedEntityFactory {
    private final static AdjectiveLoader adjectiveLoader = AdjectiveLoader.getInstance();
    private final String GENERIC_SPRITE = "/assets/images/generic.png";
    private static final Random random = new Random();
    private static final Gson gson = new Gson();
    private static final EntityLoader entityLoader = new EntityLoader();

    public static TypedEntity make(EntityType type){
        TypedEntity entity = entityLoader.getRandomAilmentEntity(type);
        entity.setAdjective(adjectiveLoader.getRandomAdjectiveByEntityType(type, random));
        return entity;
    }

    private static class EntityLoader{

        private Map<EntityType, List<TypedEntity>> entityMap;

        private EntityLoader(){
            loadAll();
        }

        private void loadAll() {
            entityMap = new HashMap<>();
            try {
                StringBuilder sb = new StringBuilder();
                for (EntityType e: EntityType.values()) {
                    sb.setLength(0);
                    String type = e.toString().toLowerCase();
                    sb.append("/assets/data/").append(type).append("/").append(type).append(".json");
                    JsonReader jsonReader = new JsonReader(new FileReader( getClass().getResource(sb.toString()).getFile()));
                    TypedEntity[] entityArray = gson.fromJson(jsonReader, Adjective[].class);
                    entityMap.put(e, Arrays.asList(entityArray));
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        private TypedEntity getRandomAilmentEntity(EntityType type){
            List<TypedEntity> list = entityMap.get(type);
            int size = list.size();
            return list.get(random.nextInt(size));
        }

    }
}



