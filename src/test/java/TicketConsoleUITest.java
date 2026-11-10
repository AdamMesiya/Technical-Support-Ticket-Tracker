import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class TicketConsoleUITest{
    
    // Test: 12
    @Test
    void testReadTicketIDRejectsInvalidInput(){
        Scanner scanner = new Scanner("abc\n-2\n0\n5\n");

        TicketConsoleUI ticketUI = new TicketConsoleUI(scanner);

        int id = ticketUI.readTicketId("Enter ticket ID: ");

        assertEquals(5,id);

    }

    // Test: 13
    @Test 
    void testReadTicketStatus(){
        Scanner scanner = new Scanner("invalid\nclosed\n");
        TicketConsoleUI ticketUI = new TicketConsoleUI(scanner);

        String ticketStatus = ticketUI.readTicketStatus("Enter ticket Status: ");

        assertEquals("Closed",ticketStatus);

    }
}
