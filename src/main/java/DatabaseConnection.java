//Establishes a connection between the Java application and the PostgreSQL database

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{
    // JDBC - Java Database Connectivity
    /*
    - JDBC URL specifying the PostgreSQL database location 
    - jdbc: Java Database Connectivity
    - postgresql: Specifies PostgreSQL as the database type
    - localhost: PostgreSQL is running on this computer
    - 5432: Default PostgreSQL port
    - techsupporttracker: Database name
     */ 
    private static final String URL = "jdbc:postgresql://localhost:5432/techsupporttracker";

    // Stores the postgreSQL username used to establish the connection
    private static final String USER = "postgres";

    // Method to open a connection and return a connection to the PostgreSQL database
    public static Connection getConnection(String password) throws SQLException{
        // Opens the database connection
        /*
        DriverManager.getConnection(URL,USER,password)
        DriverManager.getConnection() - opens the connection
        asks JDBC to establish a connection using three arguments
        URL - where the database is
        USER - the PostgreSQL account to use
        password - the password for the account
         */
        Connection connection = DriverManager.getConnection(URL,USER,password);
        
        // return the open connection
        return connection; 
    }
}