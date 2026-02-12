package fr.eni.helpme.bll;

import fr.eni.helpme.bo.Ticket;
import fr.eni.helpme.dto.ResponseDTO;
import fr.eni.helpme.dto.TicketDTO;

import java.util.List;

public interface TicketService {

    List<Ticket> getAllTickets();

    Ticket trouverTicketParId(String id);

    Ticket ajouterTicket(TicketDTO ticketDto);

    Ticket ajouterResponse(String idTicket, ResponseDTO responseDto);
}
