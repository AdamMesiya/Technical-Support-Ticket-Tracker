import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TicketRepository{
    // method to add a ticket to the database
    public void addTicket(String title, String description, String password)throws SQLException{
        // SQL query to insert a new ticket into the database 
        String sql = "INSERT INTO tickets (title, description) VALUES(?, ?)";

        // try-with-resources statement
        // Automatically closes the resources declared inside the parentheses when the try block finishes
        // Attempts to open a connection between the Java application and the database
        try(Connection connection = DatabaseConnection.getConnection(password); 
            // connection - the open PostgreSQL connection
            // prepares the SQL statement for execution
            PreparedStatement statement = connection.prepareStatement(sql)){

            // Setting the first parameter for the statement to title
            statement.setString(1,title);

            // Setting the second parameter for the statement to description
            statement.setString(2,description);

          
            // Inserting the ticket into the database 
            // rowsAffected stores the number of database rows affected by statement.executeUpdate command
            int rowsAffected = statement.executeUpdate();
        }
    }
    // Retrieves all tickets from PostgreSQL and returns them as an ArrayList
    public ArrayList<Ticket> viewAllTickets(String password)throws SQLException{
        String sql = "SELECT * FROM tickets ORDER BY id";
        ArrayList <Ticket> ticketList = new ArrayList<>();
         // Attempts to open a connection between the Java application and the database
        try(Connection connection = DatabaseConnection.getConnection(password);
            // prepares the SQL statement for execution
            PreparedStatement statement = connection.prepareStatement(sql);
            // Stores the results returned by the SQL query
            ResultSet resultSet = statement.executeQuery()){
            // Looping through the rows returned by the SQL query one row at a time
            while (resultSet.next()){
                // Reads the current row's ticket id into a java variable
                int id = resultSet.getInt("id");
                // Reads the current row's ticket title into a java variable
                String title = resultSet.getString("title");
                // Reads the current rows's ticket description into a java variable
                String description = resultSet.getString("description");
                // Reads the current row's ticket status into a java variable
                String status = resultSet.getString("status");
                
                Ticket ticket = new Ticket(id,title,description,status);
                ticketList.add(ticket);

            }

            return ticketList;
           
        }
    }
    // Method to find a ticket by its ID
    public Ticket findTicketById(int id,String password) throws SQLException{
        String sql = "SELECT * FROM tickets WHERE id = ?";
         // Attempts to open a connection between the Java application and the database
        try(Connection connection = DatabaseConnection.getConnection(password);
            // prepares the SQL statement for execution
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1,id);
            
            try(ResultSet resultSet = statement.executeQuery()){
                // If a matching ticket exists, retrieve its information
                if(resultSet.next()){
                    int searchID = resultSet.getInt("id"); 
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String status = resultSet.getString("status");   
                    Ticket ticket = new Ticket(searchID,title,description,status);
                    return ticket;
                }else{
                    // Return null if no matching tickets exist
                    return null;
                }
            }

            
            

        }
    }
    // Method to update a ticket's status in the database using its ID
    public boolean updateTicketStatus(int id, String newStatus, String password) throws SQLException{
        String sql = "UPDATE tickets SET status = ? WHERE id = ?";
         // Attempts to open a connection between the Java application and the database
        try(Connection connection = DatabaseConnection.getConnection(password);
            // prepares the SQL statement for execution
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1,newStatus);
                statement.setInt(2,id);

                int rowsAffected = statement.executeUpdate();
                // Return true if ticket status was updated, otherwise false
                if(rowsAffected > 0){
                   return true;
                } else{
                    return false;
                }
            }
    }
    // Method to delete a ticket from the database using its ID
    public boolean deleteTicket(int id, String password) throws SQLException{
        String sql = "DELETE FROM tickets WHERE id = ?";
         // Attempts to open a connection between the Java application and the database
        try(Connection connection = DatabaseConnection.getConnection(password);
            // prepares the SQL statement for execution
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1,id);
                int rowsAffected = statement.executeUpdate();
                // Returns true if a ticket was deleted, otherwise false
                if(rowsAffected > 0){
                   return true;
                } else{
                    return false;
                }
            }
    }
}