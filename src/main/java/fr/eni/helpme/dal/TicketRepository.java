package fr.eni.helpme.dal;

import fr.eni.helpme.bo.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {


}
