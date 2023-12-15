package com.javasmithy.data;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.List;

public class DataAccess {
    private static DataAccess dataAccessInstance = null;
    private NameList nameList;
    private Gson gson = new Gson();

    private DataAccess(){
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

    public static DataAccess getInstance(){
        if (dataAccessInstance == null){
            dataAccessInstance = new DataAccess();
        }
        return dataAccessInstance;
    }
}


