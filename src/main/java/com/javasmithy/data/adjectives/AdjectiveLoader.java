package com.javasmithy.data.adjectives;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.javasmithy.data.entity.EntityType;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class AdjectiveLoader {
    private static AdjectiveLoader adjectiveLoaderInstance = null;
    private Map<EntityType, List<Adjective>> adjectives;
    private Gson gson = new Gson();

    private AdjectiveLoader(){
        initializeAdjectiveMap();
    }

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

    public Adjective getRandomAdjectiveByEntityType(EntityType entityType, Random random){
        List<Adjective> adjectiveList = adjectives.get(entityType);
        int index = random.nextInt(adjectiveList.size());
        return adjectiveList.get(index);
    }

    public static AdjectiveLoader getInstance(){
        if (adjectiveLoaderInstance == null){
            adjectiveLoaderInstance = new AdjectiveLoader();
        }
        return adjectiveLoaderInstance;
    }
}


