import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        
        TicketManager manager = new TicketManager();
        Scanner scanner = new Scanner(System.in);
        TicketConsoleUI consoleUI = new TicketConsoleUI(scanner);
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
                    
                        manager.addTicket(ticketTitle,ticketDescription);
                        break;
                // View all tickets
                    case 2:
                        manager.viewAllTickets();
                        break;
                // Search for a ticket using the ticket ID
                    case 3:
                        int findTicketID = consoleUI.readTicketId("Enter the ID of the ticket that you want to find: ");
                        manager.findTicket(findTicketID);
                        break;
                // Update the status of a ticket using the ticket ID
                    case 4:
                        int ticketStatusID = consoleUI.readTicketId("Enter the ID of the Ticket that you want to update the status for: ");
                        String ticketStatusUpdate = consoleUI.readTicketStatus("Enter the status change: ");
                        manager.updateTicketStatus(ticketStatusID, ticketStatusUpdate);
                        break;
                        
                // Delete a ticket using the ticket ID
                    case 5:
                        int deleteTicketID = consoleUI.readTicketId("Enter the ID of the Ticket you want to delete: ");
                        manager.deleteTicket(deleteTicketID);
                        break;

                    case 6:
                        String keyword = consoleUI.readNonEmptyText("Enter the title to search for: " , "title");
                        manager.searchTicketByTitle(keyword);
                        break;

                    case 7:
                        String status = consoleUI.readTicketStatus("Enter the status to search for: ");
                        manager.searchTicketsByStatus(status);
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

