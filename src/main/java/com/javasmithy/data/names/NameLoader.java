package com.javasmithy.data.names;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Example of Singleton pattern. Logic behind it is to only load from file once.
 */
public class NameLoader {
    private static NameLoader nameLoaderInstance = null;
    private NameList nameList;
    private Gson gson = new Gson();

    /**
     * constructor to initialize name list
     */
    private NameLoader(){
        initializeNameList();
    }

    /**
     * Loads all names from json file using Gson
     */
    private void initializeNameList() {
        try {
            JsonReader jsonReader = new JsonReader(new FileReader( getClass().getResource("/assets/data/names.json").getFile()));
            nameList = gson.fromJson(jsonReader, NameList.class);
        } catch (FileNotFoundException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  Exposes nameList to retrieve names from.
     * @return name list to get names from
     */
    public NameList nameList(){
        return nameList;
    }

    /**
     * Creates an instance if none exists, otherwise returns singleton.
     * @return Nameloader instance
     */
    public static NameLoader getInstance(){
        if (nameLoaderInstance == null){
            nameLoaderInstance = new NameLoader();
        }
        return nameLoaderInstance;
    }
}


