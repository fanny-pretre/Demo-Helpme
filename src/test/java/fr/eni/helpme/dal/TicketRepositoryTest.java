package fr.eni.helpme.dal;

import fr.eni.helpme.bo.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TicketRepositoryTest {
    @Autowired
    private TicketRepository ticketRepository;

    @BeforeEach
    void setUp() {
        ticketRepository.deleteAll();
    }

    @Test
    @DisplayName("test creation ticket ok")
    void testCreationTicket() {

        // arrange
        Ticket ticket = new Ticket("bob", "souci 1");

        // act
        ticketRepository.save(ticket);

        // assert
        Assertions.assertNotNull(ticket.getId());
        Ticket ticketBD = ticketRepository.findById(ticket.getId()).orElse(null);
        Assertions.assertNotNull(ticketBD);
        Assertions.assertEquals(ticket, ticketBD);
    }


}
