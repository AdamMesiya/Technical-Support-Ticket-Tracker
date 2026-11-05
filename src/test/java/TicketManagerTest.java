
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;

public class TicketManagerTest {
    // Test: 03
    // Manager assigning automatic ID in sequential order
    @Test
    void shouldAssignAutomaticTicketIds() {

        TicketManager manager = new TicketManager();

        manager.addTicket("Wifi Issue", "Wifi not connecting");
        manager.addTicket("Printer Issue", "Printer not working");


        Ticket ticket1 = manager.getTicketById(1);
        Ticket ticket2 = manager.getTicketById(2);
        assertEquals(1, ticket1.getId());
        assertEquals(2, ticket2.getId());
    }
    // Test: 04
    // Nonexistent ticket should return null
    @Test 
    void shouldReturnNullForNonExistentTicket(){
        TicketManager manager = new TicketManager();
        manager.addTicket("Wifi Issue", "Wifi is not connecting");

        Ticket result = manager.getTicketById(50);

        assertNull(result);
    }
    // Test: 05
    // Ticket deletion
    @Test 
    void shouldDeleteTicket(){
        TicketManager manager = new TicketManager();
        manager.addTicket("Wifi Issue", "Wifi is not connecting");

        manager.deleteTicket(1);

        Ticket result =  manager.getTicketById(1);

        assertNull(result);


    }

    // Test: 06
    // One deleted ticket does not delete others
    @Test 
    void shouldNotDeleteOtherTickets(){
        TicketManager manager = new TicketManager();

        manager.addTicket("Wifi Issue", "Wifi is not connecting");
        manager.addTicket("Printer Issue", "Printer is not connecting");

        manager.deleteTicket(1);
        Ticket result = manager.getTicketById(2);
        assertNotNull(result);
        assertEquals("Printer Issue", result.getTitle());

    }
    // Test: 07
    // Ticket status update
    @Test 
    void shouldUpdateTicketStatusThroughManager(){
        TicketManager manager = new TicketManager();
        manager.addTicket("Wifi Issue", "Wifi is not connecting");
        manager.updateTicketStatus(1, "In Progress");

        Ticket result = manager.getTicketById(1);

        assertEquals("In Progress", result.getStatus());


    }

    // Test: 08
    // Counting tickets by status
    @Test 
    void shouldCountTicketsByStatus(){
        TicketManager manager = new TicketManager();
        manager.addTicket("Wifi Issue", "Wifi is not connecting");
        manager.addTicket("Printer Issue", "Printer is not working");
        manager.addTicket("Laptop Issue", "Laptop is not starting");
        manager.updateTicketStatus(2,"In Progress");
        manager.updateTicketStatus(3, "Closed");

        assertEquals(1,manager.countTicketsByStatus("Open"));
        assertEquals(1,manager.countTicketsByStatus("In Progress"));
        assertEquals(1,manager.countTicketsByStatus("Closed"));
        

    }
    // Test: 09
    // Edge case: Counting tickets when there are none
    @Test
    void shouldReturnZeroWhenNoTicketsExist(){
        TicketManager manager = new TicketManager();

        assertEquals(0,manager.countTicketsByStatus("Open"));
        assertEquals(0,manager.countTicketsByStatus("In Progress"));
        assertEquals(0,manager.countTicketsByStatus("Closed"));

        
    }
    // Test: 10
    // Trying to delete a ticket that does not exist and than checking that it does not delete a ticket that does exist
    @Test 
    void shouldKeepExistingTicketWhenDeletingNonExistentId(){
        TicketManager manager = new TicketManager();

        manager.addTicket("Wifi Issue", "Wifi is not connecting");
        manager.deleteTicket(50);

        Ticket result = manager.getTicketById(1);

        assertNotNull(result);
        assertEquals("Wifi Issue",result.getTitle());

    }

    // Test: 11
    @Test
    void testGetTicketsSortedById(){
        TicketManager manager = new TicketManager();
        manager.addTicket("Wifi Issue","Wifi not connecting");
        manager.addTicket("Printer Issue", "Printer not connecting");
        manager.addTicket("Computer Issue","Computer freezing");

        manager.deleteTicket(2);

        manager.addTicket("Printer Issue","Printer is not printing");
        ArrayList <Ticket> sortedTickets = manager.getTicketsSortedById();

        // Checking if the arraySize is correct
        assertEquals(3,sortedTickets.size());
        // Checking if the tickets are in the sorted order based of ID
        assertEquals(1,sortedTickets.get(0).getId());
        assertEquals(3,sortedTickets.get(1).getId());
        assertEquals(4,sortedTickets.get(2).getId());

    }
}
