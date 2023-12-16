package com.javasmithy.data.adjectives;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.javasmithy.data.entity.EntityType;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Example of Singleton Pattern.
 */
public class AdjectiveLoader {

    /**
     *The instance of the adjective loader, default is null, set in via getInstance() method.
     */
    private static AdjectiveLoader adjectiveLoaderInstance = null;

    /**
     * Map of adjectives, keys are entity types and each value is a list of adjectives.
     */
    private Map<EntityType, List<Adjective>> adjectives;

    /**
     * Gson for use loading json.
     */
    private Gson gson = new Gson();

    /**
     * Singular private constructor called from getInstance(). Initializes the adjective map field.
     */
    private AdjectiveLoader(){
        initializeAdjectiveMap();
    }

    /**
     * Loads data from json files into adjective map field. Done using gson.
     */
    private void initializeAdjectiveMap() {
        adjectives = new HashMap<>();
        try {
            StringBuilder sb = new StringBuilder();
            for (EntityType e: EntityType.values()) {
                sb.setLength(0);
                String type = e.toString().toLowerCase();
                sb.append("/assets/data/").append(type).append("/").append(type).append("-adjectives.json");
                JsonReader jsonReader = new JsonReader(new FileReader( getClass().getResource(sb.toString()).getFile()));
                Adjective[] adjArr = gson.fromJson(jsonReader, Adjective[].class);
                adjectives.put(e, Arrays.asList(adjArr));
            }
        } catch (FileNotFoundException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a random adjective based on given entity type.
     * @param entityType desired entity type adjective should be associated with
     * @param random an instand of the Random class to generate random index
     * @return Adjective an adjective
     */
    public Adjective getRandomAdjectiveByEntityType(EntityType entityType, Random random){
        List<Adjective> adjectiveList = adjectives.get(entityType);
        int index = random.nextInt(adjectiveList.size());
        return adjectiveList.get(index);
    }

    /**
     * Returns the instance of adjective loader or makes one if there is none.
     * @return AdjectiveLoader singleton instance
     */
    public static AdjectiveLoader getInstance(){
        if (adjectiveLoaderInstance == null){
            adjectiveLoaderInstance = new AdjectiveLoader();
        }
        return adjectiveLoaderInstance;
    }
}


