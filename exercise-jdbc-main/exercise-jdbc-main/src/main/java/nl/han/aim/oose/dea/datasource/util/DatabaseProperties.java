package nl.han.aim.oose.dea.datasource.util;


import javax.xml.crypto.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class DatabaseProperties {
    private Logger logger = Logger.getLogger(DatabaseProperties.class.getName());
    private Properties properties;

    public DatabaseProperties() {
        properties = new Properties();
        try {
            properties.load(DatabaseProperties.class.getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't access property file database.properties");
        }
    }

    public String connectionString(){
        return properties.getProperty("connectionString");
    }

    public String driverString(){
        return  properties.getProperty("driver");
    }

}
