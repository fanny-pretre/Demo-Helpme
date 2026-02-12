package fr.eni.helpme.rest;

import ch.qos.logback.core.net.server.Client;
import fr.eni.helpme.bll.TicketService;
import fr.eni.helpme.bo.Ticket;
import fr.eni.helpme.dto.ResponseDTO;
import fr.eni.helpme.dto.TicketDTO;
import fr.eni.helpme.exceptions.DataNotFound;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketRestController {

    private final TicketService ticketService;

    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // GET
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Ticket> getAllTickets() {
        List tickets = ticketService.getAllTickets();
        return tickets;
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getClientById(@PathVariable String id) {
        try {
            Ticket ticket = ticketService.trouverTicketParId(id);
            return ResponseEntity.ok(ticket);
        } catch (DataNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // POST
    @PostMapping("/{idCours}")
    public ResponseEntity<Ticket> createTicket(@PathVariable(name = "idCours") String idCours, @RequestBody TicketDTO ticketDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

       Ticket createdTicket = ticketService.ajouterTicket(idCours, ticketDTO);
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/api/tickets/" + createdTicket.getId())
                .body( createdTicket);
    }

    @PostMapping("/{idTicket}/responses")
    public ResponseEntity<Ticket> ajouterReponse(@PathVariable(name = "idTicket") String idTicket, @RequestBody ResponseDTO responseDto) {
        Ticket ticket = ticketService.ajouterResponse(idTicket, responseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }
}
