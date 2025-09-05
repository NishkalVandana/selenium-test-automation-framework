package org.example;

import java.io.InputStream;
import java.util.Properties;

public class Configbuilder {
    private final Properties properties;
    public Configbuilder(){
        properties=new Properties();

        try(InputStream inputStream=getClass().getClassLoader().getResourceAsStream("application.properties")){
            if (inputStream==null){
                //debug statements
                return;
            }
            properties.load(inputStream);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String getproperty(String key){
        return properties.getProperty(key);
    }
}
