package fr.eni.helpme.bll;

import ch.qos.logback.core.net.server.Client;
import fr.eni.helpme.bo.Cours;
import fr.eni.helpme.bo.Response;
import fr.eni.helpme.bo.Ticket;
import fr.eni.helpme.dal.CoursRepository;
import fr.eni.helpme.dal.TicketRepository;
import fr.eni.helpme.dto.TicketDTO;
import fr.eni.helpme.dto.ResponseDTO;
import fr.eni.helpme.exceptions.DataNotFound;
import fr.eni.helpme.exceptions.TicketAlreadyExistException;
import fr.eni.helpme.exceptions.TicketNonTrouve;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final CoursRepository coursRepository;
    @NonNull
    private TicketRepository ticketRepository;

    public TicketServiceImpl(@NonNull TicketRepository ticketRepository, CoursRepository coursRepository) {
        this.ticketRepository = ticketRepository;
        this.coursRepository = coursRepository;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket trouverTicketParId(String id) {
        Optional<Ticket> optTicket = ticketRepository.findById(id);
        if (optTicket.isEmpty()) {
            throw new DataNotFound("Ticket", id);
        }
        return optTicket.get();
    }


    @Override
    public Ticket ajouterTicket(String idCours, TicketDTO ticketDto) {

        Cours cours = coursRepository.findById(idCours).orElseThrow(()->new TicketNonTrouve(idCours));
        Ticket ticket = new Ticket(ticketDto.getAuteur(), ticketDto.getMessage());
        ticket.setCours(cours);


        BeanUtils.copyProperties(ticketDto, ticketDto);

        Ticket newTicket= null;
        try {
            newTicket = ticketRepository.save(ticket);
        }catch(DataIntegrityViolationException ex) {
            throw new TicketAlreadyExistException();
        }

        return newTicket;
    }

    @Override
    public Ticket ajouterResponse(String idTicket, ResponseDTO responseDto) {
        Ticket  ticket = ticketRepository.findById(idTicket).orElseThrow(()->new TicketNonTrouve(idTicket));
        Response response = new Response(responseDto.getAuteur(),responseDto.getMessage());

        if(ticket.getResponses()==null)
            {ticket.setResponses(new ArrayList<>());}

        ticket.getResponses().add(response);
        return ticketRepository.save(ticket);
    }
}
