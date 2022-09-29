package com.book.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }


    public String getUser_Name(){
        String prop = properties.getProperty("user_name");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    public String getPassword(){
        String prop = properties.getProperty("user_password");
        if(prop != null) return prop;
        else throw new RuntimeException("property password is not specified in the config.properties file");
    }
    public String getBaseuri(){
        String prop = properties.getProperty("base_uri");
        if(prop != null) return prop;
        else throw new RuntimeException("property base_uri is not specified in the config.properties file");
    }

}
