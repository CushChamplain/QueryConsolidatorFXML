package queryconsolidatorfxml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Course: SDEV 450 ~ Enterprise Java Programming
 * @Author Name: Cush
 * @Assignment Name: queryconsolidatorfxml
 * @Date: Nov 1, 2017
 * @Subclass DatabaseConnector Description:
 */
//Imports
//Begin Subclass DatabaseConnector
public class DatabaseConnector {

    //Create the url for the connection using windows authentication
    static String theURL = "jdbc:sqlserver://localhost;instanceName=" + QueryConsolidatorFXML.getServer()
            + ";integratedSecurity=true;databaseName=QueryConsolidate";
    static Connection con = null;

    public DatabaseConnector() {

    }

   

} //End Subclass DatabaseConnector
