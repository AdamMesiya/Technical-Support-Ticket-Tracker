import java.util.Scanner;
import java.util.ArrayList;
import java.sql.SQLException;
public class Main {
    public static void main(String[] args){
        
        TicketManager manager = new TicketManager();
        Scanner scanner = new Scanner(System.in);
        TicketConsoleUI consoleUI = new TicketConsoleUI(scanner);
        TicketRepository repository = new TicketRepository();
        String password = System.getenv("DB_PASSWORD");
        boolean running = true;
        while(running){
            // Choices for Technical Support Ticket Tracker
            consoleUI.displayMenu();
                int choice = consoleUI.readMenuChoice();
                System.out.println("Choice: " + choice);
                switch (choice){
                // Create a Ticket
                    case 1:
                        
                        String ticketTitle = consoleUI.readNonEmptyText("Enter Ticket Title: ", "title");
                        
                        String ticketDescription = consoleUI.readNonEmptyText("Enter Ticket Description: ", "description"); 
                        try{
                            repository.addTicket(ticketTitle,ticketDescription,password);
                            System.out.println("Ticket created");
                        }catch(SQLException e){
                            System.out.println("Database error" + e.getMessage());
                        }
                        
                        //manager.addTicket(ticketTitle,ticketDescription);
                        break;
                // View all tickets
                    case 2:
                        //manager.viewAllTickets();
                        try {
                            ArrayList<Ticket> tickets = repository.viewAllTickets(password);
                            consoleUI.displayTickets(tickets);
                        } catch(SQLException e){
                            System.out.println("Database error: " + e.getMessage());
                        }
                        break;
                // Search for a ticket using the ticket ID
                    case 3:
                        int findTicketID = consoleUI.readTicketId("Enter the ID of the ticket that you want to find: ");
                        try{
                            Ticket ticket = repository.findTicketById(findTicketID, password);
                            if(ticket == null){
                                System.out.println("Ticket ID " + findTicketID + " not found"); 
                            } else{
                                ticket.displayTicket();
                            }
                        }catch(SQLException e){
                            System.out.println("Database error: " + e.getMessage());
                        }

                        //manager.findTicket(findTicketID);
                        break;
                // Update the status of a ticket using the ticket ID
                    case 4:
                        int ticketStatusID = consoleUI.readTicketId("Enter the ID of the Ticket that you want to update the status for: ");
                        String ticketStatusUpdate = consoleUI.readTicketStatus("Enter the status change: ");
                        try{
                            boolean updatedTicket = repository.updateTicketStatus(ticketStatusID,ticketStatusUpdate,password);
                            if(updatedTicket){
                               System.out.println("Ticket status updated");
                            }else{
                                System.out.println("Ticket ID " + ticketStatusID + " not found");
                            }
                        }catch(SQLException e){
                            System.out.println("Database error:" + e.getMessage());
                        }
                        //manager.updateTicketStatus(ticketStatusID, ticketStatusUpdate);
                        break;
                        
                // Delete a ticket using the ticket ID
                    case 5:
                        int deleteTicketID = consoleUI.readTicketId("Enter the ID of the Ticket you want to delete: ");
                        try{
                            boolean deletedTicket = repository.deleteTicket(deleteTicketID, password);
                            if(deletedTicket){
                                System.out.println("Ticket ID " + deleteTicketID + " deleted");
                            } else{
                                System.out.println("Ticket ID " + deleteTicketID + " not found"); 
                            }
                        } catch (SQLException e){
                            System.out.println("Database error: " + e.getMessage());
                        }
                        //manager.deleteTicket(deleteTicketID);
                        break;

                    case 6:
                        String keyword = consoleUI.readNonEmptyText("Enter the title to search for: " , "title");
                        try{
                            ArrayList<Ticket> ticketsFoundList = repository.searchTicketsByTitle(keyword, password);
                            if(ticketsFoundList.isEmpty()){
                                System.out.println("No tickets found with the keyword: " + keyword);
                            }else{
                                consoleUI.displayTickets(ticketsFoundList);
                            }
                            
                        }catch(SQLException e){
                            System.out.println("Database error: " + e.getMessage());
                        }
                        //manager.searchTicketByTitle(keyword);
                        break;

                    case 7:
                        String searchStatus = consoleUI.readTicketStatus("Enter the status to search for: ");
                        try{
                            ArrayList<Ticket> statusTicketsFoundList = repository.searchTicketsByStatus(searchStatus, password);
                            if(statusTicketsFoundList.isEmpty()){
                                System.out.println("No tickets found with the status: " + searchStatus);
                            } else{
                                consoleUI.displayTickets(statusTicketsFoundList);
                            }
                        } catch(SQLException e){
                            System.out.println("Database error: " + e.getMessage());
                        }
                        //manager.searchTicketsByStatus(status);
                        break;
                        
                    case 8:
                        // Exit the program
                        running = false;
                        System.out.println("You have exited the program."); 
                        break;

                    default:
                        System.out.println("Invalid choice. Please Try Again.");
                        break;
                }
        }
        scanner.close();
    }   

}

