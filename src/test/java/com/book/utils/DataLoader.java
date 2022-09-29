package com.book.utils;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance(){
        if(dataLoader == null){
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getBookId(){
        String prop = properties.getProperty("get_Book_Id");
        if(prop != null) return prop;
        else throw new RuntimeException("property get book id is not specified in the data.properties file");
    }

    public String getUpdateBookId(){
        String prop = properties.getProperty("update_Book_Id");
        if(prop != null) return prop;
        else throw new RuntimeException("property updating book id is not specified in the data.properties file");
    }
    public String getDeleteBookId(){
        String prop = properties.getProperty("delete_Book_Id");
        if(prop != null) return prop;
        else throw new RuntimeException("property book id is not specified in the data.properties file");
    }
}
