package com.javasmithy.data.entity.factory;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.javasmithy.data.adjectives.Adjective;
import com.javasmithy.data.adjectives.AdjectiveLoader;
import com.javasmithy.data.entity.EntityType;
import com.javasmithy.data.entity.TypedEntity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class TypedEntityFactory {
    /**
     *  Adjective loader instance to help with make() method.
     */
    private final static AdjectiveLoader adjectiveLoader = AdjectiveLoader.getInstance();
    /**
     * Instance of random for random generation.
     */
    private static final Random random = new Random();
    /**
     *  Gson instance for file loading.
     */
    private static final Gson gson = new Gson();
    /**
     *  Instance of private EntityLoader class that loads TypedEntities from files.
     */
    private static final EntityLoader entityLoader = new EntityLoader();

    /**
     * Example of Factory Method pattern.
     * @param type - type entity of class you wish to generate
     * @return - return a TypedEntity
     */
    public static TypedEntity make(EntityType type){
        TypedEntity entity = entityLoader.getRandomAilmentEntity(type);
        entity.setAdjective(adjectiveLoader.getRandomAdjectiveByEntityType(type, random));
        return entity;
    }

    /**
     *  A private helper class to load entity objects.
     */
    private static class EntityLoader{

        /**
         *  The entity map, uses entity type for key and lists of TypedEntities loaded from json
         */
        private Map<EntityType, List<TypedEntity>> entityMap;

        /**
         *  Private constructor to load all data from files.
         */
        private EntityLoader(){
            loadAll();
        }

        /**
         *  This method loads all data from files using gson.
         */
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

        /**
         *
         * @param type The entity type you wish to retrieve.
         * @return returns a random TypedEntity associated with the type
         */
        private TypedEntity getRandomAilmentEntity(EntityType type){
            List<TypedEntity> list = entityMap.get(type);
            int size = list.size();
            return list.get(random.nextInt(size));
        }

    }
}



