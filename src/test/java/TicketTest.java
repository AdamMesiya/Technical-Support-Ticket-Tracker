import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketTest{
    @Test
    void newTicketShouldBeOpen(){
        Ticket ticket = new Ticket(1, "Wifi Issue", "Wifi is not connecting");
        assertEquals("Open", ticket.getStatus());
    }

    @Test
    void shouldUpdateTicketStatus(){
        Ticket ticket = new Ticket(2,"Printer Issue", "Printer is not workng");
        ticket.setStatus("In Progress");
        assertEquals("In Progress", ticket.getStatus());
    }
}