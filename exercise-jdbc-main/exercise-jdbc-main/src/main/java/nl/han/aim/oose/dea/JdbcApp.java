package nl.han.aim.oose.dea;

import nl.han.aim.oose.dea.datasource.*;
import nl.han.aim.oose.dea.datasource.util.*;

public class JdbcApp {
    public static void main(String[] args) {
//        DatabaseProperties databaseProperties = new DatabaseProperties();
//        System.out.println(databaseProperties.connectionString());
        ItemDao itemDao = new ItemDao();
        itemDao.findAll();
    }
}
