package com.javasmithy.data.names;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class NameLoader {
    private static NameLoader nameLoaderInstance = null;
    private NameList nameList;
    private Gson gson = new Gson();

    private NameLoader(){
        initializeNameList();
    }

    private void initializeNameList() {
        try {
            JsonReader jsonReader = new JsonReader(new FileReader( getClass().getResource("/assets/data/names.json").getFile()));
            nameList = gson.fromJson(jsonReader, NameList.class);
        } catch (FileNotFoundException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public NameList nameList(){
        return nameList;
    }

    public static NameLoader getInstance(){
        if (nameLoaderInstance == null){
            nameLoaderInstance = new NameLoader();
        }
        return nameLoaderInstance;
    }
}


