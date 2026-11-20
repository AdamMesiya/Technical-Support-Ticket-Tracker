import java.sql.SQLException;
import java.util.ArrayList;

public class TicketMenuHandler{

    private TicketConsoleUI consoleUI;
    private TicketRepository repository;
    private String password;

    // Constructor
    public TicketMenuHandler(TicketConsoleUI consoleUI, TicketRepository repository, String password){
        this.consoleUI = consoleUI;
        this.repository = repository;
        this.password = password;
    }

    // Adds tickets to the PostgreSQL database
    public void addTicket(){
        String ticketTitle = consoleUI.readNonEmptyText("Enter Ticket Title: ", "title");
        String ticketDescription = consoleUI.readNonEmptyText("Enter Ticket Description: ", "description"); 
        try{
            repository.addTicket(ticketTitle,ticketDescription,password);
            System.out.println("\nTicket created");
        }catch(SQLException e){
            System.out.println("Database error: " + e.getMessage());
        }
    }


    // Views all the tickets in the PostgreSQL database
    public void viewAllTickets(){
        try {
            
            ArrayList<Ticket> tickets = repository.viewAllTickets(password);
            int openTickets = 0;
            int inProgressTickets = 0;
            int closedTickets = 0;

            for(Ticket currentTicket : tickets){
                if(currentTicket.getStatus().equals("Open")){
                    openTickets++;
                }else if(currentTicket.getStatus().equals("In Progress")){
                    inProgressTickets++;
                }else if(currentTicket.getStatus().equals("Closed")){
                    closedTickets++;
                }
            }
            System.out.println("\nTicket Information");
            System.out.println("---------------------");
            System.out.println("Total Tickets: " + tickets.size());
            System.out.println("---------------------");
            System.out.println("Open Tickets: " + openTickets);
            System.out.println("In Progress Tickets: " + inProgressTickets);
            System.out.println("Closed Tickets: " + closedTickets);
            System.out.println("---------------------");
            consoleUI.displayTickets(tickets);
        }catch(SQLException e){
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Finds a ticket by its ID from the PostgreSQL database
    public void findTicketById(){
        int findTicketID = consoleUI.readTicketId("Enter the ID of the ticket that you want to find: ");
        try{
            Ticket ticket = repository.findTicketById(findTicketID, password);
            if(ticket == null){
            System.out.println("\nTicket with the ID " + findTicketID + " not found"); 
            }else{
                ticket.displayTicket();
            }
        }catch(SQLException e){
            System.out.println("Database error: " + e.getMessage());
        }

    }
    
    // Updates the ticket status in the PostgreSQL database
    public void updateTicketStatus(){
        int ticketStatusID = consoleUI.readTicketId("Enter the ID of the Ticket that you want to update the status for: ");
        String ticketStatusUpdate = consoleUI.readTicketStatus("Enter the status change: ");
        try{
            boolean updatedTicket = repository.updateTicketStatus(ticketStatusID,ticketStatusUpdate,password);
            if(updatedTicket){
                System.out.println("\nTicket status updated");
            }else{
                System.out.println("Ticket ID " + ticketStatusID + " not found");
            }
        }catch(SQLException e){
            System.out.println("Database error: " + e.getMessage());
        }

    }

    // Deletes a ticket from the PostgreSql database
    public void deleteTicket(){
        int deleteTicketID = consoleUI.readTicketId("Enter the ID of the Ticket you want to delete: ");
        try{
            boolean deletedTicket = repository.deleteTicket(deleteTicketID, password);
            if(deletedTicket){
                System.out.println("\nTicket ID " + deleteTicketID + " deleted");
            }else{
                System.out.println("\nTicket ID " + deleteTicketID + " not found"); 
            }
        }catch (SQLException e){
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Finds a ticket by its title from the PostgreSql database
    public void searchByTitle(){
        String keyword = consoleUI.readNonEmptyText("Enter the title to search for: " , "title");
        try{
            ArrayList<Ticket> ticketsFoundList = repository.searchTicketsByTitle(keyword, password);
            if(ticketsFoundList.isEmpty()){
                System.out.println("\nNo tickets found with the keyword: " + keyword);
            }else{
                consoleUI.displayTickets(ticketsFoundList);
            }
                            
        }catch(SQLException e){
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Finds a ticket by its status from the PostgreSQL database
    public void searchByStatus(){
        String searchStatus = consoleUI.readTicketStatus("Enter the status to search for: ");
        try{
            ArrayList<Ticket> statusTicketsFoundList = repository.searchTicketsByStatus(searchStatus, password);
            if(statusTicketsFoundList.isEmpty()){
                System.out.println("\nNo tickets found with the status: " + searchStatus);
            }else{
                System.out.println("\nTicket Information");
                System.out.println("---------------------");
                System.out.println(searchStatus + " Tickets: " + statusTicketsFoundList.size());
                consoleUI.displayTickets(statusTicketsFoundList);
            }
        }catch(SQLException e){
            System.out.println("Database error: " + e.getMessage());
        }
    }


    

}