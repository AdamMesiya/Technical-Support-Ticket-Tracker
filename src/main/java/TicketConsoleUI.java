import java.util.Scanner;


public class TicketConsoleUI{
    private Scanner scanner;
    
    public TicketConsoleUI(Scanner scanner){
        this.scanner = scanner;
    }

    public void displayMenu(){
            System.out.println("\nTech Support Ticket Tracker");
            System.out.println("----------------------------");
            System.out.println("1. Create Ticket");
            System.out.println("2. View All Tickets");
            System.out.println("3. Find Ticket");
            System.out.println("4. Update Ticket Status");
            System.out.println("5. Delete Ticket");
            System.out.println("6. Search Tickets By Title");
            System.out.println("7. Search Tickets By Status");
            System.out.println("8. Exit");
            System.out.print("\nEnter choice: ");
            
        }

    public int readMenuChoice(){
            int choice;
            while(true){
                try{
                    choice = Integer.parseInt(scanner.nextLine());
                    if(choice <= 8 && choice >= 1){
                        return choice;
                    }
                    System.out.println("Choices are between 1 and 8.");
                }catch(NumberFormatException e){
                    System.out.println("Invalid Entry. Enter a number.");
                }
                System.out.print("Enter choice: ");
            }
        }   
        

    // Helper method to read and validate a positive ID
    public int readTicketId(String prompt){
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

    public String readNonEmptyText(String prompt, String type){
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
    

    public String readTicketStatus(String prompt){
        String ticketStatusUpdate;
        while(true){
            System.out.print(prompt);
            ticketStatusUpdate = scanner.nextLine().trim();
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