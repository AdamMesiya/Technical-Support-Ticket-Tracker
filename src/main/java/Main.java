import java.util.Scanner;
//import java.util.ArrayList;
//import java.sql.SQLException;
public class Main {
    public static void main(String[] args){
        
        //TicketManager manager = new TicketManager();
        Scanner scanner = new Scanner(System.in);
        TicketConsoleUI consoleUI = new TicketConsoleUI(scanner);
        TicketRepository repository = new TicketRepository();
        String password = System.getenv("DB_PASSWORD");
        TicketMenuHandler handler = new TicketMenuHandler(consoleUI,repository,password);
        boolean running = true;
        while(running){
            // Choices for Technical Support Ticket Tracker
            consoleUI.displayMenu();
                int choice = consoleUI.readMenuChoice();
                System.out.println("Choice: " + choice);
                switch (choice){
                // Create a Ticket
                    case 1:
                        
                        handler.addTicket();
                        //manager.addTicket(ticketTitle,ticketDescription);
                        break;
                // View all tickets
                    case 2:
                        handler.viewAllTickets();
                        //manager.viewAllTickets();
                        break;
                // Search for a ticket using the ticket ID
                    case 3:
                        handler.findTicketById();
                        //manager.findTicket(findTicketID);
                        break;
                // Update the status of a ticket using the ticket ID
                    case 4:
                        handler.updateTicketStatus();
                        //manager.updateTicketStatus(ticketStatusID, ticketStatusUpdate);
                        break;
                        
                // Delete a ticket using the ticket ID
                    case 5:
                        handler.deleteTicket();
                        //manager.deleteTicket(deleteTicketID);
                        break;

                    case 6:
                        handler.searchByTitle();
                        //manager.searchTicketByTitle(keyword);
                        break;

                    case 7:

                        handler.searchByStatus();
                        //manager.searchTicketsByStatus(searchStatus);
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

