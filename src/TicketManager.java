import java.util.ArrayList;

public class TicketManager {
    
    private ArrayList <Ticket> tickets;
    private int nextTicketId = 1;

    public TicketManager(){
        this.tickets = new ArrayList<>();
    }
// Method to Add a ticket to the tickets arrayList
    public void addTicket(String title, String description){
        Ticket currentTicket = new Ticket(nextTicketId,title,description);    
        this.tickets.add(currentTicket);
        this.nextTicketId++;
        System.out.println("Ticket created " +"\nTicket ID: "+ currentTicket.getId());

    }

// Method to view all Tickets in the tickets arrayList
    public void viewAllTickets(){
    
    if(tickets.isEmpty()){
        System.out.println("No tickets found");
    } else {
        System.out.println("\n---------------------");
        System.out.println("Total Tickets: " + tickets.size());
        System.out.println("Open Tickets: " + countTicketsByStatus("Open"));
        System.out.println("In Progress Tickets: " + countTicketsByStatus("In Progress"));
        System.out.println("Closed Tickets: " + countTicketsByStatus("Closed"));
        for(int i = 0; i <= tickets.size() - 1; i++){
            Ticket currentTicket = tickets.get(i);
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

    public void deleteTicket(int ticketId){
        for(int i = 0; i < tickets.size(); i++){
            Ticket currentTicket = tickets.get(i);
            if(currentTicket.getId() == ticketId){
                tickets.remove(currentTicket);
                System.out.println("Ticket " + ticketId + " has been deleted");
                return;
            }
        }
        System.out.println("Ticket ID " + ticketId + "  not found.");
    }
    // Counts tickets in each status category 
    public int countTicketsByStatus(String status){
        int count = 0;
        for(int i = 0; i < tickets.size(); i++){
            if(tickets.get(i).getStatus().equals(status)){
                count ++;
            }
        }
        return count;
    }

    public Ticket getTicketById(int ticketId){
        for(int i = 0; i < tickets.size(); i++){
            Ticket currentTicket = tickets.get(i);
            if(currentTicket.getId() == ticketId){
                return currentTicket;
            }
            
        }
        return null;
    }
}
