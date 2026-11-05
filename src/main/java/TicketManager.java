import java.util.ArrayList;
import java.util.HashMap;

public class TicketManager {


    private HashMap <Integer, Ticket> tickets;
    
    private int nextTicketId = 1;

    public TicketManager(){
        this.tickets = new HashMap<>();
    }

// Method to Add a ticket to the tickets 
    public void addTicket(String title, String description){
        Ticket currentTicket = new Ticket(nextTicketId,title,description);    
        this.tickets.put(currentTicket.getId(),currentTicket);
        this.nextTicketId++;
        System.out.println("Ticket created " +"\nTicket ID: "+ currentTicket.getId());

    }

// Method to view all Tickets in the tickets 
    public void viewAllTickets(){
    
    if(tickets.isEmpty()){
        System.out.println("No tickets found");
    } else {
        System.out.println("\n---------------------");
        System.out.println("Total Tickets: " + tickets.size());
        System.out.println("Open Tickets: " + countTicketsByStatus("Open"));
        System.out.println("In Progress Tickets: " + countTicketsByStatus("In Progress"));
        System.out.println("Closed Tickets: " + countTicketsByStatus("Closed"));

        for(Ticket currentTicket : tickets.values()){
            currentTicket.displayTicket();
        }
    }
    }
    //Method to Find a match for a ticket based on the ID
    public void findTicket(int ticketId){
        Ticket ticket = getTicketById(ticketId);
        if(ticket == null){
            System.out.println("Ticket Id " + ticketId + " not found.");
        }else{
            ticket.displayTicket();
        }
        
    }
    // Method to update Ticket Status
    public void updateTicketStatus(int ticketId, String newStatus){
        Ticket ticket = getTicketById(ticketId);
        if(ticket == null){
            System.out.println("Ticket Id " + ticketId + " not found.");
        }else{
            ticket.setStatus(newStatus);
            System.out.println("Ticket " + ticketId + " status has been changed.");
        }
    }
    // Changed method to use HashMap
    public void deleteTicket(int ticketId){
        
        Ticket currentTicket = getTicketById(ticketId);
        if(currentTicket == null){
            System.out.println("\nTicket Id " + ticketId + " not found.");
        } else{
            tickets.remove(ticketId);
            System.out.println("\nTicket " + ticketId + " has been deleted.");
        }
        
    }
    // Counts tickets in each status category 
    public int countTicketsByStatus(String status){
        int count = 0;
        for(Ticket currentTicket : tickets.values()){
            if(currentTicket.getStatus().equals(status)){
                count ++;
            }
        }
        return count;
    }
   // Get the ticket from Id entry
    public Ticket getTicketById(int ticketId){
        return tickets.get(ticketId);
    }

    
    public void searchTicketByTitle(String keyword){
        ArrayList<Ticket> match = new ArrayList<>();

        for(Ticket currentTicket : tickets.values()){
            String title = currentTicket.getTitle();
            if(title.toLowerCase().contains(keyword.toLowerCase())){
                match.add(currentTicket);
            }
        }
        if(match.isEmpty()){
            System.out.println("\nTickets with the title " + keyword + " not found.");
        }else{
            System.out.println("\nSearch Results for: " + keyword);
            System.out.println("Matching Tickets: " + match.size());
            System.out.print("\n");
            for(int i = 0; i < match.size(); i++){
                Ticket matchingTicket = match.get(i);
                matchingTicket.displayTicket();
            }
        }
        
    }
    public void searchTicketsByStatus(String status){
        //Try to find a ticket by its status
        ArrayList<Ticket>statusMatch = new ArrayList<>();

        for(Ticket currentTicket : tickets.values()){
            if(currentTicket.getStatus().equalsIgnoreCase(status)){
                statusMatch.add(currentTicket);
            }
        }
        if(statusMatch.isEmpty()){
            System.out.println("Tickets with the status " + status + " not found.");
        }else{
            System.out.println("\nSearch Results for Status: " + status);
            System.out.print("Matching Tickets: " + statusMatch.size());
            System.out.println("\n");
            for(int i = 0; i < statusMatch.size(); i++){
                Ticket ticketMatch = statusMatch.get(i);
                ticketMatch.displayTicket();
            }
        }
    }
}

