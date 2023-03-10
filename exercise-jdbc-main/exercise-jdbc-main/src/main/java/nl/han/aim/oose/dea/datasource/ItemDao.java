package nl.han.aim.oose.dea.datasource;

import com.microsoft.sqlserver.jdbc.*;
import nl.han.aim.oose.dea.datasource.util.*;
import nl.han.aim.oose.dea.domain.*;

import java.sql.*;
import java.util.*;

public class ItemDao {
    public List<Item> findAll(){
        DatabaseProperties databaseProperties = new DatabaseProperties();
        try {
            Class.forName(databaseProperties.driverString());
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            ResultSet result = connection.prepareStatement("SELECT 'hello world' as 'Hello'").executeQuery();
                System.out.println("I made a query!:");
                System.out.println(result.getString(0));

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
