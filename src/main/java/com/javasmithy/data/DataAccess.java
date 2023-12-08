package com.javasmithy.data;

public class DataAccess {
    private static DataAccess dataAccessInstance = null;

    private DataAccess(){

    }

    public static DataAccess getInstance(){
        if (dataAccessInstance == null){
            dataAccessInstance = new DataAccess();
        }
        return dataAccessInstance;
    }


}
