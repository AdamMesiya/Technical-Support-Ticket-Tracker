import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        
        TicketManager manager = new TicketManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while(running){
            // Choices for Technical Support Ticket Tracker
            displayMenu();
                int choice = readMenuChoice(scanner);
                System.out.println("Choice: " + choice);
                switch (choice){
                // Create a Ticket
                    case 1:
                        
                        String ticketTitle = readNonEmptyText(scanner, "Enter Ticket Title: ", "title");
                        
                        String ticketDescription = readNonEmptyText(scanner, "Enter Ticket Description: ", "description");
                    
                        manager.addTicket(ticketTitle,ticketDescription);
                        break;
                // View all tickets
                    case 2:
                        manager.viewAllTickets();
                        break;
                // Search for a ticket using the ticket ID
                    case 3:
                        int findTicketID = readTicketId(scanner, "Enter the ID of the ticket that you want to find: ");
                        manager.findTicket(findTicketID);
                        break;
                // Update the status of a ticket using the ticket ID
                    case 4:
                        int ticketStatusID = readTicketId(scanner, "Enter the ID of the Ticket that you want to update the status for: ");
                        String ticketStatusUpdate = readTicketStatus(scanner);
                        manager.updateTicketStatus(ticketStatusID, ticketStatusUpdate);
                        break;
                        
                // Delete a ticket using the ticket ID
                    case 5:
                        int deleteTicketID = readTicketId(scanner, "Enter the ID of the Ticket you want to delete: ");
                        manager.deleteTicket(deleteTicketID);
                        break;
                    case 6:

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
    // Helper method to display menu choices
    public static void displayMenu(){
        System.out.println("\nTech Support Ticket Tracker");
        System.out.println("----------------------------");
        System.out.println("1. Create Ticket");
        System.out.println("2. View All Tickets");
        System.out.println("3. Find Ticket");
        System.out.println("4. Update Ticket Status");
        System.out.println("5. Delete Ticket");
        System.out.println("6. Exit");
        System.out.print("\nEnter choice: ");
        
    }

    // Helper function to read and validate a menu choice (1-6)
    public static int readMenuChoice(Scanner scanner){
        int choice;
        while(true){
            try{
                choice = Integer.parseInt(scanner.nextLine());
                if(choice <= 6 && choice >= 1){
                    return choice;
                }
                System.out.println("Choices are between 1 and 6.");
            }catch(NumberFormatException e){
                System.out.println("Invalid Entry. Enter a number.");
            }
            System.out.print("Enter choice: ");
        }
    }
    // Helper method to read and validate a positive ID
    public static int readTicketId(Scanner scanner, String prompt){
        int ticketID;
        while(true){
            System.out.print(prompt);
            try{
                ticketID = Integer.parseInt(scanner.nextLine());
                if(ticketID > 0){
                    break;
                }
                System.out.println("Ticket ID entered is less than or equal to 0");
            }catch(NumberFormatException e){
                System.out.println("Invalid ticket ID. Enter a number");
            }
        }       
        return ticketID;
    }
    
    public static String readNonEmptyText(Scanner scanner, String prompt, String type){
        String checkString;
        while(true){
            System.out.print(prompt);
            checkString = scanner.nextLine();
            if(!checkString.trim().isEmpty()){
                return checkString;
            }
            System.out.println("No " + type + " entered for ticket");
        }
    }
    


    public static String readTicketStatus(Scanner scanner){
        String ticketStatusUpdate;
        while(true){
            System.out.print("Enter the status change: ");
            ticketStatusUpdate = scanner.nextLine();
            if(ticketStatusUpdate.equalsIgnoreCase("Open")){
                return "Open";
            }
            if(ticketStatusUpdate.equalsIgnoreCase("In Progress")){
                return "In Progress";

            }
            if(ticketStatusUpdate.equalsIgnoreCase("Closed")){
                return "Closed";

            }

            System.out.println("Invalid Status. Use Open , In Progress, or Closed for this program");
        }
        
    }


}

