package fr.eni.helpme.bll;

import ch.qos.logback.core.net.server.Client;
import fr.eni.helpme.bo.Response;
import fr.eni.helpme.bo.Ticket;
import fr.eni.helpme.dal.ResponseRepository;
import fr.eni.helpme.dal.TicketRepository;
import fr.eni.helpme.dto.ResponseDTO;
import fr.eni.helpme.dto.TicketDTO;
import fr.eni.helpme.exceptions.TicketAlreadyExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ResponseRepository responseRepository;

    @BeforeEach
    void setUp() {
        ticketRepository.deleteAll();
        responseRepository.deleteAll();
    }


    @Test
    @DisplayName("Ajout d'un ticket cas positif")
    public void testAjouterTicketCasPositif() {
        //Arrange
        TicketDTO ticketDto = new TicketDTO("Fanfan", "Message 1");
        ResponseDTO responseDto = new ResponseDTO("Pouet", "Toto");

        //Act
        Ticket ticketActual = ticketService.ajouterTicket(ticketDto);
        Ticket response = ticketService.ajouterResponse(ticketActual.getId(), responseDto);

        //Assert
        Assertions.assertNotNull(ticketActual);
        Ticket ticketActualResultat = ticketRepository.findById(ticketActual.getId()).orElse(null);
        Assertions.assertNotNull(ticketActualResultat);
        Assertions.assertEquals(ticketActual.getAuteur(), ticketActualResultat.getAuteur());
        Assertions.assertEquals(ticketActual.getMessage(), ticketActualResultat.getMessage());
    }
}



